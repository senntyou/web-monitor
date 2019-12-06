package senntyou.webmonitor.main.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import senntyou.webmonitor.main.dto.ArticleQueryParam;
import senntyou.webmonitor.model.Article;

public interface ArticleService {
  Article getRecord(long id);

  @Transactional
  int update(long id, Article article);

  List<Article> list(ArticleQueryParam articleQueryParam, Integer pageSize, Integer pageNum);
}
