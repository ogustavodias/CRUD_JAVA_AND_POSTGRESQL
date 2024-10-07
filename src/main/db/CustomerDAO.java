package main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.domain.Customer;

public class CustomerDAO implements ICustomerDAO {

  private Connection connection;
  private PreparedStatement stm;
  private ResultSet rs;

  @Override
  public int register(Customer customer) throws SQLException {
    int affectedRows = 0;

    try {
      String sql = "INSERT INTO customer(cpf, nome) values(?, ?)";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, "48587815857");
      this.stm.setString(2, "Augusto Oliveira");
      affectedRows = this.stm.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return affectedRows;
  }

  @Override
  public Customer search(String cpf) throws SQLException {
    Customer customer = null;

    try {
      String sql = "SELECT * FROM customer WHERE cpf = ?";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, cpf);
      this.rs = this.stm.executeQuery();

      if (rs.next()) {
        String nome = this.rs.getString("nome");
        customer = new Customer(cpf, nome);
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return customer;
  }

  @Override
  public List<Customer> searchAll() throws SQLException {
    List<Customer> customerList = new ArrayList<>();

    try {
      String sql = "SELECT * FROM customer";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.rs = this.stm.executeQuery();

      while (rs.next()) {
        Customer customerDB = new Customer(this.rs.getString("cpf"), this.rs.getString("nome"));
        customerList.add(customerDB);
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return customerList;
  }

  @Override
  public int updateCustomer(Customer customer) throws SQLException {
    int affectedRows = 0;

    try {
      String sql = "UPDATE customer SET nome = ? WHERE cpf = ?";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, customer.getNome());
      this.stm.setString(2, customer.getCpf());
      affectedRows = this.stm.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return affectedRows;
  }

  @Override
  public int deleteCustomer(String cpf) throws SQLException {
    int affectedRows = 0;

    try {
      String sql = "DELETE FROM customer WHERE cpf = ?";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, cpf);
      affectedRows = this.stm.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return affectedRows;
  }

  public void closeConnection() throws SQLException {
    if (this.connection != null && !this.connection.isClosed())
      this.connection.close();
    if (this.stm != null && !this.stm.isClosed())
      this.stm.close();
    if (this.rs != null && !this.rs.isClosed())
      this.rs.close();
  }

}
