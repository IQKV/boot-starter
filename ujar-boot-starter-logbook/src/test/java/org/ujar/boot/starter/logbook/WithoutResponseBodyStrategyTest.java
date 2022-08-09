package org.ujar.boot.starter.logbook;

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
    final var logbookStrategy = new WithoutResponseBodyStrategy(400);
    final var sink = mock(Sink.class);
    final var httpRequest = mock(HttpRequest.class);
    final var httpResponse = mock(HttpResponse.class);
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
    final var logbookStrategy = new WithoutResponseBodyStrategy(400);
    final var sink = mock(Sink.class);
    final var httpRequest = mock(HttpRequest.class);
    final var httpResponse = mock(HttpResponse.class);
    when(httpResponse.getStatus()).thenReturn(200);
    final var httpRequestWithoutBody = mock(HttpRequest.class);
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
    final var httpResponse = mock(HttpResponse.class);
    final var httpResponseWithoutBody = mock(HttpResponse.class);
    when(httpResponse.withoutBody()).thenReturn(httpResponseWithoutBody);

    // when
    final var resultResponse = new WithoutResponseBodyStrategy(0).process(mock(HttpRequest.class), httpResponse);

    // then
    assertEquals(httpResponseWithoutBody, resultResponse);
    verify(httpResponse).withoutBody();
  }
}
