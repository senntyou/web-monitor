package senntyou.webmonitor.main.service;

import org.springframework.transaction.annotation.Transactional;
import senntyou.webmonitor.common.CommonResult;
import senntyou.webmonitor.mbg.model.User;

public interface UserService {
  /** Get user by username */
  User getByUsername(String username);

  /** Get user by id */
  User getById(int id);

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
  User getCurrentUser();
}
