package senntyou.webmonitor.main.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"senntyou.webmonitor.mbg.mapper", "senntyou.webmonitor.main.dao"})
public class MyBatisConfig {
  //
}
