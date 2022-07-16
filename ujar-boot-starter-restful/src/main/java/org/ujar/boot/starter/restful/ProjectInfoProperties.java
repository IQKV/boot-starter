package org.ujar.boot.starter.restful;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConstructorBinding
@AllArgsConstructor
@Value
@Validated
@ConfigurationProperties("build")
public class ProjectInfoProperties {
  @NotNull String version;
  @NotNull String description;
  @NotNull String relativePath;
}
