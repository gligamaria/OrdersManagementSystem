package Presentation.Product;

import BusinessLogic.ProductBLL;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TableProductController implements ActionListener {

    ProductBLL productBLL = new ProductBLL();
    ProductWindow productWindow;
    ProductAction productAction;
    int index = 0;

    TableProductController(ProductWindow productWindow){
        this.productWindow = productWindow;
    }

    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(),"delete")){
            if(productWindow.getInstanceIdToDelete() != -1){
                productBLL.deleteProduct(productWindow.getInstanceIdToDelete());
                productWindow.dispose();
                ProductWindow productWindow = new ProductWindow();
            }
        }
        else if (Objects.equals(e.getActionCommand(),"edit")){
            if(productWindow.getInstanceIdToDelete() != -1){
                index = productWindow.getInstanceIdToDelete();
                productWindow.dispose();
                productAction = new ProductAction("Edit Product");
                productAction.addProduct.addActionListener(this);
                productAction.addProduct.setActionCommand("Edit Product");
            }
        }
        else if (Objects.equals(e.getActionCommand(),"Edit Product")){
            Product product = new Product();
            product.setId(index);
            product.setName(productAction.nameText.getText());
            product.setPrice(Float.parseFloat(productAction.priceText.getText()));
            product.setCurrentStock(Integer.parseInt(productAction.stockText.getText()));
            productBLL.updateProduct(product, index);
            productAction.dispose();
            ProductWindow productWindow = new ProductWindow();

        }
    }

}
