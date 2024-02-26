DROP TABLE IF EXISTS Customer CASCADE;
CREATE TABLE Customer (
  Customer_ID SERIAL PRIMARY KEY,
  Name        text NOT NULL,
  Phone       char(20),
  email       text,
  Address     text,
  City        text,
  zip_code    char(10),
  Description text
);

DROP TABLE IF EXISTS Order_ CASCADE;
CREATE TABLE Order_ (
  Order_number  SERIAL PRIMARY KEY,
  Customer_ID   int NOT NULL REFERENCES Customer(Customer_ID) ON DELETE CASCADE,
  Ship_date     DATE NOT NULL,
  Comment       text
);

DROP TABLE IF EXISTS Product CASCADE;
CREATE TABLE Product (
  Product_ID      SERIAL PRIMARY KEY,
  Name            text NOT NULL,
  Product_type    text,
  size1           int,
  size2           int,
  size3           int,
  Description     text,
  Expiration_date date
);

DROP TABLE IF EXISTS Supplier CASCADE;
CREATE TABLE Supplier (
  Supplier_ID SERIAL PRIMARY KEY,
  Name        text NOT NULL,
  Phone       char(20),
  email       text,
  Address     text,
  Description text
);

DROP TABLE IF EXISTS Supply CASCADE;
CREATE TABLE Supply (
  Supply_ID SERIAL PRIMARY KEY,
  Supplier_ID   int REFERENCES Supplier(Supplier_ID) ON DELETE CASCADE,
  Ship_date     date
);

DROP TABLE IF EXISTS Stock CASCADE;
CREATE TABLE Stock (
  Stock_ID    SERIAL PRIMARY KEY,
  Product_ID  int NOT NULL REFERENCES Product(Product_ID) ON DELETE CASCADE,
  Supply_ID   int NOT NULL REFERENCES Supply(Supply_ID) ON DELETE CASCADE,
  Quantity    int CHECK(Quantity >= 0)
);


DROP TABLE IF EXISTS Warehouse_space CASCADE;
CREATE TABLE Warehouse_space (
  Place_ID      SERIAL PRIMARY KEY,
  Stock_ID      int NOT NULL REFERENCES Stock(Stock_ID),
  Room          int,
  Shelf         int,
  Product_type  text,
  Empty_shelf   bool
);

DROP TABLE IF EXISTS Goods CASCADE;
CREATE TABLE Goods ( 
  Item_ID       SERIAL PRIMARY KEY,
  Stock_ID      int NOT NULL REFERENCES Stock(Stock_ID),
  Order_number  int NOT NULL REFERENCES Order_(Order_number) ON DELETE CASCADE,
  Quantity      int CHECK(Quantity > 0)
);

