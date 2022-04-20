package org.ujar.starter.rest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvalidRequestParameterMeta {

  private String parameter;
  private String invalidValue;

}
