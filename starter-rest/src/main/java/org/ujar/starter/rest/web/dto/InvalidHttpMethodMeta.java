package org.ujar.starter.rest.web.dto;

import java.util.ArrayList;
import java.util.List;

public record InvalidHttpMethodMeta(String invalidMethod, List<String> allowedMethods) {
  public List<String> getAllowedMethods() {
    return new ArrayList<>(allowedMethods);
  }
}
