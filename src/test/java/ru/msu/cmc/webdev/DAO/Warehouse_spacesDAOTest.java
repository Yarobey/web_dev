package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Warehouse_spaces;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application.properties")
public class Warehouse_spacesDAOTest {

    @Autowired
    private Warehouse_spacesDAO Warehouse_spacesDAO;
    @Autowired
    private ProductsDAO productsDAO;

    @Test
    void testSimpleManipulations() {
        List<Warehouse_spaces> PlacesByType = Warehouse_spacesDAO.getNotEmptySpacesByType("smth");
        assertNull(PlacesByType);

        List<Warehouse_spaces> PlacesForGood = (List<Warehouse_spaces>) Warehouse_spacesDAO.getProductPlaces(productsDAO.getById(3L));
        assertEquals(2, PlacesForGood.size());
    }
}