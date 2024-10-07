package test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.db.IProductDAO;
import main.db.ProductDAO;
import main.domain.Product;

public class ProductTest {

  private IProductDAO dao = new ProductDAO();

  public int insertBefore() throws SQLException {
    Product product = new Product("1", "Iphone 13", new BigDecimal(1400.99));
    return dao.register(product);
  }

  public int deleteAfter() throws SQLException {
    return dao.deleteProduct("1");
  }

  @Test
  public void register() throws SQLException {
    Assert.assertTrue(insertBefore() > 0);
  }

  @Test
  public void search() throws SQLException {
    insertBefore();
    Product productDB = dao.search("1");
    Assert.assertNotNull(productDB);
    Assert.assertEquals(productDB.getCodigo(), "1");
    deleteAfter();
  }

  @Test
  public void searchAll() throws SQLException {
    insertBefore();
    List<Product> productList = dao.searchAll();
    Assert.assertTrue(productList.size() > 0);
    deleteAfter();
  }

  @Test
  public void updateProduct() throws SQLException {
    insertBefore();
    Product product = new Product("1", "TESTE UPDATE", new BigDecimal(1400.99));
    Boolean validation = dao.updateProduct(product) > 0 && dao.search("1").getNome().equals("TESTE UPDATE");
    Assert.assertTrue(validation);
    deleteAfter();
  }

  @Test
  public void deleteProduct() throws SQLException {
    insertBefore();
    Boolean validation = deleteAfter() > 0 && dao.search("1") == null;
    Assert.assertTrue(validation);
  }

}
