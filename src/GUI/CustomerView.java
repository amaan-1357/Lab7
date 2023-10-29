package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerView extends JFrame {
    private JLabel bookLabel = new JLabel("Books");
    private JTable bookTable;
    private JScrollPane bookPane;
    private DefaultTableModel bookModel = new DefaultTableModel();
    private JButton placeOrder = new JButton("Place Order");
    private SpringLayout layout = new SpringLayout();

    public CustomerView() {
        setTitle("Customer view");
        setLayout(layout);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(bookLabel);
        layout.putConstraint(SpringLayout.WEST, bookLabel, 5, SpringLayout.WEST, this);

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
        layout.putConstraint(SpringLayout.NORTH, bookPane, 5, SpringLayout.SOUTH, bookLabel);
        layout.putConstraint(SpringLayout.WEST, bookPane, 5, SpringLayout.EAST, this);

        add(placeOrder);
        layout.putConstraint(SpringLayout.NORTH, placeOrder, 5, SpringLayout.SOUTH, bookPane);
        layout.putConstraint(SpringLayout.WEST, placeOrder, 5, SpringLayout.WEST, bookPane);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            CustomerView customerView = new CustomerView();
        });
    }
}
