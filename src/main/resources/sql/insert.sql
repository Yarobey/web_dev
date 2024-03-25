INSERT INTO suppliers (supplier_id, name, phone, email, address, description) VALUES
    (1, 'Apple', '8(800)111-11-11', 'apple@gmail.com', 'Cupertino', 'you know this company'),
    (2, 'Haribo', '8(800)222-22-22', 'haribo@gmail.com', 'Berlin', 'im pretty sure that youve eaten them at least once'),
    (3, 'Lego', '8(800)333-33-33', 'lego@gmail.com', 'Copenhagen', 'toys for everyone');

INSERT INTO supplies (supply_id, supplier_id, ship_date, comment, supplier_name) VALUES
    (1, 1, '2023-01-01', 'from Apple', 'Apple'),
    (2, 2, '2023-02-01', 'from Haribo', 'Haribo'),
    (3, 3, '2022-01-01', 'from Lego', 'Lego'),
    (4, 1, '2013-01-02', 'from Apple', 'Apple'),
    (5, 2, '2023-01-02', 'from Haribo', 'Haribo'),
    (6, 3, '2013-02-02', 'from Lego', 'Lego'),
    (7, 1, '2014-01-02', 'from Apple', 'Apple'),
    (8, 2, '2014-02-02', 'from Haribo', 'Haribo'),
    (9, 3, '2014-03-02', 'from Lego', 'Lego'),
    (10, 1, '2014-04-02', 'from Apple', 'Apple'),
    (11, 1, '2012-1-02', 'from Apple', 'Apple');

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

INSERT INTO supply_products (item_id, supply_id, stock_id, quantity) VALUES
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

INSERT INTO orders (order_number, customer_id, ship_date, comment, customer_name) VALUES
    (1, 1, '2023-01-02', 'to Tim', 'Tim Cook'),
    (2, 2, '2023-02-02', 'to Kenichiro', 'Kenichiro Yoshida'),
    (3, 3, '2022-01-02', 'to Jensen', 'Jensen Huang'),
    (4, 1, '2013-01-03', 'to Tim', 'Tim Cook'),
    (5, 2, '2023-01-03', 'to Kenichiro', 'Kenichiro Yoshida'),
    (6, 3, '2013-02-03', 'to Jensen', 'Jensen Huang'),
    (7, 1, '2014-01-03', 'to Tim', 'Tim Cook'),
    (8, 2, '2014-02-03', 'to Kenichiro', 'Kenichiro Yoshida'),
    (9, 3, '2014-03-03', 'to Jensen', 'Jensen Huang'),
    (10, 1, '2014-04-03', 'to Tim', 'Tim Cook'),
    (11, 1, '2012-1-03', 'to Tim', 'Tim Cook');

INSERT INTO products_ordered (item_id, stock_id, order_number, quantity) VALUES
    (1, 1, 7, 1),
    (2, 2, 9, 1),
    (3, 2, 1, 1),
    (4, 2, 7, 1);
