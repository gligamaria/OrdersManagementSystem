package BusinessLogic;

import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The class handles the part of writing in a file and creating the receipt
 * of an order, based on the order that is transmitted as a parameter
 */

public class ReceiptMaker  {

    ClientDAO clientDAO = new ClientDAO();
    ProductDAO productDAO = new ProductDAO();

    public void makeReceipt(Orders order){

         Client client = clientDAO.findById(order.getClientId());
         Product product = productDAO.findById(order.getProductId());

        try {
            FileWriter fileWriter = new FileWriter("Order no." + order.getId());

            fileWriter.write("Order no." + order.getId() + "\n");
            fileWriter.write("----------------------------------- \n");
            fileWriter.write("Client details:\n");
            fileWriter.write("   - name: " + client.getName() + "\n");
            fileWriter.write("   - address: " + client.getAddress() + "\n");
            fileWriter.write("   - email: " + client.getEmail() + "\n");
            fileWriter.write("   - age: " + client.getAge() + "\n");
            fileWriter.write("----------------------------------- \n");
            fileWriter.write("Product details:\n");
            fileWriter.write("   - name: " + product.getName() + "\n");
            fileWriter.write("   - bought quantity " + order.getQuantity() + "\n");
            fileWriter.write("   - price per unit: " + product.getPrice() + "\n");
            fileWriter.write("   - price in total: " + product.getPrice()* order.getQuantity());

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
