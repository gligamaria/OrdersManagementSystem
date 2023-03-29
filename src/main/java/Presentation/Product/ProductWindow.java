package Presentation.Product;

import BusinessLogic.ProductBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductWindow extends JFrame{

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    JButton addProductButton;
    JButton editProductButton;
    JButton deleteProductButton;
    JTable jTable;
    JFrame frame;
    public int instanceIdToDelete = -1;

    public int getInstanceIdToDelete() { return instanceIdToDelete;
    }

    public ProductWindow(){

        super("Products");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(435,350);
        this.setLayout(null);

        ProductBLL productBLL = new ProductBLL();
        ProductWindowController productWindowController = new ProductWindowController(this);

        String[][] data = productBLL.getData();
        String[] columnNames = productBLL.getColumns();

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 60, 400, 200);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(145);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,60,400,200);
        this.add(sp);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(productWindowController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(70,10,100,40);
        addProductButton.setBackground(Color.GRAY);
        addProductButton.addActionListener(productWindowController);
        addProductButton.setActionCommand("add");
        this.add(addProductButton);

        TableProductController tableProductController = new TableProductController(this);

        editProductButton = new JButton("Edit Product");
        editProductButton.setBounds(190,10,100,40);
        editProductButton.setBackground(Color.GRAY);
        editProductButton.addActionListener(tableProductController);
        editProductButton.setActionCommand("edit");
        this.add(editProductButton);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setBounds(310,10,100,40);
        deleteProductButton.setBackground(Color.GRAY);
        deleteProductButton.addActionListener(tableProductController);
        deleteProductButton.setActionCommand("delete");
        this.add(deleteProductButton);

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                instanceIdToDelete = Integer.parseInt(data[jTable.getSelectedRow()][0]);
            }
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
        });

    }

}