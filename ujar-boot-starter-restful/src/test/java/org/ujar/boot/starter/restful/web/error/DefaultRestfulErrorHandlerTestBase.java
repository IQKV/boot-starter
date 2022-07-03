package org.ujar.boot.starter.restful.web.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.boot.starter.restful.web.RequestDto;
import org.ujar.boot.starter.restful.web.StatusType;
import org.ujar.boot.starter.restful.web.TestController;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {TestController.class})
@SuppressFBWarnings("EI_EXPOSE_REP2")
public class DefaultRestfulErrorHandlerTestBase {

  protected final MockMvc mockMvc;
  protected final ObjectMapper objectMapper;


  public DefaultRestfulErrorHandlerTestBase(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

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
