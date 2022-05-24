package org.ujar.boot.starter.logbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;

class ResponseOnStatusAtLeastStrategyTest {
  @Test
  void shouldWriteResponseWithoutBodyIfStatusLessThanMinimum() throws IOException {
    // given
    var logbookStrategy = new ResponseOnStatusAtLeastStrategy(400);
    var httpResponse = mock(HttpResponse.class);
    when(httpResponse.getStatus()).thenReturn(200);
    var httpResponseWithoutBody = mock(HttpResponse.class);
    when(httpResponse.withoutBody()).thenReturn(httpResponseWithoutBody);
    // when
    var resultResponse = logbookStrategy.process(mock(HttpRequest.class), httpResponse);
    // then
    assertEquals(httpResponseWithoutBody, resultResponse);
  }

  @Test
  void shouldWriteResponseWithBodyIfStatusMoreThanMinimum() throws IOException {
    // given
    var logbookStrategy = new ResponseOnStatusAtLeastStrategy(200);
    var httpResponse = mock(HttpResponse.class);
    when(httpResponse.getStatus()).thenReturn(400);
    var httpResponseWithBody = mock(HttpResponse.class);
    when(httpResponse.withBody()).thenReturn(httpResponseWithBody);
    // when
    var resultResponse = logbookStrategy.process(mock(HttpRequest.class), httpResponse);
    // then
    assertEquals(httpResponseWithBody, resultResponse);
  }
}
