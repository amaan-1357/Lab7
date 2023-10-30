package GUI;

import Objects.Books;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerView extends JFrame {
    private JLabel bookLabel = new JLabel("Books");
    private JTable bookTable;
    private JScrollPane bookPane;
    private Books books = new Books();
    private DefaultTableModel bookModel = new DefaultTableModel();
    private JButton placeOrder = new JButton("Place Order");
    private SpringLayout layout = new SpringLayout();

    public CustomerView() {
        setTitle("Customer view");
        setLayout(layout);
        setVisible(true);
        setSize(500,600);
        setLocationRelativeTo(null);
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

        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    PlaceOrderView pov = new PlaceOrderView();
                });
            }
        });

        getBooks();
    }
    public void getBooks(){
        ArrayList<Books> book = books.getBooks();
        for(Books b: book){
            Object[] a = {b.getId(),b.getName(),b.getDescription(),b.getQuantity(),b.getAvailable()};
            bookModel.addRow(a);
        }
    }


}
