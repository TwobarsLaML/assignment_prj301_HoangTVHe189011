CREATE DATABASE ShoppingManagement
GO
Use ShoppingManagement
GO

CREATE TABLE Items(
[id] INT IDENTITY(1,1) PRIMARY KEY,
[img] NVARCHAR(200) ,
[name] NVARCHAR(200),
[price] FLOAT,
[category] NVARCHAR(50))

INSERT INTO Items VALUES 
('handbag.jpg','Prada', 2399.99, 'Luxury'),
('necklace.jpg', 'Personalized Name Necklace | Diamond Name Necklace', 999.99, 'Personal'),
('headband.jpg', 'Effortless Scarf Headband | Handmade | Chiffon', 49.99, 'Handmade')

Create TABLE Accounts(
username NVARCHAR(20) PRIMARY KEY,
password NVARCHAR(30))	

INSERT INTO Accounts VALUES
('sa','123'),('twobars','123'),('admin','123')

CREATE TABLE Carts(
cart_id INT IDENTITY(1,1) PRIMARY KEY,
account_name NVARCHAR(20) REFERENCES Accounts(username),
item_id INT REFERENCES Items(id),
quantity INT
)

CREATE TABLE Orders(
order_id INT IDENTITY(1,1) PRIMARY KEY,
account_name NVARCHAR(20) REFERENCES Accounts(username),
item_id INT REFERENCES Items(id),
quantity INT,
total_price FLOAT
)