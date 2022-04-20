package org.ujar.starter.rest.web.dto;

import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error<M> {

  private String code;
  private String detail;
  private M meta;


  public static Error of(String code, String detail) {
    return new Error(code, detail, Collections.emptyMap());
  }

}
