package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.OrdersDAO;
import ru.msu.cmc.webdev.models.Orders;

import java.util.Date;
import java.util.List;

@Repository
public class OrdersDAO_Implementation extends CommonDAOImplementation<Orders, Long> implements OrdersDAO {

    public OrdersDAO_Implementation() {
        super(Orders.class);
    }

    @Override
    public List<Orders> getAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders", Orders.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }


    @Override
    public List<Orders> getAllOrdersByCustomer(String customer) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE customer_name LIKE :name", Orders.class)
                    .setParameter("name", likeExpr(customer));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getAllOrdersByCustomerLimit5(String customer) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE customer_name LIKE :name LIMIT 5", Orders.class)
                    .setParameter("name", customer);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getAllOrdersByPeriod(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE (ship_date >= :start) AND (ship_date <= :end) ", Orders.class)
                    .setParameter("start", start).setParameter("end", end);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getOrdersSortedByPeriodASC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY ship_date ASC", Orders.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getOrdersSortedByPeriodDESC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY ship_date DESC", Orders.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getOrdersSortedByNumberOfProductsInPeriodASC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY count_orders_products(id) ASC", Orders.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getOrdersSortedByNumberOfProductsInPeriodDESC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY count_orders_products(id) DESC", Orders.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getOrdersSortedByCustomerNameInPeriodASC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY customer_name ASC", Orders.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Orders> getOrdersSortedByCustomerNameInPeriodDESC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("FROM Orders " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY customer_name DESC", Orders.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}