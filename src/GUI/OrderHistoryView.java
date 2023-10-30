package GUI;

import Objects.Books;
import Objects.BooksInOrder;
import Objects.Customer;
import Objects.Order;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class OrderHistoryView extends JFrame {
    private DefaultListModel<String> orderListModel = new DefaultListModel<>();
    private JList<String> orderList;
    private JScrollPane orderScrollPane;
    private JButton closeButton;
    Order o = new Order();
    private JList<String> selectedBooksList ;
    private DefaultListModel<String> selectedBooksModel = new DefaultListModel<>();
    private JScrollPane bookScrollPane;

    public OrderHistoryView() {
        setTitle("Order History");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        orderList = new JList<>(orderListModel);
        orderScrollPane = new JScrollPane(orderList);

        ArrayList<Order> orders = o.getOrders();

        for (Order or : orders) {
            Customer c = new Customer();
            c.load(or.getCustomerID());
            String el = or.getID().toString() +" "+ c.getName();
            orderListModel.addElement(el);
        }
        selectedBooksList = new JList<>(selectedBooksModel);
        bookScrollPane = new JScrollPane(selectedBooksList);
        orderList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedBooksModel.clear();
                BooksInOrder b = new BooksInOrder();
                String id = orderList.getSelectedValue();
                String[] els= id.split(" ");
                id = els[0];
                ArrayList<Hashtable<String,String>> BIO = b.loadMultiple(Integer.parseInt(id));
                for(Hashtable<String,String > hs:BIO){
                    Books books = new Books();
                    books.load(Integer.parseInt(hs.get("bookID")));
                    selectedBooksModel.addElement(books.getName());
                }
            }
        });
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(orderScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(bookScrollPane,BorderLayout.EAST);

        add(mainPanel);
    }

}
