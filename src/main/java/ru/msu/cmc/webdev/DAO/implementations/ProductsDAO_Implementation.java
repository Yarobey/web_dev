package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.ProductsDAO;
import ru.msu.cmc.webdev.models.Products;

import java.util.List;

@Repository
public class ProductsDAO_Implementation extends CommonDAOImplementation<Products, Long> implements ProductsDAO {

    public ProductsDAO_Implementation() {
        super(Products.class);
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }

    @Override
    public List<Products> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products", Products.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getAllProductsByName(String ProductName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products WHERE name LIKE :gotName", Products.class)
                    .setParameter("gotName", likeExpr(ProductName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getProductsSortedByNamesASC(String ProductNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products " +
                            "WHERE name LIKE :gotName ORDER BY name ASC", Products.class)
                    .setParameter("gotName", likeExpr(ProductNames));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getProductsSortedByNamesDESC(String ProductName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products " +
                            "WHERE name LIKE :gotName ORDER BY name DESC", Products.class)
                    .setParameter("gotName", likeExpr(ProductName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getProductsSortedByExpirationDateASC(String ProductName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products " +
                            "WHERE name LIKE :gotName ORDER BY expiration_date ASC", Products.class)
                    .setParameter("gotName", likeExpr(ProductName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getProductsSortedByExpirationDateDESC(String ProductName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products " +
                            "WHERE name LIKE :gotName ORDER BY expiration_date DESC", Products.class)
                    .setParameter("gotName", likeExpr(ProductName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getProductsSortedByEmptySpaceASC(String ProductName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products " +
                            "WHERE name LIKE :gotName ORDER BY count_warehouse_space_for_product(id) ASC", Products.class)
                    .setParameter("gotName", likeExpr(ProductName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Products> getProductsSortedByEmptySpaceDESC(String ProductName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("FROM Products " +
                            "WHERE name LIKE :gotName ORDER BY count_warehouse_space_for_product(id) DESC", Products.class)
                    .setParameter("gotName", likeExpr(ProductName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Products getSingleProductByName(String ProductName) {
        List<Products> candidates = this.getAllProductsByName(ProductName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }
}