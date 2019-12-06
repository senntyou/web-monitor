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
    JsError errorLog = new JsError();

    errorLog.setHref(this.getHref());
    errorLog.setUserAgent(this.getUserAgent());
    errorLog.setCookie(this.getCookie());
    errorLog.setMessage(this.getMessage());
    errorLog.setSource(this.getSource());
    errorLog.setLine(this.getLine());
    errorLog.setColumn(this.getColumn());
    errorLog.setError(this.getError());
    errorLog.setStack(this.getStack());
    errorLog.setTime(this.getTime());

    return errorLog;
  }
}
