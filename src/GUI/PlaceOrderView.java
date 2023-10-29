package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderView extends JFrame {
    private JComboBox<String> bookDropdown;
    private JButton selectBookButton;
    private JButton removeBookButton;
    private JButton placeOrder;
    private JList<String> selectedBooksList;
    private DefaultListModel<String> selectedBooksModel;
    private List<String> availableBooks;

    public PlaceOrderView(List<String> books) {
        setTitle("Place Order");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        availableBooks = new ArrayList<>(books);

        bookDropdown = new JComboBox<>(availableBooks.toArray(new String[0]));
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
                }
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the selected book from the list
                int selectedIndex = selectedBooksList.getSelectedIndex();
                if (selectedIndex != -1) {
                    selectedBooksModel.remove(selectedIndex);
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(selectBookButton);
        buttonsPanel.add(removeBookButton);
        buttonsPanel.add(placeOrder);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(bookDropdown, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(selectedBooksList), BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            List<String> sampleAvailableBooks = new ArrayList<>();
            sampleAvailableBooks.add("Book A");
            sampleAvailableBooks.add("Book B");
            sampleAvailableBooks.add("Book C");

            PlaceOrderView placeOrderView = new PlaceOrderView(sampleAvailableBooks);
            placeOrderView.setVisible(true);
        });
    }
}
