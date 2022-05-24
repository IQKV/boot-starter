package org.ujar.boot.starter.logbook;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Strategy;

public class ResponseOnStatusAtLeastStrategy implements Strategy {
  private final int minimumStatus;

  public ResponseOnStatusAtLeastStrategy(@Value("${logbook.minimum-status:0}")
                                             int minimumStatus) {
    this.minimumStatus = minimumStatus;
  }

  @Override
  public HttpResponse process(HttpRequest request, HttpResponse response) throws IOException {
    if (response.getStatus() >= minimumStatus) {
      return response.withBody();
    } else {
      return response.withoutBody();
    }
  }
}
