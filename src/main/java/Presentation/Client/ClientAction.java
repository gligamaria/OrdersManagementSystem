package Presentation.Client;

import javax.swing.*;
import java.awt.*;

public class ClientAction extends JFrame {

    public JTextField nameText;
    public JTextField addressText;
    public JTextField emailText;
    public JTextField ageText;

    public JButton backButton;
    public JButton addClient;

    ClientWindowController clientWindowController = new ClientWindowController(this);

    ClientAction(String windowName) {

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
        backButton.addActionListener(clientWindowController);
        backButton.setActionCommand("back");
        this.add(backButton);

        addClient = new JButton(windowName);
        addClient.setBounds(300,120,100,40);
        addClient.setBackground(Color.GRAY);
        addClient.addActionListener(clientWindowController);
        addClient.setActionCommand("Add Client");
        this.add(addClient);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        name.setBounds(30, 70, 150, 30);
        this.add(name);

        nameText = new JTextField();
        nameText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameText.setBounds(135, 70, 150, 25);
        this.add(nameText);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        address.setBounds(30, 110, 150, 30);
        this.add(address);

        addressText = new JTextField();
        addressText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        addressText.setBounds(135, 110, 150, 25);
        this.add(addressText);

        JLabel email = new JLabel("Email");
        email.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        email.setBounds(30, 150, 150, 30);
        this.add(email);

        emailText = new JTextField();
        emailText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailText.setBounds(135, 150, 150, 25);
        this.add(emailText);

        JLabel age = new JLabel("Age");
        age.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        age.setBounds(30, 190, 150, 30);
        this.add(age);

        ageText = new JTextField();
        ageText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        ageText.setBounds(135, 190, 150, 25);
        this.add(ageText);

    }
}
