package org.ujar.starter.rest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KubernetesSwaggerConfigTest {
  @Test
  void shouldCorrectPassParams() {
    // given
    var k8sSwaggerConfiguration = new SwaggerConfiguration.K8sSwaggerConfiguration();
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
