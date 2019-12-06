package senntyou.webmonitor.main.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import senntyou.webmonitor.mbg.model.JsError;

public interface JsErrorDao {
  int insertList(@Param("list") List<JsError> list);
}
