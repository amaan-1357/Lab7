package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private JButton adminView = new JButton("Open Admin View");
    private JButton customerView = new JButton("Open Customer View");

    public MainPage(){
        setTitle("Main");
        setLayout(new FlowLayout());
        setSize(150,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        add(adminView);
        add(customerView);

        adminView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    SwingUtilities.invokeLater(()->{
                        AdminView adminView = new AdminView();
                    });
                });
            }
        });
        customerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    CustomerView customerView = new CustomerView();
                });
            }
        });
    }
    public static void main(String... args){
        SwingUtilities.invokeLater(()->{
            MainPage mp = new MainPage();
        });
    }
}
