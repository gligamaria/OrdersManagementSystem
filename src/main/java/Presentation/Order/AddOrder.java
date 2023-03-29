package Presentation.Order;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddOrder extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    JButton placeOrder;
    JFrame frame;
    JTable clientTable;
    JTable productTable;
    String productName, productPrice;
    public JTextField quantityText;
    int clientId = -1, productId = -1, currenctQuantity = -1;

    AddOrder(){
        super("Orders");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(435,350);
        this.setLayout(null);

        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();

        String[][] dataClient = clientBLL.getData();
        String[] columnNamesClient = clientBLL.getColumns();

        // Initializing the JTable
        clientTable = new JTable(dataClient, columnNamesClient);
        clientTable.setBounds(10, 60, 400, 100);
        clientTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        clientTable.getColumnModel().getColumn(3).setPreferredWidth(135);
        clientTable.getColumnModel().getColumn(4).setPreferredWidth(40);

        // adding it to JScrollPane
        JScrollPane spClient = new JScrollPane(clientTable);
        spClient.setBounds(10,60,400,100);
        this.add(spClient);

        String[][] dataProduct = productBLL.getData();
        String[] columnNamesProduct = productBLL.getColumns();

        productTable = new JTable(dataProduct, columnNamesProduct);
        productTable.setBounds(10, 180, 400, 100);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(145);

        // adding it to JScrollPane
        JScrollPane spProduct = new JScrollPane(productTable);
        spProduct.setBounds(10,180,400,100);
        this.add(spProduct);

        OrderWindowController orderWindowController = new OrderWindowController(this);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(orderWindowController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JLabel quantity = new JLabel("Quantity");
        quantity.setBounds(70,10,100,40);
        this.add(quantity);

        quantityText = new JTextField();
        quantityText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        quantityText.setBounds(125, 10, 40, 40);
        quantityText.setBackground(myDarkPink);
        this.add(quantityText);

        AddOrderControl addOrderControll = new AddOrderControl(this);

        placeOrder = new JButton("Place order");
        placeOrder.setBounds(185,10,225,40);
        placeOrder.setBackground(myPink);
        placeOrder.addActionListener(addOrderControll);
        placeOrder.setActionCommand("place");
        this.add(placeOrder);

        clientTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                clientId = Integer.parseInt(dataClient[clientTable.getSelectedRow()][0]);
            }
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
        });

        productTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                productId = Integer.parseInt(dataProduct[productTable.getSelectedRow()][0]);
                productName = dataProduct[productTable.getSelectedRow()][1];
                productPrice = dataProduct[productTable.getSelectedRow()][2];
                currenctQuantity = Integer.parseInt(dataProduct[productTable.getSelectedRow()][3]);
            }
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
        });
    }
}
