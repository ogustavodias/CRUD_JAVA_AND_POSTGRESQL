package main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import main.domain.Product;

public class ProductDAO implements IProductDAO {

  private Connection connection;
  private PreparedStatement stm;
  private ResultSet rs;

  @Override
  public int register(Product product) throws SQLException {
    int affectedRows = 0;

    try {
      String sql = "INSERT INTO product(codigo, nome, price) values(?, ?, ?)";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, product.getCodigo());
      this.stm.setString(2, product.getNome());
      this.stm.setBigDecimal(3, product.getPreco());
      affectedRows = this.stm.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return affectedRows;
  }

  @Override
  public Product search(String codigo) throws SQLException {
    Product product = null;

    try {
      String sql = "SELECT * FROM product WHERE codigo = ?";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, codigo);
      this.rs = this.stm.executeQuery();

      if (rs.next()) {
        String nome = this.rs.getString("nome");
        BigDecimal price = this.rs.getBigDecimal("price");
        product = new Product(codigo, nome, price);
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return product;
  }

  @Override
  public List<Product> searchAll() throws SQLException {
    List<Product> productList = new ArrayList<>();

    try {
      String sql = "SELECT * FROM product";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.rs = this.stm.executeQuery();

      while (rs.next()) {
        Product productDB = new Product(this.rs.getString("codigo"), this.rs.getString("nome"),
            this.rs.getBigDecimal("price"));
        productList.add(productDB);
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return productList;
  }

  @Override
  public int updateProduct(Product product) throws SQLException {
    int affectedRows = 0;

    try {
      String sql = "UPDATE product SET nome = ? WHERE codigo = ?";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, product.getNome());
      this.stm.setString(2, product.getCodigo());
      affectedRows = this.stm.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
      closeConnection();
    }

    return affectedRows;
  }

  @Override
  public int deleteProduct(String codigo) throws SQLException {
    int affectedRows = 0;

    try {
      String sql = "DELETE FROM product WHERE codigo = ?";
      this.connection = PostgreSQLConnection.getConnection();
      this.stm = this.connection.prepareStatement(sql);
      this.stm.setString(1, codigo);
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
