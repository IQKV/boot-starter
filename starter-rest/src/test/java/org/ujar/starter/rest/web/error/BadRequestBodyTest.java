package org.ujar.starter.rest.web.error;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ujar.starter.rest.web.RequestDto;

@ExtendWith(SpringExtension.class)
public class BadRequestBodyTest extends DefaultRestErrorHandlerTestBase {


  @Test
  @SneakyThrows
  void endpointIsAvailable() {
    mockMvc.perform(
            post("/request-body")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createValidRequest()))
        )
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @ParameterizedTest
  @CsvSource(value = {"-15,/numberField must be greater than or equal to 10",
      "1000,/numberField must be less than or equal to 100"})
  void maxAndMinNumber(int number, String errorMessage) {
    var request = createValidRequest();
    request.setNumberField(number);

    doRequestAndVerifyBody(
        request,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"" + errorMessage + "\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/numberField\",\n"
        + "        \"invalidValue\": " + number + "\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}"
    );
  }

  @Test
  @SneakyThrows
  void numberNonNumeric() {
    RequestDto request = createValidRequest();
    ObjectNode node = objectMapper.valueToTree(request);
    node.remove("numberField");
    node.put("numberField", "not a number");

    doRequestAndVerifyBody(
        node,
        //language=JSON
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"Cannot deserialize value of type `int` from String \\\"not a number\\\": not a valid"
        + " `int` value\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/numberField\",\n"
        + "        \"invalidValue\": \"not a number\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}"
    );
  }

  @ParameterizedTest
  @ValueSource(strings = {"1,2,3,4", "1"})
  void listSize(String values) {
    var request = createValidRequest();
    request.setStringListField(List.of(values.split(",")));

    var invalidValue = request.getStringListField()
        .stream()
        .map(str -> "\"" + str + "\"")
        .collect(Collectors.joining(","));
    doRequestAndVerifyBody(
        request,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/stringListField size must be between 2 and 3\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/stringListField\",\n"
        + "        \"invalidValue\": [" + invalidValue + "]\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void nonNullListElement() {
    var request = createValidRequest();
    request.setStringListField(Arrays.asList("1", null, "2"));

    doRequestAndVerifyBody(
        request,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/stringListField/1 must not be null\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/stringListField/1\",\n"
        + "        \"invalidValue\": null\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void nestedObjectInvalidValue() {
    var request = createValidRequest();
    request.getNestedObjectField().setStringField("");

    doRequestAndVerifyBody(
        request,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/nestedObjectField/stringField must not be blank\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/nestedObjectField/stringField\",\n"
        + "        \"invalidValue\": \"\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void nestedListInvalidValue() {
    var request = createValidRequest();
    request.getNestedObjectListField().get(1).setStringField("");

    doRequestAndVerifyBody(
        request,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/nestedObjectListField/1/stringField must not be blank\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/nestedObjectListField/1/stringField\",\n"
        + "        \"invalidValue\": \"\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void stringNotBlank() {
    var request = createValidRequest();
    request.setStringField("");

    doRequestAndVerifyBody(
        request,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/stringField must not be blank\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/stringField\",\n"
        + "        \"invalidValue\": \"\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void uuidInvalid() {
    RequestDto request = createValidRequest();
    ObjectNode node = objectMapper.valueToTree(request);
    node.remove("uuidField");
    node.put("uuidField", "not a uuid");

    doRequestAndVerifyBody(
        node,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"Cannot deserialize value of type `java.util.UUID` from String \\\"not a uuid\\\": "
        + "UUID has to be represented by standard 36-char representation\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/uuidField\",\n"
        + "        \"invalidValue\": \"not a uuid\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void invalidEnum() {
    RequestDto request = createValidRequest();
    ObjectNode node = objectMapper.valueToTree(request);
    node.remove("enumField");
    node.put("enumField", "bad enum");

    doRequestAndVerifyBody(
        node,
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/enumField must be one of [FOO,BAR]\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/enumField\",\n"
        + "        \"invalidValue\": \"bad enum\"\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @Test
  void fieldNonNull() {
    var request = createValidRequest();
    request.setNestedObjectField(null);

    doRequestAndVerifyBody(
        request,
        //language=JSON
        "{\n"
        + "  \"errors\": [\n"
        + "    {\n"
        + "      \"code\": \"101\",\n"
        + "      \"detail\": \"/nestedObjectField must not be null\",\n"
        + "      \"meta\": {\n"
        + "        \"field\": \"/nestedObjectField\",\n"
        + "        \"invalidValue\": null\n"
        + "      }\n"
        + "    }\n"
        + "  ]\n"
        + "}");
  }

  @SneakyThrows
  private void doRequestAndVerifyBody(Object request, String body) {
    mockMvc.perform(
            post("/request-body")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json(body));
  }
}
