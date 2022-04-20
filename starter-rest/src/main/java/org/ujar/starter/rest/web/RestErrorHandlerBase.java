package org.ujar.starter.rest.web;

import static org.ujar.starter.rest.web.RestErrorHandlerUtils.getJsonPointerField;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.ujar.starter.rest.web.dto.Error;
import org.ujar.starter.rest.web.dto.ErrorResponse;
import org.ujar.starter.rest.web.dto.InvalidContentTypeMeta;
import org.ujar.starter.rest.web.dto.InvalidHttpMethodMeta;
import org.ujar.starter.rest.web.dto.InvalidRequestBodyMeta;
import org.ujar.starter.rest.web.dto.InvalidRequestParameterMeta;
import org.ujar.starter.rest.web.dto.UnknownErrorMeta;

public class RestErrorHandlerBase {

  public ErrorResponse handle(Exception exception, String code) {
    return ErrorResponse.singleError(new Error(
        code,
        exception.getMessage(),
        UnknownErrorMeta.fromException(exception)
    ));
  }

  public ErrorResponse handle(MethodArgumentNotValidException exception, String code) {
    var errors = exception.getBindingResult().getFieldErrors().stream()
        .map(error -> {
          var jsonPointer = getJsonPointerField(error);
          return new Error(
              code,
              jsonPointer + " " + error.getDefaultMessage(),
              new InvalidRequestBodyMeta(jsonPointer, error.getRejectedValue()));
        })
        .collect(Collectors.toList());
    return ErrorResponse.withErrors(errors);
  }

  public ErrorResponse handle(HttpMessageNotReadableException exception, String code) {
    Error error;

    if (exception.getCause() instanceof InvalidFormatException) {
      var formatException = (InvalidFormatException) exception.getCause();
      error = getErrorForInvalidFormat(formatException, code);
    } else {
      error = Error.of(
          code,
          exception.getMessage()
      );
    }
    return ErrorResponse.singleError(error);
  }

  public ErrorResponse handle(MissingServletRequestParameterException exception,
                              String code) {
    return ErrorResponse.singleError(new Error<>(
        code,
        exception.getMessage(),
        new InvalidRequestParameterMeta(exception.getParameterName(), null)
    ));
  }

  public ErrorResponse handle(TypeMismatchException exception, String code) {
    return ErrorResponse.singleError(new Error<>(
        code,
        exception.getMessage(),
        new InvalidRequestParameterMeta(
            exception.getPropertyName(),
            Objects.requireNonNullElse(exception.getValue(), "").toString()
        )));
  }

  public ErrorResponse handle(MethodArgumentTypeMismatchException exception,
                              String code) {
    return ErrorResponse.singleError(new Error<>(
        code,
        exception.getMessage(),
        new InvalidRequestParameterMeta(
            exception.getName(),
            Objects.requireNonNullElse(exception.getValue(), "").toString()
        )));
  }

  public ErrorResponse handle(ConstraintViolationException exception, String code) {
    var errors = exception.getConstraintViolations().stream()
        .map(constraintViolation -> {
              var parameter = StreamSupport
                  .stream(constraintViolation.getPropertyPath().spliterator(), false)
                  .collect(Collectors.toList())
                  // Path has the following structure: Method name -> Parameter name.
                  // So we fetch 2nd element of the collection
                  .get(1)
                  .toString();
              return new Error(
                  code,
                  parameter + " " + constraintViolation.getMessage(),
                  new InvalidRequestParameterMeta(
                      parameter,
                      Objects.toString(constraintViolation.getInvalidValue(), null)
                  )
              );
            }
        )
        .collect(Collectors.toList());
    return ErrorResponse.withErrors(errors);
  }

  public ErrorResponse handle(HttpRequestMethodNotSupportedException exception,
                              String code) {
    return ErrorResponse.singleError(new Error(
        code,
        exception.getMessage(),
        new InvalidHttpMethodMeta(
            exception.getMethod(),
            List.of(Objects.requireNonNullElseGet(exception.getSupportedMethods(), () -> new String[0]))
        )
    ));
  }

  public ErrorResponse handle(HttpMediaTypeNotSupportedException exception,
                              WebRequest webRequest, String code) {

    return ErrorResponse.singleError(new Error(
            code,
            exception.getMessage(),
            new InvalidContentTypeMeta(
                webRequest.getHeader(HttpHeaders.CONTENT_TYPE),
                exception.getSupportedMediaTypes()
                    .stream()
                    .map(MimeType::toString)
                    .collect(Collectors.toList())
            )
        )
    );
  }

  protected Error getErrorForInvalidFormat(InvalidFormatException formatException, String code) {
    var jsonPointer = getJsonPointerField(formatException);
    String detail;
    if (formatException.getTargetType().isEnum()) {
      var allowedValues = Stream.of(formatException.getTargetType().getEnumConstants())
          .map(Object::toString)
          .collect(Collectors.joining(","));

      detail = String.format("%s must be one of [%s]",
          jsonPointer,
          allowedValues);
    } else {
      detail = formatException.getOriginalMessage();
    }
    return new Error(
        code,
        detail,
        new InvalidRequestBodyMeta(
            jsonPointer,
            formatException.getValue()
        )
    );
  }


}
