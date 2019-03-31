package tech.form3.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring Boot application main runner.
 */
@SpringBootApplication
@EnableAsync (proxyTargetClass = true)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}