package org.ujar.boot.starter.restful.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.validation.FieldError;

class RestfulErrorHandlerUtilsTest {

  @ParameterizedTest
  @CsvSource({
      "root,/root",
      "root[100],/root/100",
      "root.children,/root/children",
      "root[1].children,/root/1/children",
      "root[1].child1[2].child3[3][4][5][6].child4,/root/1/child1/2/child3/3/4/5/6/child4"
  })
  void shouldParseToJsonPath(String input, String expected) {
    final var actual = RestfulErrorHandlerUtils.getJsonPointerField(new FieldError("", input, ""));

    assertEquals(expected, actual);
  }

  @Test
  void shouldGetJsonPathFromInvalidFormatException() {
    final var exception = mock(InvalidFormatException.class);
    when(exception.getPath()).thenReturn(
        List.of(
            new JsonMappingException.Reference(Object.class, "fieldName"),
            new JsonMappingException.Reference(new ArrayList<>(), 0),
            new JsonMappingException.Reference(Object.class, "anotherField"),
            new JsonMappingException.Reference(List.of(), 100)
        )
    );

    final var actual = RestfulErrorHandlerUtils.getJsonPointerField(exception);

    assertEquals("/fieldName/0/anotherField/100", actual);
  }
}
