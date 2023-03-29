package Presentation.Product;

import BusinessLogic.ProductBLL;
import Model.Product;
import Presentation.StartWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ProductWindowController implements ActionListener {

    JFrame jFrame;
    ProductAction productAction;
    ProductBLL productBLL = new ProductBLL();

    ProductWindowController(JFrame jFrame){
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
            ProductAction addProduct = new ProductAction("Add Product");
        }
        else if (Objects.equals(e.getActionCommand(),"back")){
            jFrame.dispose();
            ProductWindow productWindow = new ProductWindow();
        }
        else if (Objects.equals(e.getActionCommand(),"Add Product")){
            productAction = (ProductAction) jFrame;
            Product product = new Product();
            product.setName(productAction.nameText.getText());
            product.setPrice(Float.parseFloat(productAction.priceText.getText()));
            product.setCurrentStock(Integer.parseInt(productAction.stockText.getText()));
            productBLL.addProduct(product);
            jFrame.dispose();
            ProductWindow productWindow = new ProductWindow();
        }
    }
}
