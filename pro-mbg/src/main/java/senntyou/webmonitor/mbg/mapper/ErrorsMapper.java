package senntyou.webmonitor.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import senntyou.webmonitor.mbg.model.Errors;
import senntyou.webmonitor.mbg.model.ErrorsExample;

public interface ErrorsMapper {
    long countByExample(ErrorsExample example);

    int deleteByExample(ErrorsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Errors record);

    int insertSelective(Errors record);

    List<Errors> selectByExample(ErrorsExample example);

    Errors selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Errors record, @Param("example") ErrorsExample example);

    int updateByExample(@Param("record") Errors record, @Param("example") ErrorsExample example);

    int updateByPrimaryKeySelective(Errors record);

    int updateByPrimaryKey(Errors record);
}