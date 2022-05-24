package org.ujar.boot.starter.restful.web.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressFBWarnings("EI_EXPOSE_REP")
public record InvalidContentTypeMeta(String invalidType, List<String> supportedTypes) {

  public List<String> getSupportedTypes() {
    return new ArrayList<>(supportedTypes);
  }
}
