package Presentation.Client;

import BusinessLogic.ClientBLL;
import Model.Client;
import Presentation.StartWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientWindowController implements ActionListener {

    JFrame jFrame;
    ClientAction addClient;
    ClientBLL clientBLL = new ClientBLL();

    ClientWindowController(JFrame jFrame){
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
            StartWindow startWindow = new StartWindow();
        }
        else if (Objects.equals(e.getActionCommand(),"add")){
            jFrame.dispose();
            ClientAction addClient = new ClientAction("Add Client");
        }
        else if (Objects.equals(e.getActionCommand(),"back")){
            jFrame.dispose();
            ClientWindow clientWindow = new ClientWindow();
        }
        else if (Objects.equals(e.getActionCommand(),"Add Client")){
            clientBLL.addClient(clientData());
            jFrame.dispose();
            ClientWindow clientWindow = new ClientWindow();
        }
    }
    private Client clientData(){
        addClient = (ClientAction) jFrame;
        Client client = new Client();
        client.setName(addClient.nameText.getText());
        client.setAddress(addClient.addressText.getText());
        client.setEmail(addClient.emailText.getText());
        client.setAge(Integer.parseInt(addClient.ageText.getText()));
        return client;
    }
}
