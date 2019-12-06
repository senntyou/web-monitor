package senntyou.webmonitor.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import senntyou.webmonitor.common.CommonResult;

@Controller
@RequestMapping("/")
public class RouteController {
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<String> index() {
    return CommonResult.success("Ok");
  }

  @RequestMapping(value = "admin", method = RequestMethod.GET)
  public String admin() {
    return "index";
  }
}
