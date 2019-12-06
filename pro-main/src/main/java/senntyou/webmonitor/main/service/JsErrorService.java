package senntyou.webmonitor.main.service;

import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import senntyou.webmonitor.main.dto.JsErrorQueryParam;
import senntyou.webmonitor.mbg.model.JsError;

public interface JsErrorService {
  @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
  int create(JsError errorLog);

  JsError getById(Integer id);

  @Transactional
  int update(Integer id, JsError jsError);

  List<JsError> list(JsErrorQueryParam errorLogQueryParam, Integer pageSize, Integer pageNum);
}
