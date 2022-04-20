package org.ujar.starter.rest.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class RequestDto {

  @Max(100)
  @Min(10)
  private int numberField;

  @Size(min = 2, max = 3)
  private List<@NotNull String> stringListField;

  @Valid
  @NotNull
  private NestedObject nestedObjectField;

  @Valid
  @NotNull
  private List<NestedObject> nestedObjectListField;

  @NotBlank
  private String stringField;

  @NotNull
  private UUID uuidField;

  @NotNull
  private StatusType enumField;

  public List<String> getStringListField() {
    return new ArrayList<>(stringListField);
  }

  public RequestDto setStringListField(List<String> stringListField) {
    this.stringListField = new ArrayList<>(stringListField);
    return this;
  }

  public List<NestedObject> getNestedObjectListField() {
    return new ArrayList<>(nestedObjectListField);
  }

  public RequestDto setNestedObjectListField(
      List<NestedObject> nestedObjectListField) {
    this.nestedObjectListField = new ArrayList<>(nestedObjectListField);
    return this;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NestedObject {

    @NotBlank
    private String stringField;
  }
}
