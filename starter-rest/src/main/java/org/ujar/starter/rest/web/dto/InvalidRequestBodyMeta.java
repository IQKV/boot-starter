package org.ujar.starter.rest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvalidRequestBodyMeta {

  private String field;
  private Object invalidValue;

}
