package senntyou.webmonitor.main.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JsErrorQueryParam {
  @ApiModelProperty(value = "href")
  private String href;

  @ApiModelProperty(value = "userAgent")
  private String userAgent;

  @ApiModelProperty(value = "cookie")
  private String cookie;

  @ApiModelProperty(value = "time")
  private String time;
}
