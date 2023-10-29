package GUI;

import Objects.Books;
import Objects.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminView extends JFrame {
    private JLabel customerLabel = new JLabel("Customers");
    private JLabel bookLabel = new JLabel("Books");
    private JTable customerTable;
    private JTable bookTable;
    private JScrollPane customerPane;
    private JScrollPane bookPane;
    private final Books books = new Books();
    private final Customer customers = new Customer();
    private DefaultTableModel customerModel = new DefaultTableModel();
    private DefaultTableModel bookModel = new DefaultTableModel();
    private JButton addCustomer = new JButton("Add Customer");
    private JButton addBook = new JButton("Add Book");
    private JButton showOrderHistory = new JButton("Display Order History");
    private SpringLayout layout = new SpringLayout();
    public AdminView(){
        setTitle("Admin view");
        setLayout(layout);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(customerLabel);
        add(bookLabel);
        layout.putConstraint(SpringLayout.WEST, customerLabel,5,SpringLayout.EAST, this);

        customerModel.addColumn("ID");
        customerModel.addColumn("Name");
        customerTable = new JTable(customerModel);
        customerTable.setGridColor(Color.BLACK);
        customerTable.setShowGrid(true);
        customerPane = new JScrollPane(customerTable);
        add(customerPane);
        layout.putConstraint(SpringLayout.NORTH, customerPane, 5, SpringLayout.SOUTH, customerLabel);
        layout.putConstraint(SpringLayout.WEST, customerPane,5,SpringLayout.EAST,this);

        bookModel.addColumn("ID");
        bookModel.addColumn("Name");
        bookModel.addColumn("Description");
        bookModel.addColumn("Quantity");
        bookModel.addColumn("Available");
        bookTable = new JTable(bookModel);
        bookTable.setGridColor(Color.BLACK);
        bookTable.setShowGrid(true);
        bookPane = new JScrollPane(bookTable);
        add(bookPane);
        layout.putConstraint(SpringLayout.NORTH,bookPane, 5, SpringLayout.SOUTH, bookLabel);
        layout.putConstraint(SpringLayout.WEST,bookLabel,200,SpringLayout.EAST,customerPane);
        layout.putConstraint(SpringLayout.WEST,bookPane,5,SpringLayout.EAST,customerPane);

        add(addCustomer);
        layout.putConstraint(SpringLayout.NORTH,addCustomer, 5, SpringLayout.SOUTH,customerPane);
        layout.putConstraint(SpringLayout.WEST, addCustomer,160,SpringLayout.WEST,customerPane);

        add(addBook);
        layout.putConstraint(SpringLayout.NORTH,addBook,5,SpringLayout.SOUTH,bookPane);
        layout.putConstraint(SpringLayout.WEST,addBook,175,SpringLayout.WEST,bookPane);

        add(showOrderHistory);
        layout.putConstraint(SpringLayout.NORTH,showOrderHistory,5,SpringLayout.SOUTH,addCustomer);
        layout.putConstraint(SpringLayout.WEST,showOrderHistory,20,SpringLayout.EAST,addCustomer);
        layout.putConstraint(SpringLayout.EAST,showOrderHistory,-20, SpringLayout.WEST,addBook);


    }
    public static void main(String... args){
        SwingUtilities.invokeLater(()->{
            AdminView adminView = new AdminView();
        });
    }
}
