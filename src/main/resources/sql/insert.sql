-- INSERT INTO Customer (Name, Phone, email) VALUES
--   ('Tim Cook', '8(926)111-11-11', 'cook@apple.com'),
--   ('Kenichiro Yoshida', '8(926)222-22-22', 'yoshida@sony.com'),
--   ('Jensen Huang', '8(926)222-22-22', 'huang@nvidia.com');
--
--
-- INSERT INTO Order_ (Customer_ID, Ship_date, Comment) VALUES
--   (1, '2023-11-01', 'to tim cook'),
--   (2, '2023-11-02', 'to Kenichiro Yoshida'),
--   (3, '2023-11-03', 'to Jensen Huang');
--
--
-- INSERT INTO Product (Name, Product_type) VALUES
--   ('MacBook', 'electronics'),
--   ('Haribo', 'food'),
--   ('Millenium Falcon', 'toys');
--
-- INSERT INTO Supplier (Name, Phone, email, Address, Description) VALUES
--   ('Apple', '8(800)111-11-11', 'apple@gmail.com', 'Cupertino', 'you know this company'),
--   ('Haribo', '8(800)222-22-22', 'haribo@gmail.com', 'Berlin', 'im pretty sure that youve eaten them at least once'),
--   ('Lego', '8(800)333-33-33', 'lego@gmail.com', 'Copenhagen', 'you should buy their shares');
--
-- INSERT INTO Supply (Supplier_ID, Ship_date) VALUES
--   (1, '2023-01-01'),
--   (2, '2023-01-02'),
--   (3, '2023-11-03');
--
--
-- INSERT INTO Stock (Product_ID, Supply_ID, Quantity) VALUES
--   (1, 1, 5),
--   (2, 2, 5),
--   (3, 3, 5);
--
--
-- INSERT INTO Warehouse_space(Stock_ID, Room, Shelf, Product_type, Empty_shelf) VALUES
--   (1, 1, 1, 'electronics', true),
--   (2, 2, 2, 'food', true),
--   (3, 3, 3, 'toys', true);
--
--
-- INSERT INTO Items_ordered (Stock_ID, Order_number, Quantity) VALUES
--   (1, 1, 5),
--   (2, 2, 5),
--   (3, 3, 5);

INSERT INTO suppliers (supplier_id, name, phone, email, address, description) VALUES
    (1, 'Apple', '8(800)111-11-11', 'apple@gmail.com', 'Cupertino', 'you know this company'),
    (2, 'Haribo', '8(800)222-22-22', 'haribo@gmail.com', 'Berlin', 'im pretty sure that youve eaten them at least once'),
    (3, 'Lego', '8(800)333-33-33', 'lego@gmail.com', 'Copenhagen', 'toys for everyone');

INSERT INTO supplies (supply_id, supplier_id, ship_date, comment) VALUES
    (1, 1, '2023-01-01', 'from Apple'),
    (2, 2, '2023-02-01', 'from Haribo'),
    (3, 3, '2022-01-01', 'from Lego'),
    (4, 1, '2013-01-02', 'from Apple'),
    (5, 2, '2023-01-02', 'from Haribo'),
    (6, 3, '2013-02-02', 'from Lego'),
    (7, 1, '2014-01-02', 'from Apple'),
    (8, 2, '2014-02-02', 'from Haribo'),
    (9, 3, '2014-03-02', 'from Lego'),
    (10, 1, '2014-04-02', 'from Apple'),
    (11, 1, '2012-1-02', 'from Apple');

INSERT INTO products (product_id, name, product_type, size1, size2, size3, description) VALUES
    (1, 'MacBook', 'electronics', 40, 20, 10, 'laptop'),
    (2, 'Haribo Bears', 'food', 15, 15, 15, 'sweet'),
    (3, 'Haribo Snakes', 'food', 15, 15, 15, 'sweet'),
    (4, 'Millenium Falcon', 'toys', 70, 70, 30, 'spaceship');

INSERT INTO stock (stock_id, product_id, supply_id, quantity) VALUES
    (1, 1, 7, 2),
    (2, 2, 2, 2),
    (3, 3, 2, 1),
    (4, 4, 9, 2);

INSERT INTO supply_products (position_id, supply_id, stock_id, quantity) VALUES
    (1, 7, 1, 2),
    (2, 2, 2, 2),
    (3, 2, 3, 1),
    (4, 9, 4, 2);

INSERT INTO warehouse_spaces (place_id, stock_id, room, shelf, product_type, empty_space) VALUES
    (1, 1, 1, 1, 'electronics', false),
    (2, 2, 3, 2, 'food', false),
    (3, 3, 3, 10, 'food', false),
    (4, 4, 4, 6, 'toys', true);

INSERT INTO customers (customer_id, name, phone, email) VALUES
    (1, 'Tim Cook', '8(926)111-11-11', 'cook@apple.com'),
    (2, 'Kenichiro Yoshida', '8(926)222-22-22', 'yoshida@sony.com'),
    (3, 'Jensen Huang', '8(926)222-22-22', 'huang@nvidia.com');

INSERT INTO orders (order_number, customer_id, ship_date, comment) VALUES
    (1, 1, '2023-01-02', 'to Tim'),
    (2, 2, '2023-02-02', 'to Kenichiro'),
    (3, 3, '2022-01-02', 'to Jensen'),
    (4, 1, '2013-01-03', 'to Tim'),
    (5, 2, '2023-01-03', 'to Kenichiro'),
    (6, 3, '2013-02-03', 'to Jensen'),
    (7, 1, '2014-01-03', 'to Tim'),
    (8, 2, '2014-02-03', 'to Kenichiro'),
    (9, 3, '2014-03-03', 'to Jensen'),
    (10, 1, '2014-04-03', 'to Tim'),
    (11, 1, '2012-1-03', 'to Tim');

INSERT INTO products_ordered (item_id, stock_id, order_number, quantity) VALUES
    (1, 1, 7, 1),
    (2, 2, 9, 1),
    (3, 2, 1, 1),
    (4, 2, 7, 1);
