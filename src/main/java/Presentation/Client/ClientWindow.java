package Presentation.Client;

import BusinessLogic.ClientBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClientWindow extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    JButton addClientButton;
    JButton editClientButton;
    JButton deleteClientButton;
    JTable jTable;
    JFrame frame;
    public int instanceIdToDelete = -1;

    public int getInstanceIdToDelete() { return instanceIdToDelete;
    }

    public ClientWindow(){

        super("Clients");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(435,350);
        this.setLayout(null);

        ClientBLL clientBLL = new ClientBLL();
        ClientWindowController clientWindowController = new ClientWindowController(this);

        String[][] data = clientBLL.getData();
        String[] columnNames = clientBLL.getColumns();

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 60, 400, 200);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(135);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(40);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,60,400,200);
        this.add(sp);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(clientWindowController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        addClientButton = new JButton("Add Client");
        addClientButton.setBounds(70,10,100,40);
        addClientButton.setBackground(Color.GRAY);
        addClientButton.addActionListener(clientWindowController);
        addClientButton.setActionCommand("add");
        this.add(addClientButton);

        TableController tableController = new TableController(this);

        editClientButton = new JButton("Edit Client");
        editClientButton.setBounds(190,10,100,40);
        editClientButton.setBackground(Color.GRAY);
        editClientButton.addActionListener(tableController);
        editClientButton.setActionCommand("edit");
        this.add(editClientButton);

        deleteClientButton = new JButton("Delete Client");
        deleteClientButton.setBounds(310,10,100,40);
        deleteClientButton.setBackground(Color.GRAY);
        deleteClientButton.addActionListener(tableController);
        deleteClientButton.setActionCommand("delete");
        this.add(deleteClientButton);

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
