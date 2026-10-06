package io.github.infobrees.auth.migration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Testcontainers
class FlywayMigrationTest {

  private static final String POSTGRES_IMAGE = "postgres:18-bookworm";

  private static Connection connection() throws SQLException {
    return DriverManager.getConnection(
        postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
  }

  @Container
  @SuppressWarnings("resource")
  static final PostgreSQLContainer postgres =
      new PostgreSQLContainer(POSTGRES_IMAGE)
          .withDatabaseName("capitly_auth_test")
          .withUsername("test")
          .withPassword("test");

  @BeforeAll
  static void migrateSchema() {
    Flyway.configure()
        .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
        .locations("classpath:db/migration")
        .load()
        .migrate();
  }

  private record ExpectedRoleScopes(String roleName, Set<String> scopes) {
    @Override
    public String toString() {
      return roleName;
    }
  }

  private static Stream<ExpectedRoleScopes> expectedRoleScopes() {
    return Stream.of(
        new ExpectedRoleScopes(
            "ADMIN",
            Set.of(
                "health.view",
                "portfolio.read",
                "portfolio.write",
                "portfolio.delete",
                "accounts.read",
                "accounts.write",
                "accounts.delete")),
        new ExpectedRoleScopes(
            "USER",
            Set.of(
                "portfolio.read",
                "portfolio.write",
                "portfolio.delete",
                "accounts.read",
                "accounts.write",
                "accounts.delete")));
  }

  @ParameterizedTest(name = "{index} - {0}")
  @MethodSource("expectedRoleScopes")
  void migrationAssignsExpectedScopesToRole(ExpectedRoleScopes expectedRoleScopes)
      throws SQLException {
    String sql =
        """
        SELECT s.label
        FROM role_scopes rs
        JOIN roles r ON r.role_id = rs.role_id
        JOIN scopes s ON s.scope_id = rs.scope_id
        WHERE r.rolename = ?
        """;
    Set<String> actualScopes = new HashSet<>();
    try (Connection connection = connection();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, expectedRoleScopes.roleName());
      try (ResultSet result = statement.executeQuery()) {
        while (result.next()) {
          actualScopes.add(result.getString("label"));
        }
      }
    }
    assertEquals(expectedRoleScopes.scopes(), actualScopes);
  }
}
