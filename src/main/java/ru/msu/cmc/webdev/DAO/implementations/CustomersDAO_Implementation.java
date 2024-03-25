package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.CustomersDAO;
import ru.msu.cmc.webdev.models.Customers;

import java.util.List;

@Repository
public class CustomersDAO_Implementation extends CommonDAOImplementation<Customers, Long> implements CustomersDAO {

    public CustomersDAO_Implementation() {
        super(Customers.class);
    }

    @Override
    public List<Customers> getCustomers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers", Customers.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersByName(String CustomerName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersSortedByNameASC(String CustomerNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName " +
                            "ORDER BY name ASC", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersSortedByNameDESC(String CustomerNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName " +
                            "ORDER BY name DESC", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersSortedByOrderYearsASC(String CustomerNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName " +
                            "ORDER BY count_customers_orders_by_years(id) ASC", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersSortedByOrderYearsDESC(String CustomerNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName " +
                            "ORDER BY count_customers_orders_by_years(id) DESC", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersSortedByNumberOfOrdersASC(String CustomerNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName " +
                            "ORDER BY count_customers_orders(id) ASC", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Customers> getCustomersSortedByNumberOfOrdersDESC(String CustomerNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customers> query = session.createQuery("FROM Customers WHERE name LIKE :gotName " +
                            "ORDER BY count_customers_orders(id) DESC", Customers.class)
                    .setParameter("gotName", likeExpr(CustomerNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Customers getCustomerByName(String CustomerName) {
        List<Customers> candidates = this.getCustomersByName(CustomerName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}