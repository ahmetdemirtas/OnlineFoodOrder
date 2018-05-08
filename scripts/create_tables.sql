-- Java DB altına herkesin ayrı ayrı veritabanını kurması gerekiyor. Bilgiler asagida
-- DATABASE NAME: foodorder
-- USERNAME: dedeler
-- PASSWORD: dedeler

DROP TABLE FoodOrder;
DROP TABLE Food;
DROP TABLE Restaurant;
DROP TABLE Category;
DROP TABLE Customer;
DROP TABLE Address;
DROP TABLE City;


CREATE TABLE City
(
        Province VARCHAR (20) NOT NULL /* il */,
        Town VARCHAR (20) NOT NULL /* ilçe */,
        District VARCHAR (20) NOT NULL /* mahalle */,
        PRIMARY KEY (Province, Town, District)
);

CREATE TABLE Address
(
        AddressID INT NOT NULL GENERATED ALWAYS AS IDENTITY /*(START WITH 1, INCREMENT BY 1) --> bilgi amacli!!! */,
        Category VARCHAR (10) NOT NULL /* restaurant or customer */,
        Street VARCHAR (30) NOT NULL,
        Apartment VARCHAR (30) NOT NULL,
        Province VARCHAR (20) NOT NULL,
        Town VARCHAR (20) NOT NULL,
        District VARCHAR (20) NOT NULL,
        PRIMARY KEY (AddressID),
        CONSTRAINT City_FK
        FOREIGN KEY (Province, Town, District)
        REFERENCES City (Province, Town, District)
);

CREATE TABLE Customer
(
        CustomerID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 200, INCREMENT BY 1),
        Password VARCHAR (15) NOT NULL,
        FirstName VARCHAR (15) NOT NULL,
        LastName VARCHAR (30) NOT NULL,
        Email VARCHAR (30) NOT NULL,
        PhoneNumber VARCHAR (15) NOT NULL,
        AddressID INT REFERENCES Address (AddressID),
        PRIMARY KEY (CustomerID)
);

CREATE TABLE Category
(
        CategoryName VARCHAR (15) NOT NULL,
        PRIMARY KEY (CategoryName)
);

CREATE TABLE Restaurant
(
        --restaurant id 100den başla
        RestaurantID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 100, INCREMENT BY 1),
        Password VARCHAR (15) NOT NULL,
        RestaurantName VARCHAR (15) NOT NULL,
        Email VARCHAR (30) NOT NULL,
        PhoneNumber VARCHAR (15) NOT NULL,
        AddressID INT REFERENCES Address (AddressID),
        CategoryName VARCHAR (15) NOT NULL REFERENCES Category (CategoryName),
        PRIMARY KEY (RestaurantID)
);

CREATE TABLE Food
(
        FoodID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
        RestaurantID INT NOT NULL REFERENCES Restaurant (RestaurantID),
        FoodName VARCHAR (15) NOT NULL,
        Price INT NOT NULL,
        PRIMARY KEY (FoodID)
);

CREATE TABLE FoodOrder
(
        OrderID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
        CustomerID INT NOT NULL REFERENCES Customer (CustomerID),
        RestaurantID INT NOT NULL REFERENCES Restaurant (RestaurantID),
        FoodID INT NOT NULL REFERENCES Food (FoodID),
        OrderTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP/* Java kodunda dokunmayın! */,
        Status VARCHAR (10) NOT NULL,
        PRIMARY KEY (OrderID)
);