package org.ujar.boot.starter.restful.web.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public record InvalidHttpMethodMeta(String invalidMethod, List<String> allowedMethods) {
  public List<String> getAllowedMethods() {
    return new ArrayList<>(allowedMethods);
  }
}
