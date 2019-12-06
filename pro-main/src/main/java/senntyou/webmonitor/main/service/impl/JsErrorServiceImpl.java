package senntyou.webmonitor.main.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import senntyou.webmonitor.main.dto.JsErrorQueryParam;
import senntyou.webmonitor.main.service.JsErrorService;
import senntyou.webmonitor.mbg.mapper.JsErrorMapper;
import senntyou.webmonitor.mbg.model.JsError;
import senntyou.webmonitor.mbg.model.JsErrorExample;

@Service
public class JsErrorServiceImpl implements JsErrorService {
  @Autowired private JsErrorMapper jsErrorMapper;

  @Override
  public int create(JsError jsError) {
    jsError.setId(null);
    String now = DateUtil.now();
    jsError.setCreateTime(now);
    jsError.setUpdateTime(now);
    jsErrorMapper.insertSelective(jsError);

    return 1;
  }

  @Override
  public JsError getById(Integer id) {
    JsErrorExample example = new JsErrorExample();
    example.createCriteria().andIdEqualTo(id);
    List<JsError> jsErrors = jsErrorMapper.selectByExample(example);

    if (!CollectionUtils.isEmpty(jsErrors)) {
      return jsErrors.get(0);
    }
    return null;
  }

  @Override
  public int update(Integer id, JsError jsError) {
    JsErrorExample example = new JsErrorExample();
    example.createCriteria().andIdEqualTo(id);

    jsErrorMapper.updateByExampleSelective(jsError, example);

    return 1;
  }

  @Override
  public List<JsError> list(
      JsErrorQueryParam jsErrorQueryParam, Integer pageSize, Integer pageNum) {
    PageHelper.startPage(pageNum, pageSize);
    JsErrorExample example = new JsErrorExample();
    JsErrorExample.Criteria criteria = example.createCriteria();

    example.setOrderByClause("id desc");

    if (jsErrorQueryParam.getHref() != null) {
      criteria.andHrefLike("%" + jsErrorQueryParam.getHref() + "%");
    }
    if (jsErrorQueryParam.getUserAgent() != null) {
      criteria.andUserAgentLike("%" + jsErrorQueryParam.getUserAgent() + "%");
    }
    if (jsErrorQueryParam.getCookie() != null) {
      criteria.andCookieLike("%" + jsErrorQueryParam.getCookie() + "%");
    }
    if (jsErrorQueryParam.getTime() != null) {
      criteria.andTimeLike("%" + jsErrorQueryParam.getTime() + "%");
    }
    return jsErrorMapper.selectByExample(example);
  }
}
