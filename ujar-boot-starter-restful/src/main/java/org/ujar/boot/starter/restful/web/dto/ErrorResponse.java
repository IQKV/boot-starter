package org.ujar.boot.starter.restful.web.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@NoArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP2")
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ErrorResponse<?> that = (ErrorResponse<?>) o;

    return new EqualsBuilder().append(errors, that.errors).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(errors).toHashCode();
  }
}
