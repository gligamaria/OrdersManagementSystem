package Presentation.Client;

import BusinessLogic.ClientBLL;
import Model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TableController implements ActionListener {

    ClientBLL clientBLL = new ClientBLL();
    ClientWindow clientWindow;
    ClientAction clientAction;
    int index = 0;

    TableController(ClientWindow clientWindow){
        this.clientWindow = clientWindow;
    }

    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(),"delete")){
            if(clientWindow.getInstanceIdToDelete() != -1){
                clientBLL.deleteClient(clientWindow.getInstanceIdToDelete());
                clientWindow.dispose();
                ClientWindow clientWindow = new ClientWindow();
            }
        }
        else if (Objects.equals(e.getActionCommand(),"edit")){
            if(clientWindow.getInstanceIdToDelete() != -1){
                index = clientWindow.getInstanceIdToDelete();
                clientWindow.dispose();
                clientAction = new ClientAction("Edit Client");
                clientAction.addClient.addActionListener(this);
                clientAction.addClient.setActionCommand("Edit Client");
            }
        }
        else if (Objects.equals(e.getActionCommand(),"Edit Client")){
                Client client = new Client();
                client.setId(index);
                client.setName(clientAction.nameText.getText());
                client.setAddress(clientAction.addressText.getText());
                client.setEmail(clientAction.emailText.getText());
                client.setAge(Integer.parseInt(clientAction.ageText.getText()));
                clientBLL.updateClient(client, index);
                clientAction.dispose();
                ClientWindow clientWindow = new ClientWindow();

        }
    }
}
