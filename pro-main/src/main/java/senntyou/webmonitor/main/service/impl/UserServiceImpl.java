package senntyou.webmonitor.main.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import senntyou.webmonitor.main.dto.UserQueryParam;
import senntyou.webmonitor.main.service.UserService;
import senntyou.webmonitor.mapper.UserMapper;
import senntyou.webmonitor.model.User;
import senntyou.webmonitor.model.UserExample;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserMapper userMapper;

  @Override
  public User getRecord(long id) {
    UserExample example = new UserExample();
    example.createCriteria().andIdEqualTo(id);
    List<User> users = userMapper.selectByExample(example);

    if (!CollectionUtils.isEmpty(users)) {
      return users.get(0);
    }
    return null;
  }

  @Override
  public int update(long id, User user) {
    UserExample example = new UserExample();
    example.createCriteria().andIdEqualTo(id);

    userMapper.updateByExampleSelective(user, example);

    return 1;
  }

  @Override
  public List<User> list(UserQueryParam userQueryParam, Integer pageSize, Integer pageNum) {
    PageHelper.startPage(pageNum, pageSize);
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();

    criteria.andDeletedEqualTo(0);
    if (userQueryParam.getUsername() != null) {
      criteria.andUsernameLike("%" + userQueryParam.getUsername() + "%");
    }
    if (userQueryParam.getEmail() != null) {
      criteria.andEmailEqualTo(userQueryParam.getEmail());
    }
    return userMapper.selectByExample(example);
  }
}