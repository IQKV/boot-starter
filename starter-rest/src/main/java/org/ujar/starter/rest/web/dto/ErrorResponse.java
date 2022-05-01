package org.ujar.starter.rest.web.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse<M> {

  private List<Error<M>> errors;

  public ErrorResponse(List<Error<M>> errors) {
    this.errors = new ArrayList<>(errors);
  }

  public static ErrorResponse singleError(Error error) {
    return new ErrorResponse(List.of(error));
  }

  public List<Error> getErrors() {
    return new ArrayList<>(errors);
  }
}
