package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryView extends JFrame {
    private DefaultListModel<String> orderListModel = new DefaultListModel<>();
    private JList<String> orderList;
    private JScrollPane orderScrollPane;
    private JButton closeButton;

    public OrderHistoryView(List<String> orderHistory) {
        setTitle("Order History");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        orderList = new JList<>(orderListModel);
        orderScrollPane = new JScrollPane(orderList);

        for (String order : orderHistory) {
            orderListModel.addElement(order);
        }

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the OrderHistoryView window
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(orderScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            List<String> sampleOrderHistory = new ArrayList<>();
            sampleOrderHistory.add("Order 1: Book A, Book B");
            sampleOrderHistory.add("Order 2: Book C, Book D");
            sampleOrderHistory.add("Order 3: Book E");

            OrderHistoryView orderHistoryView = new OrderHistoryView(sampleOrderHistory);
            orderHistoryView.setVisible(true);
        });
    }
}
