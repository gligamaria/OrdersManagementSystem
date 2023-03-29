package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

/**
 * The class connects the rest of the application to the DataAccess part, when considering the clients
 */

public class ClientBLL {
    ClientDAO clientDAO = new ClientDAO();

    public String[] getColumns(){ return clientDAO.getColumns();
    }

    public String[][] getData(){
        return clientDAO.getData();
    }

    public void addClient(Client client){
        clientDAO.insert(client);
    }

    public void deleteClient(int id){
        clientDAO.delete(clientDAO.findById(id),"id" );
    }

    public void updateClient(Client client, int id){
        clientDAO.update(client,id);
    }

}
