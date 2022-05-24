package org.ujar.boot.starter.restful.web.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.boot.starter.restful.web.RequestDto;
import org.ujar.boot.starter.restful.web.StatusType;
import org.ujar.boot.starter.restful.web.TestController;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {TestController.class})
public class DefaultRestErrorHandlerTestBase {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  protected RequestDto createValidRequest() {
    return new RequestDto()
        .setNumberField(50)
        .setStringListField(List.of("a", "b", "cdef"))
        .setNestedObjectField(new RequestDto.NestedObject("abc"))
        .setNestedObjectListField(List.of(
            new RequestDto.NestedObject("123"),
            new RequestDto.NestedObject("456")
        ))
        .setStringField("stringField")
        .setUuidField(UUID.randomUUID())
        .setEnumField(StatusType.BAR);
  }

}
