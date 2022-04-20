package org.ujar.starter.rest.web.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

  private List<Error> errors;

  public ErrorResponse(List<Error> errors) {
    this.errors = new ArrayList<>(errors);
  }

  public static ErrorResponse singleError(Error error) {
    return new ErrorResponse(List.of(error));
  }

  public static ErrorResponse withErrors(List<Error> errors) {
    return new ErrorResponse(errors);
  }

  public List<Error> getErrors() {
    return new ArrayList<>(errors);
  }

  public void setErrors(List<Error> errors) {
    this.errors = new ArrayList<>(errors);
  }

}
