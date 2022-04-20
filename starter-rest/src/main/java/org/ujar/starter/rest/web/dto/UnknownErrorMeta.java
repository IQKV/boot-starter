package org.ujar.starter.rest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnknownErrorMeta {

  private String exceptionClass;
  private String stackTrace;

  public static UnknownErrorMeta fromException(Exception exception) {
    return new UnknownErrorMeta(
        exception.getClass().getSimpleName(),
        ExceptionUtils.getStackTrace(exception)
    );
  }

}
