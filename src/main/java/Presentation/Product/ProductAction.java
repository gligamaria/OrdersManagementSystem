package Presentation.Product;

import javax.swing.*;
import java.awt.*;

public class ProductAction extends JFrame{

    public JTextField nameText;
    public JTextField priceText;
    public JTextField stockText;

    public JButton backButton;
    public JButton addProduct;

    ProductWindowController productWindowController = new ProductWindowController(this);

    ProductAction(String windowName) {

        super(windowName);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(435,350);
        this.setLayout(null);

        Icon homeIcon = new ImageIcon("D:\\back_icon-01.png");
        backButton = new JButton(homeIcon);
        backButton.setBounds(10,10,40,40);
        backButton.setBackground(Color.GRAY);
        backButton.addActionListener(productWindowController);
        backButton.setActionCommand("back");
        this.add(backButton);

        addProduct = new JButton(windowName);
        addProduct.setBounds(300,100,100,40);
        addProduct.setBackground(Color.GRAY);
        addProduct.addActionListener(productWindowController);
        addProduct.setActionCommand("Add Product");
        this.add(addProduct);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        name.setBounds(30, 70, 150, 30);
        this.add(name);

        nameText = new JTextField();
        nameText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameText.setBounds(135, 70, 150, 25);
        this.add(nameText);

        JLabel price = new JLabel("Price");
        price.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        price.setBounds(30, 110, 150, 30);
        this.add(price);

        priceText = new JTextField();
        priceText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        priceText.setBounds(135, 110, 150, 25);
        this.add(priceText);

        JLabel stock = new JLabel("Current Stock");
        stock.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        stock.setBounds(30, 150, 150, 30);
        this.add(stock);

        stockText = new JTextField();
        stockText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        stockText.setBounds(135, 150, 150, 25);
        this.add(stockText);


    }
}
