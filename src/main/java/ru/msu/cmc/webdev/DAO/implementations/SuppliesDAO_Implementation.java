package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.SuppliesDAO;
import ru.msu.cmc.webdev.models.Orders;
import ru.msu.cmc.webdev.models.Supplies;

import java.util.Date;
import java.util.List;

@Repository
public class SuppliesDAO_Implementation extends CommonDAOImplementation<Supplies, Long> implements SuppliesDAO {

    public SuppliesDAO_Implementation() {
        super(Supplies.class);
    }

    @Override
    public List<Supplies> getAllSupplies() {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies", Supplies.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getAllSuppliesBySupplier(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE supplier_name LIKE :name", Supplies.class)
                    .setParameter("name", name);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getAllSuppliesBySupplierLimit5(String supplier) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE supplier_name LIKE :name ORDER BY ship_date desc", Supplies.class)
                    .setParameter("name", likeExpr(supplier));
            return query.getResultList().size() == 0 ? null : ((query.getResultList().size() < 5) ? query.getResultList() : query.getResultList().subList(0, 5));
        }
    }


    @Override
    public List<Supplies> getAllSuppliesByPeriod(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getSuppliesSortedByPeriodASC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY ship_date ASC", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getSuppliesSortedByPeriodDESC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY ship_date DESC", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getSuppliesSortedByNumberOfProductsInPeriodASC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY count_products_in_supply(id) ASC", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getSuppliesSortedByNumberOfProductsInPeriodDESC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY count_products_in_supply(id) DESC", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getSuppliesSortedBySuppliersNameInPeriodASC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY supplier_name ASC", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Supplies> getSuppliesSortedBySuppliersNameInPeriodDESC(Date start, Date end) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supplies> query = session.createQuery("FROM Supplies " +
                            "WHERE ship_date <= :end AND ship_date >= :start ORDER BY supplier_name DESC", Supplies.class)
                    .setParameter("end", end).setParameter("start", start);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}