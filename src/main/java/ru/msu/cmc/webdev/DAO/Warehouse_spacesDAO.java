package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Products;
import ru.msu.cmc.webdev.models.Warehouse_spaces;

import java.util.List;

public interface Warehouse_spacesDAO extends CommonDAO<Warehouse_spaces, Long> {
    List<Warehouse_spaces> getNotEmptySpacesByType(String type);
    public List<Warehouse_spaces> getProductPlaces(Products id);
}