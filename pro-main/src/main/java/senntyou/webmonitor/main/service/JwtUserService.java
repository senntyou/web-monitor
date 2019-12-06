package senntyou.webmonitor.main.service;

import org.springframework.transaction.annotation.Transactional;
import senntyou.webmonitor.common.CommonResult;
import senntyou.webmonitor.model.JwtUser;

public interface JwtUserService {
  /** Get user by username */
  JwtUser getByUsername(String username);

  /** Get user by id */
  JwtUser getById(long id);

  /** Sign up */
  CommonResult register(String username, String password);

  /**
   * Login
   *
   * @param username Username
   * @param password Password
   * @return JWT token
   */
  String login(String username, String password);

  String refreshToken(String oldToken);

  /** Update password */
  @Transactional
  CommonResult updatePassword(String username, String password);

  /** Get current user information */
  JwtUser getCurrentUser();
}
