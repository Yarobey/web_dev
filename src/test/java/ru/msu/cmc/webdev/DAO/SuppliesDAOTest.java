package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Supplies;

import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class SuppliesDAOTest {

    @Autowired
    private SuppliesDAO suppliesDAO;

    @Test
    void testSimpleManipulations() {
        List<Supplies> supplies_list = (List<Supplies>) suppliesDAO.getAll();
        assertEquals(11, supplies_list.size());
        
        List<Supplies> supplies_list2 = (List<Supplies>) suppliesDAO.getAllSupplies();
        assertEquals(11, supplies_list2.size());
        
        List<Supplies> all_suppliers_supplies = suppliesDAO.getAllSuppliesBySupplier("Apple Inc");
        assertEquals(5, all_suppliers_supplies.size());
        
        java.util.Date tmp1 = new java.util.Date(114, 00, 01);
        java.util.Date tmp2 = new java.util.Date(115, 00, 01);
        List<Supplies> supplies_in_2014 = suppliesDAO.getAllSuppliesByPeriod(tmp1, tmp2);
        assertEquals(4, supplies_in_2014.size());
    }

    @Test
    void testSortings() {
        java.util.Date tmp1 = new java.util.Date(114, 00, 01);
        java.util.Date tmp2 = new java.util.Date(115, 00, 01);
        List<Supplies> supplies_in_2014_sorted = suppliesDAO.getSuppliesSortedByPeriodASC(tmp1, tmp2);
        assertEquals(4, supplies_in_2014_sorted.size());
        assertEquals(7, supplies_in_2014_sorted.get(0).getId());
        
        supplies_in_2014_sorted = suppliesDAO.getSuppliesSortedByPeriodDESC(tmp1, tmp2);
        assertEquals(4, supplies_in_2014_sorted.size());
        assertEquals(7, supplies_in_2014_sorted.get(3).getId());
        
        supplies_in_2014_sorted = suppliesDAO.getSuppliesSortedByNumberOfProductsInPeriodASC(tmp1, tmp2);
        assertEquals(4, supplies_in_2014_sorted.size());
        assertEquals(7, supplies_in_2014_sorted.get(3).getId());
        
        supplies_in_2014_sorted = suppliesDAO.getSuppliesSortedByNumberOfProductsInPeriodDESC(tmp1, tmp2);
        assertEquals(4, supplies_in_2014_sorted.size());
        assertEquals(7, supplies_in_2014_sorted.get(0).getId());
        
        supplies_in_2014_sorted = suppliesDAO.getSuppliesSortedBySuppliersNameInPeriodASC(tmp1, tmp2);
        assertEquals(4, supplies_in_2014_sorted.size());
        assertEquals("Apple Inc", supplies_in_2014_sorted.get(0).getSupplier_name());
        
        supplies_in_2014_sorted = suppliesDAO.getSuppliesSortedBySuppliersNameInPeriodDESC(tmp1, tmp2);
        assertEquals(4, supplies_in_2014_sorted.size());
        assertEquals("Apple Inc", supplies_in_2014_sorted.get(3).getSupplier_name());
    }
}