package GUI;

import Objects.Books;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookView extends JFrame {
    private JLabel name = new JLabel("Name: ");
    private JTextField book = new JTextField(15);
    private JLabel des = new JLabel("Description: ");
    private JTextArea description = new JTextArea(3,15);
    private JLabel quan = new JLabel("Quantity: ");
    private JTextField quantity = new JTextField(15);
    private JButton addButton = new JButton("Add");
    private SpringLayout layout = new SpringLayout();

    public AddBookView(){
        setTitle("Add Book");
        setLayout(layout);
        setVisible(true);
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(name);
        layout.putConstraint(SpringLayout.WEST,name,5,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,name,5,SpringLayout.NORTH,this);

        add(des);
        layout.putConstraint(SpringLayout.WEST,des,5,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,des,5,SpringLayout.SOUTH,name);

        add(book);
        layout.putConstraint(SpringLayout.WEST,book,5,SpringLayout.EAST,des);
        layout.putConstraint(SpringLayout.NORTH,name,5,SpringLayout.NORTH,this);

        add(description);
        description.setLineWrap(true);
        description.setBorder(new LineBorder(Color.BLACK));
        layout.putConstraint(SpringLayout.WEST,description,5, SpringLayout.EAST,des);
        layout.putConstraint(SpringLayout.NORTH,description,5,SpringLayout.SOUTH,book);

        add(quan);
        layout.putConstraint(SpringLayout.WEST,quan,5,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,quan,5,SpringLayout.SOUTH,description);

        add(quantity);
        layout.putConstraint(SpringLayout.WEST,quantity,5,SpringLayout.EAST,des);
        layout.putConstraint(SpringLayout.NORTH,quantity,5,SpringLayout.SOUTH,description);

        add(addButton);
        layout.putConstraint(SpringLayout.NORTH,addButton,5,SpringLayout.SOUTH,quantity);
        layout.putConstraint(SpringLayout.WEST,addButton,125,SpringLayout.WEST,this);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Books b = new Books(book.getText(),description.getText(),Integer.parseInt(quantity.getText()),true);
                b.insert();
                dispose();
            }
        });
    }
    public static void main(String... args){
        SwingUtilities.invokeLater(()->{
            AddBookView a = new AddBookView();
        });
    }
}
