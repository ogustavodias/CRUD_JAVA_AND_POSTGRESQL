package test;

import java.sql.SQLException;

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

  
}
