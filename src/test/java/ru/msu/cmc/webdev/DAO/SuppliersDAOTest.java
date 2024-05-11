package ru.msu.cmc.webdev.DAO;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webdev.models.Suppliers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application.properties")
public class SuppliersDAOTest {

    @Autowired
    private SuppliersDAO suppliersDAO;

    @Test
    void testSimpleManipulations() {
        List<Suppliers> suppliers_list = (List<Suppliers>) suppliersDAO.getAll();
        assertEquals(3, suppliers_list.size());
        
        List<Suppliers> suppliers_list2 = (List<Suppliers>) suppliersDAO.getAllSuppliers();
        assertEquals(3, suppliers_list2.size());
        
        List<Suppliers> all_suppliers_names = suppliersDAO.getAllSuppliersByName("Haribo Inc");
        assertEquals(1, all_suppliers_names.size());
        assertEquals("Haribo Inc", all_suppliers_names.get(0).getName());
        
        Suppliers suppliers_by_id = suppliersDAO.getById(3L);
        assertEquals("Lego", suppliers_by_id.getName());
        
        Suppliers unreal_suppliers = suppliersDAO.getById(999L);
        assertNull(unreal_suppliers);
        
        Suppliers equal_suppliers = suppliersDAO.getSupplierByName("Lego");
        assertEquals(suppliers_by_id, equal_suppliers);
        
        Suppliers unreal_suppliers2 = suppliersDAO.getSupplierByName("Penny Holland");
        assertNull(unreal_suppliers2);
    }

    @Test
    void testSortings() {
        List<Suppliers> sorted_incorp = suppliersDAO.getSuppliersSortedByNameASC("Inc");
        assertEquals(2, sorted_incorp.size());
        assertEquals(1, sorted_incorp.get(0).getId());
        assertEquals(2, sorted_incorp.get(1).getId());
        
        sorted_incorp = suppliersDAO.getSuppliersSortedByNameDESC("Inc");
        assertEquals(2, sorted_incorp.size());
        assertEquals(2, sorted_incorp.get(0).getId());
        assertEquals(1, sorted_incorp.get(1).getId());
        
        List<Suppliers> sorted_incorp_by_shipdate = suppliersDAO.getSuppliersSortedBySuppliesPerYearASC("Inc");
        assertEquals(2, sorted_incorp_by_shipdate.size());
        assertEquals(1, sorted_incorp_by_shipdate.get(0).getId());
        assertEquals(2, sorted_incorp_by_shipdate.get(1).getId());
        
        sorted_incorp_by_shipdate = suppliersDAO.getSuppliersSortedBySuppliesPerYearDESC("Inc");
        assertEquals(2, sorted_incorp_by_shipdate.size());
        assertEquals(1, sorted_incorp_by_shipdate.get(0).getId());
        assertEquals(2, sorted_incorp_by_shipdate.get(1).getId());
        
        List<Suppliers> sorted_incorp_by_number_of_supplies = suppliersDAO.getSuppliersSortedByNumberOfSuppliesASC("Inc");
        assertEquals(2, sorted_incorp_by_number_of_supplies.size());
        assertEquals(2, sorted_incorp_by_number_of_supplies.get(0).getId());
        assertEquals(1, sorted_incorp_by_number_of_supplies.get(1).getId());
        
        sorted_incorp_by_number_of_supplies = suppliersDAO.getSuppliersSortedByNumberOfSuppliesDESC("Inc");
        assertEquals(2, sorted_incorp_by_number_of_supplies.size());
        assertEquals(1, sorted_incorp_by_number_of_supplies.get(0).getId());
        assertEquals(2, sorted_incorp_by_number_of_supplies.get(1).getId());
    }
}