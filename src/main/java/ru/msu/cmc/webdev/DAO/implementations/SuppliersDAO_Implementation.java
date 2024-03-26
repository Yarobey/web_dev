package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.SuppliersDAO;
import ru.msu.cmc.webdev.models.Suppliers;

import java.util.List;

@Repository
public class SuppliersDAO_Implementation extends CommonDAOImplementation<Suppliers, Long> implements SuppliersDAO {

    public SuppliersDAO_Implementation() {
        super(Suppliers.class);
    }

    @Override
    public List<Suppliers> getAllSuppliers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers", Suppliers.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getAllSuppliersByName(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getSuppliersSortedByNameASC(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName " +
                            "ORDER BY name ASC", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getSuppliersSortedByNameDESC(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName " +
                            "ORDER BY name DESC", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getSuppliersSortedBySupplisPerYearASC(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName " +
                            "ORDER BY count_suppliers_supplies_by_years(id) ASC", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getSuppliersSortedBySupplisPerYearDESC(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName " +
                            "ORDER BY count_suppliers_supplies_by_years(id) DESC", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getSuppliersSortedByNumberOfSuppliesASC(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName " +
                            "ORDER BY count_suppliers_supplies(id) ASC", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Suppliers> getSuppliersSortedByNumberOfSuppliesDESC(String SupplierName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Suppliers> query = session.createQuery("FROM Suppliers WHERE name LIKE :gotName " +
                            "ORDER BY count_suppliers_supplies(id) DESC", Suppliers.class)
                    .setParameter("gotName", likeExpr(SupplierName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Suppliers getSupplierByName(String SupplierName) {
        List<Suppliers> candidates = this.getAllSuppliersByName(SupplierName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}