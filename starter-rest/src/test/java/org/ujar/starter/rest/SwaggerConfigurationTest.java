package org.ujar.starter.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.ujar.starter.rest.SwaggerConfiguration.EurekaSwaggerConfiguration;
import org.ujar.starter.rest.SwaggerConfiguration.K8sSwaggerConfiguration;

class SwaggerConfigurationTest {

  @Nested
  class K8sSwaggerConfigurationTest {
    @Test
    void shouldCorrectPassParams() {
      // given
      var k8sSwaggerConfiguration = new K8sSwaggerConfiguration();
      var projectInfoProperties = new ProjectInfoProperties();
      projectInfoProperties.setDescription("Test Description");
      projectInfoProperties.setRelativePath("/application");
      projectInfoProperties.setVersion("1.0");
      // when
      var actual = k8sSwaggerConfiguration.openApi("testName", projectInfoProperties);
      // then
      assertAll(
          () -> assertEquals("/application", actual.getServers().get(0).getUrl()),
          () -> assertEquals("Test Description", actual.getInfo().getDescription()),
          () -> assertEquals("testName", actual.getInfo().getTitle()),
          () -> assertEquals("1.0", actual.getInfo().getVersion())
      );
    }
  }

  @SpringBootTest(classes = SwaggerConfiguration.class)
  @EnableConfigurationProperties(ProjectInfoProperties.class)
  @Nested
  class K8sSwaggerConfigurationTestIt {
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

  @Nested
  class EurekaSwaggerConfigurationTest {
    @Test
    void shouldCorrectPassParams() {
      // given
      var eurekaSwaggerConfiguration = new EurekaSwaggerConfiguration();
      var projectInfoProperties = new ProjectInfoProperties();
      projectInfoProperties.setDescription("Test Description");
      projectInfoProperties.setRelativePath("/application");
      projectInfoProperties.setVersion("1.0");
      // when
      var actual = eurekaSwaggerConfiguration.eurekaOpenApi("testName", projectInfoProperties);
      // then
      assertAll(
          () -> assertThat(actual.getServers()).isNullOrEmpty(),
          () -> assertEquals("Test Description", actual.getInfo().getDescription()),
          () -> assertEquals("testName", actual.getInfo().getTitle()),
          () -> assertEquals("1.0", actual.getInfo().getVersion())
      );
    }
  }
}
