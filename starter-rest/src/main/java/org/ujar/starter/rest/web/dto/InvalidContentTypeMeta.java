package org.ujar.starter.rest.web.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvalidContentTypeMeta {

  private String invalidType;
  private List<String> supportedTypes;

  public InvalidContentTypeMeta(String invalidType, List<String> supportedTypes) {
    this.supportedTypes = new ArrayList<>(supportedTypes);
    this.invalidType = invalidType;
  }

  public List<String> getSupportedTypes() {
    return new ArrayList<>(supportedTypes);
  }

  public void setSupportedTypes(List<String> supportedTypes) {
    this.supportedTypes = new ArrayList<>(supportedTypes);
  }
}
