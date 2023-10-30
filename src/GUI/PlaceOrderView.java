package GUI;

import Objects.Books;
import Objects.BooksInOrder;
import Objects.Customer;
import Objects.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderView extends JFrame {
    private JLabel name = new JLabel("Customer Name: ");
    private JTextField cus = new JTextField("",10);
    private JComboBox<String> bookDropdown;
    private JButton selectBookButton;
    private JButton removeBookButton;
    private JButton placeOrder;
    private JList<String> selectedBooksList;
    private Books books = new Books();
    private DefaultListModel<String> selectedBooksModel;
    private List<String> availableBooks;

    public PlaceOrderView() {
        setTitle("Place Order");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(420, 250);
        setLocationRelativeTo(null);
        setVisible(true);


        getBooks();
        selectBookButton = new JButton("Select Book");
        removeBookButton = new JButton("Remove Book");
        placeOrder = new JButton("Place Order");
        selectedBooksModel = new DefaultListModel<>();
        selectedBooksList = new JList<>(selectedBooksModel);

        selectBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the selected book to the list
                String selectedBook = (String) bookDropdown.getSelectedItem();

                if (selectedBook != null) {
                    selectedBooksModel.addElement(selectedBook);
                    bookDropdown.removeItemAt(bookDropdown.getSelectedIndex());
                }
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the selected book from the list
                int selectedIndex = selectedBooksList.getSelectedIndex();
                if (selectedIndex != -1) {
                    bookDropdown.addItem(selectedBooksModel.getElementAt(selectedIndex));
                    selectedBooksModel.remove(selectedIndex);
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(selectBookButton);
        buttonsPanel.add(removeBookButton);
        buttonsPanel.add(placeOrder);

        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.add(name);
        namePanel.add(cus);
        namePanel.add(bookDropdown);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(namePanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(selectedBooksList), BorderLayout.SOUTH);

        add(mainPanel);

        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order o = new Order();
                Customer c = new Customer();
                while(cus.getText().isEmpty() ||cus.getText().equals("Enter Name!!")){
                    cus.setText("Enter Name!!");
                }
                c.setName(cus.getText());
                c.insert();
                String query = "SELECT * FROM customers WHERE ID = (SELECT MAX(ID) FROM customers);";
                c.customLoad(query);
                o.setCustomerID(c.getId());
                o.insert();
                query = "SELECT * FROM orders WHERE ID = (SELECT MAX(ID) FROM orders);";
                o.customLoad(query);
                List<String> books = new ArrayList<>();
                for (int i = 0; i < selectedBooksModel.size(); i++) {
                    books.add(selectedBooksModel.getElementAt(i));
                }
                Books bo = new Books();
                BooksInOrder BIO = new BooksInOrder();
                for(String b: books){
                    String q = "'" + b + "'";
                    query = "Select * from books Where name = " + q;
                    bo.customLoad(query);
                    BIO.setOrderID(o.getID());
                    BIO.setBookID(bo.getId());
                    BIO.insert();
                    bo.setQuantity(bo.getQuantity()-1);
                    bo.save();
                }
                dispose();
            }
        });
    }
    public void getBooks(){
        ArrayList<Books> book = books.getBooks();
        availableBooks = new ArrayList<>();
        for(Books b: book){
            availableBooks.add(b.getName());
        }
        bookDropdown = new JComboBox<>(availableBooks.toArray(new String[0]));
    }

}
