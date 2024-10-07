package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import main.db.CustomerDAO;
import main.db.ICustomerDAO;
import main.domain.Customer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {

  private ICustomerDAO dao = new CustomerDAO();

  @Test
  public void register() throws SQLException {
    Customer customer = new Customer("48587815857", "Gustavo Alves Dias");
    Assert.assertTrue(dao.register(customer) > 0);
  }

  @Test
  public void search() throws SQLException {
    Customer customerDB = dao.search("48587815857");
    Assert.assertNotNull(customerDB);
    Assert.assertEquals(customerDB.getCpf(), "48587815857");
  }

  @Test
  public void searchAll() throws SQLException {
    List<Customer> customerList = dao.searchAll();
    Assert.assertTrue(customerList.size() > 0);
  }

  @Test
  public void updateCustomer() throws SQLException {
    Customer customer = new Customer("48587815857", "TESTE UPDATE");
    Boolean validation = dao.updateCustomer(customer) > 0 && dao.search("48587815857").getNome().equals("TESTE UPDATE");
    Assert.assertTrue(validation);
  }

  @Test
  public void deleteCustomer() throws SQLException {
    Boolean validation = dao.deleteCustomer("48587815857") > 0 && dao.search("48587815857") == null;
    Assert.assertTrue(validation);
  }

}
