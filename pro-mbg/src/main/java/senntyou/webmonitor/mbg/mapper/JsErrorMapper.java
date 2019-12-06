package senntyou.webmonitor.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import senntyou.webmonitor.mbg.model.JsError;
import senntyou.webmonitor.mbg.model.JsErrorExample;

public interface JsErrorMapper {
    long countByExample(JsErrorExample example);

    int deleteByExample(JsErrorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JsError record);

    int insertSelective(JsError record);

    List<JsError> selectByExample(JsErrorExample example);

    JsError selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JsError record, @Param("example") JsErrorExample example);

    int updateByExample(@Param("record") JsError record, @Param("example") JsErrorExample example);

    int updateByPrimaryKeySelective(JsError record);

    int updateByPrimaryKey(JsError record);
}