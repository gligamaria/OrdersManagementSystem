package Presentation.Order;

import BusinessLogic.OrderBLL;

import javax.swing.*;
import java.awt.*;

public class OrdersWindow extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    JButton addOrderButton;
    JFrame frame;
    JTable jTable;

    public OrdersWindow(){
        super("Orders");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(435,350);
        this.setLayout(null);

        OrderBLL orderBLL = new OrderBLL();

        String[][] data = orderBLL.getData();
        String[] columnNames = orderBLL.getColumns();

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 60, 400, 200);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,60,400,200);
        this.add(sp);

        OrderWindowController orderWindowController = new OrderWindowController(this);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(orderWindowController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        addOrderButton = new JButton("Add Order");
        addOrderButton.setBounds(70,10,100,40);
        addOrderButton.setBackground(Color.GRAY);
        addOrderButton.addActionListener(orderWindowController);
        addOrderButton.setActionCommand("add");
        this.add(addOrderButton);
    }
}