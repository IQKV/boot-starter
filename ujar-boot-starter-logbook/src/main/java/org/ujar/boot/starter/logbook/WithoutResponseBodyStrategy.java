package org.ujar.boot.starter.logbook;

import java.io.IOException;
import javax.annotation.ParametersAreNonnullByDefault;
import org.springframework.beans.factory.annotation.Value;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.Sink;
import org.zalando.logbook.Strategy;

public class WithoutResponseBodyStrategy implements Strategy {
  private final int minimumStatus;

  public WithoutResponseBodyStrategy(@Value("${logbook.minimum-status:0}")
                                         int minimumStatus) {
    this.minimumStatus = minimumStatus;
  }

  @Override
  public HttpResponse process(HttpRequest request, HttpResponse response) {
    return response.withoutBody();
  }

  @Override
  @ParametersAreNonnullByDefault
  public void write(Precorrelation precorrelation, HttpRequest request, Sink sink) {
    // defer decision until response is available if status
  }

  @Override
  public void write(Correlation correlation,
                    HttpRequest request,
                    HttpResponse response,
                    Sink sink) throws IOException {
    if (response.getStatus() >= minimumStatus) {
      sink.writeBoth(correlation, request, response);
    } else {
      sink.writeBoth(correlation, request.withoutBody(), response);
    }
  }
}

