package senntyou.webmonitor.main.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import senntyou.webmonitor.mbg.model.JsError;

@Data
public class JsErrorParam {
  @ApiModelProperty(value = "href")
  private String href;

  @ApiModelProperty(value = "userAgent")
  private String userAgent;

  @ApiModelProperty(value = "cookie")
  private String cookie;

  @ApiModelProperty(value = "message")
  private String message;

  @ApiModelProperty(value = "source")
  private String source;

  @ApiModelProperty(value = "line")
  private String line;

  @ApiModelProperty(value = "column")
  private String column;

  @ApiModelProperty(value = "error")
  private String error;

  @ApiModelProperty(value = "stack")
  private String stack;

  @ApiModelProperty(value = "time")
  private String time;

  public JsError toJsError() {
    JsError jsError = new JsError();

    jsError.setHref(this.getHref());
    jsError.setUserAgent(this.getUserAgent());
    jsError.setCookie(this.getCookie());
    jsError.setMessage(this.getMessage());
    jsError.setSource(this.getSource());
    jsError.setLine(this.getLine());
    jsError.setColumn(this.getColumn());
    jsError.setError(this.getError());
    jsError.setStack(this.getStack());
    jsError.setTime(this.getTime());

    return jsError;
  }
}
