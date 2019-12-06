package senntyou.webmonitor.main.dao;

import org.apache.ibatis.annotations.Param;
import senntyou.webmonitor.model.Article;

public interface ArticleDao {
  Article getRecord(@Param("id") long id);
}
