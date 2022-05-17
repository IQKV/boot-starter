package org.ujar.starter.rest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SwaggerConfiguration.class)
@EnableConfigurationProperties(ProjectInfoProperties.class)
class KubernetesSwaggerConfigIntegrationTest {
  @Autowired
  private OpenAPI openApi;

  @Test
  void testStart() {
    // then
    assertAll(
        () -> assertEquals("/application", openApi.getServers().get(0).getUrl()),
        () -> assertEquals("Test description", openApi.getInfo().getDescription()),
        () -> assertEquals("application", openApi.getInfo().getTitle()),
        () -> assertEquals("1.0", openApi.getInfo().getVersion())
    );
  }
}
