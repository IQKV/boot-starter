package org.ujar.starter.rest.web.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvalidHttpMethodMeta {

  private String invalidMethod;
  private List<String> allowedMethods;

  public InvalidHttpMethodMeta(String invalidMethod, List<String> allowedMethods) {
    this.invalidMethod = invalidMethod;
    this.allowedMethods = new ArrayList<>(allowedMethods);
  }

  public List<String> getAllowedMethods() {
    return new ArrayList<>(allowedMethods);
  }

  public void setAllowedMethods(List<String> allowedMethods) {
    this.allowedMethods = new ArrayList<>(allowedMethods);
  }
}
