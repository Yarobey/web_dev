CREATE OR REPLACE FUNCTION count_customers_orders_by_years(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM orders
    WHERE orders.customer_id = id
    AND orders.ship_date >= current_timestamp - interval '1 year'
    );
END;
$bound$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION count_suppliers_supplies_by_years(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM supplies
    WHERE supplies.supplier_id = id
    AND supplies.ship_date >= current_timestamp - interval '1 year'
    );
END;
$bound$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION count_customers_orders(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM orders
    WHERE orders.customer_id = id
    );
END;
$bound$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION count_suppliers_supplies(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM supplies
    WHERE supplies.supplier_id = id
    );
END;
$bound$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION count_orders_products(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM products_ordered
    WHERE products_ordered.order_number = id
    );
END;
$bound$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION count_products_in_supply(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM supply_products
    WHERE supply_products.supply_id = id
    );
END;
$bound$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION count_warehouse_space_for_stock(id INTEGER) RETURNS INTEGER AS
$bound$
begin
return (
    SELECT COUNT(*)
    FROM warehouse_spaces
    WHERE warehouse_spaces.stock_id = id
    );
END;
$bound$ LANGUAGE plpgsql;