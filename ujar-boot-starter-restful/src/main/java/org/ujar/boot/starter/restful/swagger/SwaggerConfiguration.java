package org.ujar.boot.starter.restful.swagger;

import static java.util.Objects.requireNonNullElse;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.starter.restful.ProjectInfoProperties;

@Configuration
@ConditionalOnMissingBean(OpenAPI.class)
@Import(value = {
    SwaggerConfiguration.K8sSwaggerConfiguration.class,
})
@Slf4j
public class SwaggerConfiguration {

  @Configuration
  public static class K8sSwaggerConfiguration {
    @Bean
    public OpenAPI openApi(
        @Value("${spring.application.name}") String applicationName,
        ProjectInfoProperties projectInfoProperties
    ) {
      var api = new OpenAPI().info(
          new Info()
              .title(applicationName)
              .version(projectInfoProperties.getVersion())
              .description(projectInfoProperties.getDescription())
      );
      var server = new Server();
      log.info("Project info properties: {}", projectInfoProperties);
      server.setUrl(requireNonNullElse(projectInfoProperties.getRelativePath(), ""));
      api.setServers(List.of(server));
      return api;
    }
  }
}
