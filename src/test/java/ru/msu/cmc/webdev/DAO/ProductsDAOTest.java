package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Products;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application.properties")
public class ProductsDAOTest {

    @Autowired
    private ProductsDAO productsDAO;

    @Test
    void testSimpleManipulations() {
        List<Products> product_list = (List<Products>) productsDAO.getAll();
        assertEquals(4, product_list.size());

        List<Products> product_list2 = (List<Products>) productsDAO.getAllProducts();
        assertEquals(4, product_list2.size());

        List<Products> all_products_names = productsDAO.getAllProductsByName("Haribo");
        assertEquals(2, all_products_names.size());

        Products products_by_id = productsDAO.getById(3L);
        assertEquals("Haribo Snakes", products_by_id.getName());

        Products unreal_products = productsDAO.getById(999L);
        assertNull(unreal_products);

        Products equal_products = productsDAO.getSingleProductByName("Haribo Snakes");
        assertEquals(products_by_id, equal_products);

        Products unreal_products2 = productsDAO.getSingleProductByName("socks example");
        assertNull(unreal_products2);
    }

    @Test
    void testSortings() {
        List<Products> sortedHaribo = productsDAO.getProductsSortedByNamesASC("Haribo");
        assertEquals(2, sortedHaribo.size());
        assertEquals(2, sortedHaribo.get(0).getId());
        assertEquals(3, sortedHaribo.get(1).getId());

        sortedHaribo = productsDAO.getProductsSortedByNamesDESC("Haribo");
        assertEquals(2, sortedHaribo.size());
        assertEquals(3, sortedHaribo.get(0).getId());
        assertEquals(2, sortedHaribo.get(1).getId());

        List<Products> sortedHariboByExpirationDate = productsDAO.getProductsSortedByExpirationDateASC("Haribo");
        assertEquals(2, sortedHariboByExpirationDate.size());

        sortedHariboByExpirationDate = productsDAO.getProductsSortedByExpirationDateDESC("Haribo");
        assertEquals(2, sortedHariboByExpirationDate.size());

        List<Products> sortedHariboByEmptySpace = productsDAO.getProductsSortedByEmptySpaceASC("Haribo");
        assertEquals(2, sortedHariboByEmptySpace.size());
        assertEquals(2, sortedHariboByEmptySpace.get(0).getId());
        assertEquals(3, sortedHariboByEmptySpace.get(1).getId());

        sortedHariboByEmptySpace = productsDAO.getProductsSortedByEmptySpaceDESC("Haribo");
        assertEquals(2, sortedHariboByEmptySpace.size());
        assertEquals(3, sortedHariboByEmptySpace.get(0).getId());
        assertEquals(2, sortedHariboByEmptySpace.get(1).getId());
    }
}