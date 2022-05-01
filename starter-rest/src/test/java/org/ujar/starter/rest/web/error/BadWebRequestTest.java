package org.ujar.starter.rest.web.error;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BadWebRequestTest extends DefaultRestErrorHandlerTestBase {

  @Test
  @SneakyThrows
  void methodNotSupported() {
    mockMvc.perform(
            post("/request-param")
                .param("stringParam", "foo")
                .param("integerParam", "42")
        )
        .andDo(print())
        .andExpect(status().isMethodNotAllowed())
        .andExpect(content().json(
            "{\n"
            + "  \"errors\": [\n"
            + "    {\n"
            + "      \"code\": \"201\",\n"
            + "      \"detail\": \"Request method 'POST' not supported\",\n"
            + "      \"meta\": {\n"
            + "        \"invalidMethod\": \"POST\",\n"
            + "        \"allowedMethods\": [\"GET\"]\n"
            + "      }\n"
            + "    }\n"
            + "  ]\n"
            + "}"
        ));
  }

  @Test
  @SneakyThrows
  void mediaTypeNotSupported() {
    mockMvc.perform(
            post("/request-body")
                .contentType(MediaType.APPLICATION_XML)
                .content(objectMapper.writeValueAsString(createValidRequest()))
        )
        .andDo(print())
        .andExpect(status().isUnsupportedMediaType())
        .andExpect(content().json(
            "{\n"
            + "  \"errors\": [\n"
            + "    {\n"
            + "      \"code\": \"202\",\n"
            + "      \"detail\": \"Content type 'application/xml' not supported\",\n"
            + "      \"meta\": {\n"
            + "        \"invalidType\":\"application/xml;charset=UTF-8\",\n"
            + "        \"supportedTypes\":[\"application/json\"]\n"
            + "      }\n"
            + "    }\n"
            + "  ]\n"
            + "}"
        ));
  }

  @Test
  @SneakyThrows
  void unknownException() {
    mockMvc.perform(
            get("/exception")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isInternalServerError())
        .andExpect(content().json(
            "{\n"
            + "  \"errors\": [\n"
            + "    {\n"
            + "      \"code\": \"301\",\n"
            + "      \"detail\": \"Something bad happened...\",\n"
            + "      \"meta\": {\n"
            + "        \"exceptionClass\": \"RuntimeException\"\n"
            + "      }\n"
            + "    }\n"
            + "  ]\n"
            + "}"
        ));
  }
}
