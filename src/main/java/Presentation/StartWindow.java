package Presentation;

import Presentation.Client.ClientWindow;
import Presentation.Order.OrdersWindow;
import Presentation.Product.ProductWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame implements ActionListener{

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    private JButton manageProducts;
    private JButton manageClients;
    private JButton viewOrders;

    public StartWindow(){

        super("Hello!");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        manageClients=new JButton("Manage Clients");
        manageClients.setBounds(115,65,200,40);
        manageClients.setBackground(myPink);
        this.add(manageClients);
        manageClients.addActionListener(this);

        manageProducts=new JButton("Manage Products");
        manageProducts.setBounds(115,130,200,40);
        manageProducts.setBackground(myPink);
        this.add(manageProducts);
        manageProducts.addActionListener(this);

        viewOrders=new JButton("Manage Orders");
        viewOrders.setBounds(115,195,200,40);
        this.add(viewOrders);
        viewOrders.setBackground(myPink);
        viewOrders.addActionListener(this);

        this.setSize(435,350);
        this.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == manageProducts){
            this.dispose();
            ProductWindow productWindow = new ProductWindow();
        }
        else if(e.getSource() == manageClients){
            this.dispose();
            ClientWindow clientWindow = new ClientWindow();
        }
        else{
            this.dispose();
            OrdersWindow ordersWindow = new OrdersWindow();
        }
    }
}