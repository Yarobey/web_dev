package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Supply_products;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application.properties")
public class Supply_productsDAOTest {

    @Autowired
    private Supply_productsDAO supply_productsDAO;
    @Autowired
    private SuppliesDAO suppliesDAO;

    @Test
    void testSimpleManipulations() {
        List<Supply_products> supply_products_list = (List<Supply_products>) supply_productsDAO.getAll();
        assertEquals(4, supply_products_list.size());

        List<Supply_products> supply_products_list2 = (List<Supply_products>) supply_productsDAO.getAllSupplyProducts(suppliesDAO.getById(7L));
        assertEquals(2, supply_products_list2.size());
    }
}