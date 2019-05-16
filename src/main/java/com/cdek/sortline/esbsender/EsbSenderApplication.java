package com.cdek.sortline.esbsender;

import com.cdek.queue.EsbClient;
import com.cdek.queue.register.EsbClientInitializer;
import com.zaxxer.hikari.HikariDataSource;
import org.hc.jp.exceptions.ExceptionBuilder;
import org.hc.jp.utils.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
public class EsbSenderApplication {

  public static void main(String[] args) {
    SpringApplication.run(EsbSenderApplication.class, args);
  }

  @Value("${jdbc.driverClassName}")
  private String jdbcDriverClassName;

  @Value("${jdbc.url}")
  private String jdbcUrl;

  @Value("${jdbc.username}")
  private String jdbcUsername;

  @Value("${jdbc.password}")
  private String jdbcPassword;

  @Bean
  public EsbClient esbClient() {
    return new EsbClient();
  }

  @Bean
  public ExceptionBuilder exceptionBuilder() {
    return new ExceptionBuilder();
  }

  @Bean
  public HttpClient httpClient() {
    return new HttpClient();
  }

  @Bean
  public EsbClientInitializer esbClientInitializer() {
    EsbClientInitializer esbClientInitializer = new EsbClientInitializer();
    esbClientInitializer.setSubscribeToESB(true);

    esbClientInitializer.setPublishObjects(Arrays.asList("obj.sorterincome", "obj.remeasure"));

    return esbClientInitializer;
  }

  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName(jdbcDriverClassName);
    dataSource.setJdbcUrl(jdbcUrl);
    dataSource.setUsername(jdbcUsername);
    dataSource.setPassword(jdbcPassword);
    return dataSource;
  }
}