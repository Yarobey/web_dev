package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Orders;
import ru.msu.cmc.webdev.models.Products_ordered;

import java.util.List;

public interface Products_orderedDAO extends CommonDAO<Products_ordered, Long> {
    List<Products_ordered> getOrderedProducts(Orders id);
}