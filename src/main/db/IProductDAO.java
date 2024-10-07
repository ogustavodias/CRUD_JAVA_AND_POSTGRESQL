package main.db;

import java.sql.SQLException;
import java.util.List;

import main.domain.Product;

public interface IProductDAO {

  int register(Product product) throws SQLException;

  Product search(String codigo) throws SQLException;

  List<Product> searchAll() throws SQLException;

  int updateProduct(Product product) throws SQLException;

  int deleteProduct(String codigo) throws SQLException;
}
