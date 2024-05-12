package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.Warehouse_spacesDAO;
import ru.msu.cmc.webdev.models.Products;
import ru.msu.cmc.webdev.models.Warehouse_spaces;

import java.util.List;

@Repository
public class Warehouse_spacesDAO_Implementation extends CommonDAOImplementation<Warehouse_spaces, Long> implements Warehouse_spacesDAO {

    public Warehouse_spacesDAO_Implementation() {
        super(Warehouse_spaces.class);
    }

    @Override
    public List<Warehouse_spaces> getNotEmptySpacesByType(String ptype) {
        try (Session session = sessionFactory.openSession()) {
            Query<Warehouse_spaces> query = session.createQuery("FROM Warehouse_spaces " +
                            " WHERE ((empty_space = false) and (ptype Like :ptype))", Warehouse_spaces.class)
                    .setParameter("ptype", likeExpr(ptype));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
    @Override
    public List<Warehouse_spaces> getProductPlaces(Products id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Warehouse_spaces> query = session.createQuery("FROM Warehouse_spaces " +
                            "WHERE product = :id", Warehouse_spaces.class)
                    .setParameter("id", id);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}