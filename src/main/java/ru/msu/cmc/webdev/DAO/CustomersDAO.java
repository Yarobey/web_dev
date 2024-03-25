package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Customers;
import ru.msu.cmc.webdev.models.Customers;

import java.util.List;

public interface CustomersDAO extends CommonDAO<Customers, Long> {
    List<Customers> getCustomers();

    List<Customers> getCustomersByName(String CustomerName);

    List<Customers> getCustomersSortedByNameASC(String CustomerNames);

    List<Customers> getCustomersSortedByNameDESC(String CustomerNames);

    List<Customers> getCustomersSortedByOrderYearsASC(String CustomerNames);

    List<Customers> getCustomersSortedByOrderYearsDESC(String CustomerNames);

    List<Customers> getCustomersSortedByNumberOfOrdersASC(String CustomerNames);

    List<Customers> getCustomersSortedByNumberOfOrdersDESC(String CustomerNames);

    Customers getCustomerByName(String CustomerName);
}