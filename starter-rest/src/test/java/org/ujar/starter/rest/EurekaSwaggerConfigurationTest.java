package org.ujar.starter.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EurekaSwaggerConfigurationTest {
  @Test
  void shouldCorrectPassParams() {
    // given
    var eurekaSwaggerConfiguration = new SwaggerConfiguration.EurekaSwaggerConfiguration();
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
