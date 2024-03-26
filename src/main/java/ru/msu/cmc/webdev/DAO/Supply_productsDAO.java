package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Supply_products;
import ru.msu.cmc.webdev.models.Supplies;

import java.util.List;

public interface Supply_productsDAO extends CommonDAO<Supply_products, Long> {
    List<Supply_products> getAllSupplyProducts(Supplies id);
}