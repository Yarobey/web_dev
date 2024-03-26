INSERT INTO suppliers (supplier_id, name, phone, email, address, description) VALUES
    (1, 'Apple Inc', '8(800)111-11-11', 'apple@gmail.com', 'Cupertino', 'you know this company'),
    (2, 'Haribo Inc', '8(800)222-22-22', 'haribo@gmail.com', 'Berlin', 'im pretty sure that youve eaten them at least once'),
    (3, 'Lego', '8(800)333-33-33', 'lego@gmail.com', 'Copenhagen', 'toys for everyone');

INSERT INTO supplies (supply_id, supplier_id, ship_date, comment, supplier_name) VALUES
    (1, 1, '2023-01-01', 'from Apple', 'Apple Inc'),
    (2, 2, '2023-02-01', 'from Haribo', 'Haribo Inc'),
    (3, 3, '2022-01-01', 'from Lego', 'Lego'),
    (4, 1, '2013-01-02', 'from Apple', 'Apple Inc'),
    (5, 2, '2023-01-02', 'from Haribo', 'Haribo Inc'),
    (6, 3, '2013-02-02', 'from Lego', 'Lego'),
    (7, 1, '2014-01-02', 'from Apple', 'Apple Inc'),
    (8, 2, '2014-02-02', 'from Haribo', 'Haribo Inc'),
    (9, 3, '2014-03-02', 'from Lego', 'Lego'),
    (10, 1, '2014-04-02', 'from Apple', 'Apple Inc'),
    (11, 1, '2012-1-02', 'from Apple', 'Apple Inc');

INSERT INTO products (product_id, name, product_type, size1, size2, size3, quantity, description) VALUES
    (1, 'MacBook', 'electronics', 40, 20, 10, 30, 'laptop'),
    (2, 'Haribo Bears', 'food', 15, 15, 15, 30, 'sweet'),
    (3, 'Haribo Snakes', 'food', 15, 15, 15, 30, 'sweet'),
    (4, 'Millenium Falcon', 'toys', 70, 70, 30, 30, 'spaceship');


INSERT INTO supply_products (item_id, supply_id, product_id, quantity) VALUES
    (1, 7, 1, 2),
    (2, 9, 2, 2),
    (3, 1, 2, 2),
    (4, 7, 2, 1);

INSERT INTO warehouse_spaces (place_id, product_id, room, shelf, product_type, empty_space) VALUES
    (1, 1, 1, 1, 'electronics', false),
    (2, 2, 3, 2, 'food', false),
    (3, 3, 3, 10, 'food', false),
    (4, 4, 4, 6, 'toys', true),
    (5, 3, 4, 3, 'food', false);

INSERT INTO customers (customer_id, name, phone, email) VALUES
    (1, 'Tim Cook', '8(926)111-11-11', 'cook@apple.com'),
    (2, 'Yoshida Cook', '8(926)222-22-22', 'yoshida@sony.com'),
    (3, 'Jensen Huang', '8(926)222-22-22', 'huang@nvidia.com');

INSERT INTO orders (order_number, customer_id, ship_date, comment, customer_name) VALUES
    (1, 1, '2023-01-02', 'to Tim', 'Tim Cook'),
    (2, 2, '2023-02-02', 'to Yoshida', 'Yoshida Cook'),
    (3, 3, '2022-01-02', 'to Jensen', 'Jensen Huang'),
    (4, 1, '2013-01-03', 'to Tim', 'Tim Cook'),
    (5, 2, '2023-01-03', 'to Yoshida', 'Yoshida Cook'),
    (6, 3, '2013-02-03', 'to Jensen', 'Jensen Huang'),
    (7, 1, '2014-01-03', 'to Tim', 'Tim Cook'),
    (8, 2, '2014-02-03', 'to Yoshida', 'Yoshida Cook'),
    (9, 3, '2014-03-03', 'to Jensen', 'Jensen Huang'),
    (10, 1, '2014-04-03', 'to Tim', 'Tim Cook'),
    (11, 1, '2012-1-03', 'to Tim', 'Tim Cook');

INSERT INTO products_ordered (item_id, product_id, order_number, quantity) VALUES
    (1, 1, 7, 1),
    (2, 2, 9, 1),
    (3, 2, 1, 1),
    (4, 2, 7, 1);
