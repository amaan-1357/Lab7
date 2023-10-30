SET SQL_MODE ='IGNORE_SPACE';

create database if not exists SCDLab7;

use  SCDLab7;

CREATE TABLE IF NOT EXISTS customers(
	ID int unique key not null auto_increment primary key,
	Name varchar(30) not null
);

CREATE TABLE IF NOT EXISTS books(
	ID int unique key not null auto_increment primary key,
	Name varchar(100) not null,
	Description varchar (500),
	Quantity int not null,
	Available bool not null
);

CREATE TABLE IF NOT EXISTS orders(
	ID int unique key not null auto_increment primary key,
	customerID int not null,
	CONSTRAINT FK_CustomerID FOREIGN KEY (customerID)
    REFERENCES customers(ID)
);

CREATE TABLE IF NOT EXISTS booksInOrder(
	ID int unique key not null auto_increment primary key,
	OrderID int not null,
	BookID int not null,
	Constraint FK_OrderID Foreign Key(orderID)
	references orders(id),
	constraint FK_BookID Foreign Key(BookID)
	references books(id)
);


