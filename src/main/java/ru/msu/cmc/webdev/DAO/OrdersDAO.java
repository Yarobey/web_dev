package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Orders;

import java.util.List;

public interface OrdersDAO extends CommonDAO<Orders, Long> {
    List<Orders> getAllOrders();

    List<Orders> getAllOrdersByCustomer(String id);

    List<Orders> getAllOrdersByCustomerLimit5(String id);

    List<Orders> getAllOrdersByPeriod(java.util.Date start, java.util.Date end);

    List<Orders> getOrdersSortedByPeriodASC(java.util.Date start, java.util.Date end);

    List<Orders> getOrdersSortedByPeriodDESC(java.util.Date start, java.util.Date end);

    List<Orders> getOrdersSortedByNumberOfProductsInPeriodASC(java.util.Date start, java.util.Date end);

    List<Orders> getOrdersSortedByNumberOfProductsInPeriodDESC(java.util.Date start, java.util.Date end);

    List<Orders> getOrdersSortedByCustomerNameInPeriodASC(java.util.Date start, java.util.Date end);

    List<Orders> getOrdersSortedByCustomerNameInPeriodDESC(java.util.Date start, java.util.Date end);

}