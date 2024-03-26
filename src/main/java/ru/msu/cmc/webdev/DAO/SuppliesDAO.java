package ru.msu.cmc.webdev.DAO;

import ru.msu.cmc.webdev.models.Supplies;

import java.util.List;

public interface SuppliesDAO extends CommonDAO<Supplies, Long> {
    List<Supplies> getAllSupplies();

    List<Supplies> getAllSuppliesBySupplier(String name);

    List<Supplies> getAllSuppliesByPeriod(java.util.Date start, java.util.Date end);

    List<Supplies> getSuppliesSortedByPeriodASC(java.util.Date start, java.util.Date end);

    List<Supplies> getSuppliesSortedByPeriodDESC(java.util.Date start, java.util.Date end);

    List<Supplies> getSuppliesSortedByNumberOfProductsInPeriodASC(java.util.Date start, java.util.Date end);

    List<Supplies> getSuppliesSortedByNumberOfProductsInPeriodDESC(java.util.Date start, java.util.Date end);

    List<Supplies> getSuppliesSortedBySuppliersNameInPeriodASC(java.util.Date start, java.util.Date end);

    List<Supplies> getSuppliesSortedBySuppliersNameInPeriodDESC(java.util.Date start, java.util.Date end);

}