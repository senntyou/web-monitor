package senntyou.webmonitor.main.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import senntyou.webmonitor.common.CommonResult;
import senntyou.webmonitor.main.bo.UserInfo;
import senntyou.webmonitor.main.component.JwtToken;
import senntyou.webmonitor.main.service.UserService;
import senntyou.webmonitor.mbg.mapper.UserMapper;
import senntyou.webmonitor.mbg.model.User;
import senntyou.webmonitor.mbg.model.UserExample;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired private UserDetailsService userDetailsService;
  @Autowired private UserMapper userMapper;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JwtToken jwtToken;

  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @Override
  public User getByUsername(String username) {
    UserExample example = new UserExample();
    example.createCriteria().andUsernameEqualTo(username);
    List<User> users = userMapper.selectByExample(example);
    if (!CollectionUtils.isEmpty(users)) {
      return users.get(0);
    }
    return null;
  }

  @Override
  public User getById(int id) {
    UserExample example = new UserExample();
    example.createCriteria().andIdEqualTo(id);
    List<User> users = userMapper.selectByExample(example);
    if (!CollectionUtils.isEmpty(users)) {
      return users.get(0);
    }
    return null;
  }

  @Override
  public CommonResult register(String username, String password) {
    // check if the username existed
    UserExample example = new UserExample();
    example.createCriteria().andUsernameEqualTo(username);
    List<User> users = userMapper.selectByExample(example);
    if (!CollectionUtils.isEmpty(users)) {
      return CommonResult.failed("User '" + username + "' existed");
    }

    // Add the user
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    userMapper.insertSelective(user);
    return CommonResult.success(null, "Sign up succeeded");
  }

  @Override
  public String login(String username, String password) {
    String token = null;

    try {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException("Password is not correct.");
      }
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtToken.generateToken(userDetails);
    } catch (AuthenticationException e) {
      log.warn("Login failed: {}", e.getMessage());
    }
    return token;
  }

  @Override
  public String refreshToken(String oldToken) {
    String token = oldToken.substring(tokenHead.length());
    if (jwtToken.canRefresh(token)) {
      return jwtToken.refreshToken(token);
    }
    return null;
  }

  @Override
  public CommonResult updatePassword(String username, String password) {
    UserExample example = new UserExample();
    example.createCriteria().andUsernameEqualTo(username);
    List<User> users = userMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(users)) {
      return CommonResult.failed("Account not existed");
    }
    User user = users.get(0);
    user.setPassword(passwordEncoder.encode(password));
    userMapper.updateByPrimaryKeySelective(user);
    return CommonResult.success(null, "Update password succeeded");
  }

  @Override
  public User getCurrentUser() {
    SecurityContext ctx = SecurityContextHolder.getContext();
    Authentication auth = ctx.getAuthentication();

    if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
      return null;
    }

    UserInfo userInfo = (UserInfo) auth.getPrincipal();
    return userInfo.getUser();
  }
}
