package ru.msu.cmc.webdev.DAO.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webdev.DAO.Supply_productsDAO;
import ru.msu.cmc.webdev.models.Supplies;
import ru.msu.cmc.webdev.models.Supply_products;

import java.util.List;

@Repository
public class Supply_productsDAO_Implementation extends CommonDAOImplementation<Supply_products, Long> implements Supply_productsDAO {

    public Supply_productsDAO_Implementation() {
        super(Supply_products.class);
    }

    @Override
    public List<Supply_products> getAllSupplyProducts(Supplies id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Supply_products> query = session.createQuery("FROM Supply_products WHERE supply = :id", Supply_products.class)
                    .setParameter("id", id);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
}