use SCDLab7;

-- Sample data for the customers table
INSERT INTO customers (Name) VALUES
    ('Alice'),
    ('Bob'),
    ('Charlie'),
    ('David');

-- Sample data for the books table
INSERT INTO books (Name, Description, Quantity, Available) VALUES
    ('Book 1', 'Description of Book 1', 50, 1),
    ('Book 2', 'Description of Book 2', 30, 1),
    ('Book 3', 'Description of Book 3', 20, 1),
    ('Book 4', 'Description of Book 4', 40, 1);

-- Sample data for the orders table
INSERT INTO orders (customerID) VALUES
    (1),
    (2),
    (3),
    (4);

-- Sample data for the booksInOrder table
INSERT INTO booksInOrder (OrderID, BookID) VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 4),
    (4, 1);
