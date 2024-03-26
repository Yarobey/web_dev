DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (
  customer_id SERIAL PRIMARY KEY,
  name        text NOT NULL,
  phone       text,
  email       text,
  address     text,
  description text
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (
  order_number  SERIAL PRIMARY KEY,
  customer_id   int NOT NULL REFERENCES customers(customer_id) ON DELETE CASCADE,
  ship_date     DATE NOT NULL CHECK (ship_date <= current_timestamp),
  comment       text,
  customer_name text
);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
  product_id      SERIAL PRIMARY KEY,
  name            text NOT NULL,
  product_type    text CHECK (
         product_type = 'food'
      OR product_type = 'electronics'
      OR product_type = 'toys'
      OR product_type = 'clothes'
      ),
  size1           int CHECK (size1 > 0),
  size2           int CHECK (size2 > 0),
  size3           int CHECK (size3 > 0),
  quantity        int NOT NULL CHECK (quantity >= 0),
  description     text,
  expiration_date date CHECK (expiration_date >= current_timestamp)
);

DROP TABLE IF EXISTS suppliers CASCADE;
CREATE TABLE suppliers (
  supplier_id SERIAL PRIMARY KEY,
  name        text NOT NULL,
  phone       text,
  email       text,
  address     text,
  description text
);

DROP TABLE IF EXISTS supplies CASCADE;
CREATE TABLE supplies (
  supply_id     SERIAL PRIMARY KEY,
  supplier_id   int REFERENCES suppliers(supplier_id) ON DELETE CASCADE,
  ship_date     date NOT NULL CHECK (ship_date <= current_timestamp),
  comment       text,
  supplier_name text
);


DROP TABLE IF EXISTS supply_products CASCADE;
CREATE TABLE supply_products (
  item_id   SERIAL PRIMARY KEY,
  supply_id     int NOT NULL REFERENCES supplies(supply_id) ON DELETE CASCADE,
  product_id      int NOT NULL REFERENCES products(product_id),
  quantity      int NOT NULL CHECK (quantity >= 0)
);

DROP TABLE IF EXISTS warehouse_spaces CASCADE;
CREATE TABLE warehouse_spaces (
  place_id      SERIAL PRIMARY KEY,
  product_id      int NOT NULL REFERENCES products(product_id) ON DELETE SET NULL,
  room          int NOT NULL CHECK (room >= 0 AND room <= 512),
  shelf         int NOT NULL CHECK (shelf >= 0 AND shelf <= 64),
  product_type  text CHECK (
         product_type = 'food'
      OR product_type = 'electronics'
      OR product_type = 'toys'
      OR product_type = 'clothes'
      ),
  empty_space   bool
);

DROP TABLE IF EXISTS products_ordered CASCADE;
CREATE TABLE products_ordered (
  item_id       SERIAL PRIMARY KEY,
  product_id    int NOT NULL REFERENCES products(product_id),
  order_number  int NOT NULL REFERENCES orders(order_number) ON DELETE CASCADE,
  quantity      int NOT NULL CHECK (quantity > 0)
);