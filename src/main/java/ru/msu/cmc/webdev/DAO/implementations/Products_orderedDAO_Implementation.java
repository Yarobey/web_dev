package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.Products_orderedDAO;
import ru.msu.cmc.webdev.models.Orders;
import ru.msu.cmc.webdev.models.Products_ordered;

import java.util.List;

@Repository
public class Products_orderedDAO_Implementation extends CommonDAOImplementation<Products_ordered, Long> implements Products_orderedDAO {

    public Products_orderedDAO_Implementation() {
        super(Products_ordered.class);
    }

    @Override
    public List<Products_ordered> getOrderedProducts(Orders id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Products_ordered> query = session.createQuery("FROM Products_ordered WHERE order = :id", Products_ordered.class)
                    .setParameter("id", id);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
}