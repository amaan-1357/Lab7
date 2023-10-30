DELIMITER $$

CREATE PROCEDURE PlaceOrder(
    IN p_orderID INT,
    IN p_customerID INT,
    IN p_bookID INT,
    IN p_quantity INT
)
BEGIN
    DECLARE current_quantity INT;
    DECLARE new_quantity INT;
    
    -- Get the current quantity of the book
    SELECT Quantity INTO current_quantity FROM books WHERE ID = p_bookID;
    
    -- Calculate the new quantity after the order
    SET new_quantity = current_quantity - p_quantity;
    
    IF new_quantity < 0 THEN
        SET new_quantity = 0;
    END IF;
    
    -- Update the quantity and available status
    UPDATE books SET Quantity = new_quantity, Available = (new_quantity > 0) WHERE ID = p_bookID;
    
    -- Associate the order with the customer
    INSERT INTO orders (ID, customerID) VALUES (p_orderID, p_customerID);
   
   -- Insert the book into the order
    INSERT INTO booksInOrder (OrderID, BookID) VALUES (p_orderID, p_bookID);
END$$

DELIMITER ;
