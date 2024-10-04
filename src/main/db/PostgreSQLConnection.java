package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {

  private static final String url = "jdbc:postgresql://localhost:5432/firstcrud";
  private static final String user = "postgres";
  private static final String password = "ha159357";

  private static Connection connection = null;

  private PostgreSQLConnection() {

  }

  public static Connection getConnection() throws SQLException {

    try {
      if (connection == null || connection.isClosed()) {
        connection = DriverManager.getConnection(url, user, password);
      }
    } catch (SQLException e) {
      throw e;
    }

    return connection;
  }

}
