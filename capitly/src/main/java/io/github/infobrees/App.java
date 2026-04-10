package io.github.infobrees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main application class for Capitly. */
@SpringBootApplication
public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    logger.info("✅ Starting Capitly...");
    SpringApplication.run(App.class, args);
  }
}
