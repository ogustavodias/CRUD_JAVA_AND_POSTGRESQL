package main.db;

import java.sql.SQLException;
import java.util.List;

import main.domain.Customer;

public interface ICustomerDAO {

  int register(Customer customer) throws SQLException;

  Customer search(String cpf) throws SQLException;

  List<Customer> searchAll() throws SQLException;

  int updateCustomer(Customer customer) throws SQLException;

  int deleteCustomer(String cpf) throws SQLException;
}
