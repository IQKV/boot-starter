package org.ujar.boot.starter.restful.web.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDto {
  @Parameter(description = "Page number")
  @Schema(minimum = "0")
  @Min(0)
  private int page;
  @Parameter(description = "Page size")
  @Schema(minimum = "1", maximum = "200")
  @Min(1)
  @Max(200)
  private Integer size = 10;
}

