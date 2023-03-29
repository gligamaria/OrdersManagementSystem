package Presentation.Order;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {
    ErrorWindow(){
        super("Error!");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(435,350);
        this.setLayout(null);

        OrderWindowController orderWindowController = new OrderWindowController(this);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(orderWindowController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JLabel errorText = new JLabel("<html>Unfortunately, there are not enough <BR> products on the stock for you " +
                "<BR> to place the order! :( </html>", SwingConstants.CENTER);
        errorText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        errorText.setBounds(50, 100, 300, 60);
        this.add(errorText);
    }
}
