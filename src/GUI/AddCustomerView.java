package GUI;

import javax.swing.*;

public class AddCustomerView extends JFrame {
    private JLabel name = new JLabel("Name: ");
    private JTextField customer = new JTextField(15);
    private JButton addButton = new JButton("Add");
    private SpringLayout layout = new SpringLayout();
    public AddCustomerView(){
        setTitle("Add Customer");
        setLayout(layout);
        setVisible(true);
        setSize(300,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(name);
        layout.putConstraint(SpringLayout.WEST,name,5,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,name,5,SpringLayout.NORTH,this);

        add(customer);
        layout.putConstraint(SpringLayout.WEST,customer,5,SpringLayout.EAST,name);
        layout.putConstraint(SpringLayout.NORTH,name,5,SpringLayout.NORTH,this);

        add(addButton);
        layout.putConstraint(SpringLayout.NORTH,addButton,5,SpringLayout.SOUTH,this);
        layout.putConstraint(SpringLayout.WEST,addButton,125,SpringLayout.WEST,this);
    }

    public static void main(String... args){
        SwingUtilities.invokeLater(()->{
            AddCustomerView a = new AddCustomerView();
        });
    }
}
