package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Products_ordered;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application.properties")
public class Products_orderedDAOTest {

    @Autowired
    private Products_orderedDAO Products_orderedDAO;
    @Autowired
    private OrdersDAO ordersDAO;

    @Test
    void testSimpleManipulations() {
        List<Products_ordered> products_ordered_list = (List<Products_ordered>) Products_orderedDAO.getAll();
        assertEquals(4, products_ordered_list.size());

        List<Products_ordered> products_ordered_list2 = (List<Products_ordered>) Products_orderedDAO.getOrderedProducts(ordersDAO.getById(7L));
        assertEquals(2, products_ordered_list2.size());
    }
}