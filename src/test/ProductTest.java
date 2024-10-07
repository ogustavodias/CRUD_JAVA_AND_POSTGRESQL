package test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import main.db.IProductDAO;
import main.db.ProductDAO;
import main.domain.Product;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest {

  private IProductDAO dao = new ProductDAO();

  @Test
  public void register() throws SQLException {
    Product product = new Product("1", "Iphone 13", new BigDecimal(1400.99));
    Assert.assertTrue(dao.register(product) > 0);
  }

  @Test
  public void search() throws SQLException {
    Product productDB = dao.search("1");
    Assert.assertNotNull(productDB);
    Assert.assertEquals(productDB.getCodigo(), "1");
  }

  @Test
  public void searchAll() throws SQLException {
    List<Product> productList = dao.searchAll();
    Assert.assertTrue(productList.size() > 0);
  }

  @Test
  public void updateProduct() throws SQLException {
    Product product = new Product("1", "TESTE UPDATE", new BigDecimal(1400.99));
    Boolean validation = dao.updateProduct(product) > 0 && dao.search("1").getNome().equals("TESTE UPDATE");
    Assert.assertTrue(validation);
  }

  @Test
  public void deleteProduct() throws SQLException {
    Boolean validation = dao.deleteProduct("1") > 0 && dao.search("1") == null;
    Assert.assertTrue(validation);
  }

}
