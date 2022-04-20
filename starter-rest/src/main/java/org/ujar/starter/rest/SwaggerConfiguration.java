package org.ujar.starter.rest;

import static java.util.Objects.requireNonNullElse;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnMissingBean(OpenAPI.class)
@Import(value = {
    SwaggerConfiguration.EurekaSwaggerConfiguration.class,
    SwaggerConfiguration.K8sSwaggerConfiguration.class,
})
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
      server.setUrl(requireNonNullElse(projectInfoProperties.getRelativePath(), ""));
      api.setServers(List.of(server));
      return api;
    }
  }

  @Configuration
  @ConditionalOnClass(name = "com.netflix.discovery.EurekaClientConfig")
  @ConditionalOnProperty(value = "eureka.client.enabled", matchIfMissing = true)
  public static class EurekaSwaggerConfiguration {
    @Bean
    @Primary
    protected OpenAPI eurekaOpenApi(
        @Value("${spring.application.name}") String applicationName,
        ProjectInfoProperties projectInfoProperties
    ) {
      return new OpenAPI().info(
          new Info()
              .title(applicationName)
              .version(projectInfoProperties.getVersion())
              .description(projectInfoProperties.getDescription())
      );
    }
  }

}
