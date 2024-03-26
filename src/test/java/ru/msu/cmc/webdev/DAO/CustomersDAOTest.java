package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Customers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application.properties")
public class CustomersDAOTest {

    @Autowired
    private CustomersDAO customersDAO;

    @Test
    void testSimpleManipulations() {
        List<Customers> customer_list = (List<Customers>) customersDAO.getAll();
        assertEquals(3, customer_list.size());

        List<Customers> customer_list2 = (List<Customers>) customersDAO.getAllCustomers();
        assertEquals(3, customer_list2.size());

        List<Customers> all_customers_by_name = customersDAO.getAllCustomersByName("Tim Cook");
        assertEquals(1, all_customers_by_name.size());
        assertEquals("Tim Cook", all_customers_by_name.get(0).getName());

        Customers customers_by_id = customersDAO.getById(3L);
        assertEquals("Jensen Huang", customers_by_id.getName());

        Customers unreal_customers = customersDAO.getById(999L);
        assertNull(unreal_customers);

        Customers equal_customers = customersDAO.getCustomerByName("Jensen Huang");
        assertEquals(customers_by_id, equal_customers);

        Customers unreal_customers2 = customersDAO.getCustomerByName("Penny Holland");
        assertNull(unreal_customers2);
    }

    @Test
    void testSortings() {
        List<Customers> sortedCooks = customersDAO.getCustomersSortedByNameASC("Cook");
        assertEquals(2, sortedCooks.size());
        assertEquals(1, sortedCooks.get(0).getId());
        assertEquals(2, sortedCooks.get(1).getId());

        sortedCooks = customersDAO.getCustomersSortedByNameDESC("Cook");
        assertEquals(2, sortedCooks.size());
        assertEquals(2, sortedCooks.get(0).getId());
        assertEquals(1, sortedCooks.get(1).getId());

        List<Customers> sortedCooksByOrderYears = customersDAO.getCustomersSortedByOrderPerYearASC("Cook");
        assertEquals(2, sortedCooksByOrderYears.size());
        assertEquals(1, sortedCooksByOrderYears.get(0).getId());
        assertEquals(2, sortedCooksByOrderYears.get(1).getId());

        sortedCooksByOrderYears = customersDAO.getCustomersSortedByOrderPerYearDESC("Cook");
        assertEquals(2, sortedCooksByOrderYears.size());
        assertEquals(1, sortedCooksByOrderYears.get(0).getId());
        assertEquals(2, sortedCooksByOrderYears.get(1).getId());

        List<Customers> sortedCooksByNumberOfOrders = customersDAO.getCustomersSortedByNumberOfOrdersASC("Cook");
        assertEquals(2, sortedCooksByNumberOfOrders.size());
        assertEquals(2, sortedCooksByNumberOfOrders.get(0).getId());
        assertEquals(1, sortedCooksByNumberOfOrders.get(1).getId());

        sortedCooksByNumberOfOrders = customersDAO.getCustomersSortedByNumberOfOrdersDESC("Cook");
        assertEquals(2, sortedCooksByNumberOfOrders.size());
        assertEquals(1, sortedCooksByNumberOfOrders.get(0).getId());
        assertEquals(2, sortedCooksByNumberOfOrders.get(1).getId());
    }

}