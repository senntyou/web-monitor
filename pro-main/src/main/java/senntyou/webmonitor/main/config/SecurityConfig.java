package senntyou.webmonitor.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import senntyou.webmonitor.main.bo.UserInfo;
import senntyou.webmonitor.main.component.JwtAuthenticationTokenFilter;
import senntyou.webmonitor.main.component.RestAuthenticationEntryPoint;
import senntyou.webmonitor.main.component.RestfulAccessDeniedHandler;
import senntyou.webmonitor.main.service.UserService;
import senntyou.webmonitor.mbg.model.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private UserService userService;
  @Autowired private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
  @Autowired private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf() // JWT does not need csrf
        .disable()
        .sessionManagement() // JWT does not need session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(
            HttpMethod.GET,
            "/",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/swagger/**",
            "/**/v2/api-docs",
            "/**/*.css",
            "/**/*.js",
            "/**/*.png",
            "/**/*.ico",
            "/webjars/springfox-swagger-ui/**",
            "/actuator/**",
            "/druid/**",
            "/build/**")
        .permitAll()
        .antMatchers(
            "/api/sdk/**",
            "/admin",
            "/admin/account/login",
            "/admin/account/register",
            "/admin/account/updatePassword")
        .permitAll()
        // Every cross origin request will make a OPTIONS request before its real request
        .antMatchers(HttpMethod.OPTIONS)
        .permitAll()
        .anyRequest()
        .authenticated();
    // Disable cache
    httpSecurity.headers().cacheControl();
    // Add JWT filter
    httpSecurity.addFilterBefore(
        jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    // Add custom exception handler
    httpSecurity
        .exceptionHandling()
        .accessDeniedHandler(restfulAccessDeniedHandler)
        .authenticationEntryPoint(restAuthenticationEntryPoint);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    // Get logged-in user information
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user != null) {
          return new UserInfo(user);
        }
        throw new UsernameNotFoundException("Username or password is not correct");
      }
    };
  }
}
