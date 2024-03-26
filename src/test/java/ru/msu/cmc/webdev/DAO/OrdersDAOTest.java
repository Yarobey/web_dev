package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Orders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class OrdersDAOTest {

    @Autowired
    private OrdersDAO ordersDAO;

    @Test
    void testSimpleManipulations() {
        List<Orders> order_list = (List<Orders>) ordersDAO.getAll();
        assertEquals(11, order_list.size());

        List<Orders> order_list2 = (List<Orders>) ordersDAO.getAllOrders();
        assertEquals(11, order_list2.size());

        List<Orders> all_customers_orders= ordersDAO.getAllOrdersByCustomer("Tim Cook");
        assertEquals(5, all_customers_orders.size());

        java.util.Date tmp1 = new java.util.Date(114, 00, 01);
        java.util.Date tmp2 = new java.util.Date(115, 00, 01);
        List<Orders> ordersin2014 = ordersDAO.getAllOrdersByPeriod(tmp1, tmp2);
        assertEquals(4, ordersin2014.size());
    }

    @Test
    void testSortings() {
        java.util.Date tmp1 = new java.util.Date(114, 00, 01);
        java.util.Date tmp2 = new java.util.Date(115, 00, 01);
        List<Orders> ordersin2014sorted = ordersDAO.getOrdersSortedByPeriodASC(tmp1, tmp2);
        assertEquals(4, ordersin2014sorted.size());
        assertEquals(7, ordersin2014sorted.get(0).getId());

        ordersin2014sorted = ordersDAO.getOrdersSortedByPeriodDESC(tmp1, tmp2);
        assertEquals(4, ordersin2014sorted.size());
        assertEquals(7, ordersin2014sorted.get(3).getId());

        ordersin2014sorted = ordersDAO.getOrdersSortedByNumberOfProductsInPeriodASC(tmp1, tmp2);
        assertEquals(4, ordersin2014sorted.size());
        assertEquals(7, ordersin2014sorted.get(3).getId());

        ordersin2014sorted = ordersDAO.getOrdersSortedByNumberOfProductsInPeriodDESC(tmp1, tmp2);
        assertEquals(4, ordersin2014sorted.size());
        assertEquals(7, ordersin2014sorted.get(0).getId());

        ordersin2014sorted = ordersDAO.getOrdersSortedByCustomerNameInPeriodASC(tmp1, tmp2);
        assertEquals(4, ordersin2014sorted.size());
        assertEquals("Jensen Huang", ordersin2014sorted.get(0).getCustomer_name());

        ordersin2014sorted = ordersDAO.getOrdersSortedByCustomerNameInPeriodDESC(tmp1, tmp2);
        assertEquals(4, ordersin2014sorted.size());
        assertEquals("Jensen Huang", ordersin2014sorted.get(3).getCustomer_name());
    }
}