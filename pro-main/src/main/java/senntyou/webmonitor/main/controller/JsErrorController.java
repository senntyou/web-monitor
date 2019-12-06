package senntyou.webmonitor.main.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import senntyou.webmonitor.common.CommonPage;
import senntyou.webmonitor.common.CommonResult;
import senntyou.webmonitor.main.dao.JsErrorDao;
import senntyou.webmonitor.main.dto.JsErrorParam;
import senntyou.webmonitor.main.dto.JsErrorQueryParam;
import senntyou.webmonitor.main.service.JsErrorService;
import senntyou.webmonitor.mbg.model.JsError;

@RestController
@Api(tags = "JsErrorController", description = "JsError management")
@RequestMapping("/api/jsError")
public class JsErrorController {
  @Autowired private JsErrorService jsErrorService;
  @Autowired private JsErrorDao jsErrorDao;

  @ApiOperation("Create jsError")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult create(@RequestBody JsErrorParam jsErrorParam) {
    JsError jsError = jsErrorParam.toJsError();

    int count = jsErrorService.create(jsError);
    if (count > 0) {
      return CommonResult.success(count);
    } else {
      return CommonResult.failed();
    }
  }

  @ApiOperation("Create multiple jsErrors")
  @RequestMapping(value = "/createMulti", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult createMulti(@RequestBody List<JsErrorParam> jsErrorParamList) {
    String now = DateUtil.now();

    List<JsError> jsErrorList = new ArrayList<>();
    for (JsErrorParam jsErrorParam : jsErrorParamList) {
      JsError jsError = jsErrorParam.toJsError();
      jsError.setCreateTime(now);
      jsError.setUpdateTime(now);
      jsErrorList.add(jsError);
    }

    int count = jsErrorDao.insertList(jsErrorList);
    if (count > 0) {
      return CommonResult.success(count);
    } else {
      return CommonResult.failed();
    }
  }

  @ApiOperation("Query list")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<CommonPage<JsError>> list(
      JsErrorQueryParam jsErrorQueryParam,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    List<JsError> queryList = jsErrorService.list(jsErrorQueryParam, pageSize, pageNum);
    return CommonResult.success(CommonPage.toPage(queryList));
  }
}
