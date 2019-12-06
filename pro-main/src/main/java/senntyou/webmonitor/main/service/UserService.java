package senntyou.webmonitor.main.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import senntyou.webmonitor.main.dto.UserQueryParam;
import senntyou.webmonitor.model.User;

public interface UserService {
  User getRecord(long id);

  @Transactional
  int update(long id, User user);

  List<User> list(UserQueryParam userQueryParam, Integer pageSize, Integer pageNum);
}
