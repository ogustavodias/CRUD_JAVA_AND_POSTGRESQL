package main.db;

import java.sql.SQLException;

import main.domain.Customer;

public interface ICustomerDAO {

  int register(Customer customer) throws SQLException;

  Customer search(String cpf) throws SQLException;

}
