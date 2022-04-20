package org.ujar.starter.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("build")
public class ProjectInfoProperties {
  private String version;
  private String description = "";
  private String relativePath = "";

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }
}
