package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.db.CustomerDAO;
import main.db.ICustomerDAO;
import main.domain.Customer;

public class CustomerTest {

  private ICustomerDAO dao = new CustomerDAO();

  public int insertBefore() throws SQLException {
    Customer customer = new Customer("48587815857", "Gustavo Alves Dias");
    return dao.register(customer);
  }

  public int deleteAfter() throws SQLException {
    return dao.deleteCustomer("48587815857");
  }

  @Test
  public void register() throws SQLException {
    Assert.assertTrue(insertBefore() > 0);
  }

  @Test
  public void search() throws SQLException {
    insertBefore();
    Customer customerDB = dao.search("48587815857");
    Assert.assertNotNull(customerDB);
    Assert.assertEquals(customerDB.getCpf(), "48587815857");
    deleteAfter();
  }

  @Test
  public void searchAll() throws SQLException {
    insertBefore();
    List<Customer> customerList = dao.searchAll();
    Assert.assertTrue(customerList.size() > 0);
    deleteAfter();
  }

  @Test
  public void updateCustomer() throws SQLException {
    insertBefore();
    Customer customer = new Customer("48587815857", "TESTE UPDATE");
    Boolean validation = dao.updateCustomer(customer) > 0 && dao.search("48587815857").getNome().equals("TESTE UPDATE");
    Assert.assertTrue(validation);
    deleteAfter();
  }

  @Test
  public void deleteCustomer() throws SQLException {
    insertBefore();
    Boolean validation = deleteAfter() > 0 && dao.search("48587815857") == null;
    Assert.assertTrue(validation);
  }

}
