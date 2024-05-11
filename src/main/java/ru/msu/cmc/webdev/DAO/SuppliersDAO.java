package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Suppliers;

import java.util.List;

public interface SuppliersDAO extends CommonDAO<Suppliers, Long> {
    List<Suppliers> getAllSuppliers();

    List<Suppliers> getAllSuppliersByName(String SupplierName);

    List<Suppliers> getSuppliersSortedByNameASC(String SupplierName);

    List<Suppliers> getSuppliersSortedByNameDESC(String SupplierName);

    List<Suppliers> getSuppliersSortedBySuppliesPerYearASC(String SupplierName);

    List<Suppliers> getSuppliersSortedBySuppliesPerYearDESC(String SupplierName);

    List<Suppliers> getSuppliersSortedByNumberOfSuppliesASC(String SupplierName);

    List<Suppliers> getSuppliersSortedByNumberOfSuppliesDESC(String SupplierName);

    Suppliers getSupplierByName(String SuppliersName);
}