package io.github.infobrees.auth;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main application class for capitly.auth */
@SpringBootApplication
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("✅ Starting capitly.auth ...");
        var context = SpringApplication.run(App.class, args);

        var ds = context.getBean(DataSource.class);

        try (Connection c = ds.getConnection()) {
            System.out.println("DB OK: " + c.getMetaData().getURL());
        } catch (Exception e) {
            System.err.println("DB FAILED: " + e.getMessage());
        }
    }
}
