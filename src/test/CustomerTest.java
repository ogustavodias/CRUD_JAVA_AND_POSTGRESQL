package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.db.PostgreSQLConnection;

public class CustomerTest {

  private Connection connection;

  @Before
  public void start() {
    this.connection = PostgreSQLConnection.getConnection();
  }

  @Test
  public void register() {

  }

  @After
  public void end() throws SQLException {
    this.connection.close();
  }

}
