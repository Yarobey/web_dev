package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Products;

import java.util.List;

public interface ProductsDAO extends CommonDAO<Products, Long> {
    List<Products> getAllProducts();

    List<Products> getAllProductsByName(String ProductName);

    List<Products> getProductsSortedByNamesASC(String ProductName);

    List<Products> getProductsSortedByNamesDESC(String ProductName);

    List<Products> getProductsSortedByExpirationDateASC(String ProductName);

    List<Products> getProductsSortedByExpirationDateDESC(String ProductName);

    List<Products> getProductsSortedByEmptySpaceASC(String ProductName);

    List<Products> getProductsSortedByEmptySpaceDESC(String ProductName);

    Products getSingleProductByName(String ProductName);
}