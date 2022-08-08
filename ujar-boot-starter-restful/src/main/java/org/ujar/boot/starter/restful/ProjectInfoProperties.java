package org.ujar.boot.starter.restful;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConstructorBinding
@Validated
@ConfigurationProperties("build")
public record ProjectInfoProperties(@NotNull String version,
                                    @NotNull String description,
                                    @NotNull String relativePath) {

}
