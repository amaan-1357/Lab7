package GUI;

import Objects.Books;
import Objects.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class AdminView extends JFrame {
    private JLabel customerLabel = new JLabel("Customers");
    private JLabel bookLabel = new JLabel("Books");
    private JTable customerTable;
    private JTable bookTable;
    private JScrollPane customerPane;
    private JScrollPane bookPane;
    private Books books = new Books();
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
        setSize(1000,600);
        setLocationRelativeTo(null);
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
        getBooks();
        getCustomers();
        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    AddCustomerView a = new AddCustomerView();
                    a.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosing(e);
                            System.out.println("111");
                            DefaultTableModel tableModel = (DefaultTableModel) customerTable.getModel();
                            tableModel.setRowCount(0);
                            tableModel.fireTableDataChanged();
                            getCustomers();
                            customerModel.fireTableDataChanged();
                        }
                    });
                });
            }
        });
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    AddBookView a = new AddBookView();
                    a.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosing(e);
                            System.out.println("111");
                            DefaultTableModel tableModel = (DefaultTableModel) bookTable.getModel();
                            tableModel.setRowCount(0);
                            tableModel.fireTableDataChanged();
                            getBooks();
                            bookModel.fireTableDataChanged();
                        }
                    });
                });
            }
        });
        showOrderHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    OrderHistoryView o = new OrderHistoryView();
                });
            }
        });
    }
    public void getBooks(){
        ArrayList<Books> book = books.getBooks();
        for(Books b: book){
            Object[] a = {b.getId(),b.getName(),b.getDescription(),b.getQuantity(),b.getAvailable()};
            bookModel.addRow(a);
        }
    }
    public void getCustomers(){
        ArrayList<Customer> customer = customers.getCustomers();
        for(Customer c: customer){
            Object[] a = {c.getId(),c.getName()};
            customerModel.addRow(a);
        }
    }


    public DefaultTableModel getCustomerModel() {
        return customerModel;
    }

    public DefaultTableModel getBookModel() {
        return bookModel;
    }

    public void setCustomerModel(DefaultTableModel customerModel) {
        this.customerModel = customerModel;
    }

    public void setBookModel(DefaultTableModel bookModel) {
        this.bookModel = bookModel;
    }
}
