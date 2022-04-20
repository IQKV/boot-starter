package org.ujar.starter.rest.logbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Sink;

class WithoutResponseBodyStrategyTest {
  @Test
  void shouldWriteRequestWithoutBodyIfStatusNotInList() throws IOException {
    // given
    var logbookStrategy = new WithoutResponseBodyStrategy(400);
    var sink = mock(Sink.class);
    var httpRequest = mock(HttpRequest.class);
    var httpResponse = mock(HttpResponse.class);
    when(httpResponse.getStatus()).thenReturn(500);
    // when
    logbookStrategy.write(mock(Correlation.class), httpRequest, httpResponse, sink);
    // then
    verify(sink).writeBoth(any(), eq(httpRequest), eq(httpResponse));
    verifyNoMoreInteractions(sink);
  }

  @Test
  void shouldWriteRequestWithoutBodyIfStatusInList() throws IOException {
    // given
    var logbookStrategy = new WithoutResponseBodyStrategy(400);
    var sink = mock(Sink.class);
    var httpRequest = mock(HttpRequest.class);
    var httpResponse = mock(HttpResponse.class);
    when(httpResponse.getStatus()).thenReturn(200);
    var httpRequestWithoutBody = mock(HttpRequest.class);
    when(httpRequest.withoutBody()).thenReturn(httpRequestWithoutBody);
    // when
    logbookStrategy.write(mock(Correlation.class), httpRequest, httpResponse, sink);
    // then
    verify(httpRequest).withoutBody();
    verify(sink).writeBoth(any(), eq(httpRequestWithoutBody), eq(httpResponse));
    verifyNoMoreInteractions(sink);
  }

  @Test
  void shouldMakeResponseWithoutBody() {
    // given
    var httpResponse = mock(HttpResponse.class);
    var httpResponseWithoutBody = mock(HttpResponse.class);
    when(httpResponse.withoutBody()).thenReturn(httpResponseWithoutBody);
    // when
    var resultResponse = new WithoutResponseBodyStrategy(0).process(mock(HttpRequest.class), httpResponse);
    // then
    assertEquals(httpResponseWithoutBody, resultResponse);
    verify(httpResponse).withoutBody();
  }
}
