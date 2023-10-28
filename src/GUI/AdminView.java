package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminView extends JFrame {
    private JLabel customerLabel = new JLabel("Customers");
    private JLabel bookLabel = new JLabel("Orders");
    private JTable customerTable = new JTable();
    private JTable bookTable = new JTable();
    private DefaultTableModel customerModel = new DefaultTableModel();
    private DefaultTableModel bookModel = new DefaultTableModel();
    private JButton addCustomer = new JButton("Add Customer");
    private JButton addBook = new JButton("Add Book");
    private JButton showOrderHistory = new JButton("Display Order History");
    public AdminView(){
        setLayout(new SpringLayout());
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

    }
}
