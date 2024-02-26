INSERT INTO Customer (Name, Phone, email) VALUES
  ('Tim Cook', '8(926)111-11-11', 'cook@apple.com'),
  ('Kenichiro Yoshida', '8(926)222-22-22', 'yoshida@sony.com'),
  ('Jensen Huang', '8(926)222-22-22', 'huang@nvidia.com');


INSERT INTO Order_ (Customer_ID, Ship_date, Comment) VALUES
  (1, '2023-11-01', 'to tim cook'),
  (2, '2023-11-02', 'to Kenichiro Yoshida'),
  (3, '2023-11-03', 'to Jensen Huang');
  

INSERT INTO Product (Name, Product_type) VALUES
  ('MacBook', 'electronics'),
  ('Haribo', 'food'),
  ('Millenium Falcon', 'toys');

INSERT INTO Supplier (Name, Phone, email, Address, Description) VALUES
  ('Apple', '8(800)111-11-11', 'apple@gmail.com', 'Cupertino', 'you know this company'),
  ('Haribo', '8(800)222-22-22', 'haribo@gmail.com', 'Berlin', 'im pretty sure that youve eaten them at least once'),
  ('Lego', '8(800)333-33-33', 'lego@gmail.com', 'Copenhagen', 'you should buy their shares');

INSERT INTO Supply (Supplier_ID, Ship_date) VALUES
  (1, '2023-01-01'),
  (2, '2023-01-02'),
  (3, '2023-11-03');


INSERT INTO Stock (Product_ID, Supply_ID, Quantity) VALUES
  (1, 1, 5),
  (2, 2, 5),
  (3, 3, 5);


INSERT INTO Warehouse_space(Stock_ID, Room, Shelf, Product_type, Empty_shelf) VALUES
  (1, 1, 1, 'electronics', true),
  (2, 2, 2, 'food', true),
  (3, 3, 3, 'toys', true);


INSERT INTO Goods (Stock_ID, Order_number, Quantity) VALUES
  (1, 1, 5),
  (2, 2, 5),
  (3, 3, 5);



