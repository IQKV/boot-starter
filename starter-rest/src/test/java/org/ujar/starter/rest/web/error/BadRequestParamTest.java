package org.ujar.starter.rest.web.error;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class BadRequestParamTest extends DefaultRestErrorHandlerTestBase {

  @Test
  @SneakyThrows
  void endpointIsAvailable() {
    mockMvc.perform(
            get("/request-param")
                .param("stringParam", "foo")
                .param("integerParam", "42")
        )
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @Test
  @SneakyThrows
  void requiredParamIsNotSet() {
    doRequestAndVerifyBody(
        "foo", null,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"102\",\n"
        + "      \"detail\": \"Required request parameter 'integerParam'"
        + " for method parameter type int is not present\",\n"
        + "      \"meta\": {\n"
        + "        \"parameter\": \"integerParam\",\n"
        + "        \"invalidValue\": null\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}"
    );
  }

  @Test
  @SneakyThrows
  void stringSizeConstraint() {
    doRequestAndVerifyBody(
        "zzzzzzzzzzzzz", "5",
        //language=JSON
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"102\",\n"
        + "      \"detail\": \"stringParam size must be between 0 and 10\",\n"
        + "      \"meta\": {\n"
        + "        \"parameter\": \"stringParam\",\n"
        + "        \"invalidValue\": \"zzzzzzzzzzzzz\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}"
    );
  }

  @ParameterizedTest
  @CsvSource(value = {
      "-15,intParam must be greater than or equal to 0",
      "10000,intParam must be less than or equal to 1000"
  })
  void maxAndMinNumber(String number, String errorMessage) {
    doRequestAndVerifyBody(
        "foo", number,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"102\",\n"
        + "      \"detail\": \"" + errorMessage + "\",\n"
        + "      \"meta\": {\n"
        + "        \"parameter\": \"intParam\",\n"
        + "        \"invalidValue\": \"" + number + "\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}"
    );
  }

  @Test
  @SneakyThrows
  void nonNumeric() {
    doRequestAndVerifyBody(
        "foo", "abc",
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"102\",\n"
        + "      \"detail\": \"Failed to convert value of type 'java.lang.String' to required type 'int'; "
        + "nested exception is java.lang.NumberFormatException: For input string: \\\"abc\\\"\",\n"
        + "      \"meta\": {\n"
        + "        \"parameter\": \"integerParam\",\n"
        + "        \"invalidValue\": \"abc\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}"
    );
  }

  @SneakyThrows
  void doRequestAndVerifyBody(String stringParam, String intParam, String body) {
    mockMvc.perform(
            get("/request-param")
                .param("stringParam", stringParam)
                .param("integerParam", intParam)
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json(body));
  }
}
