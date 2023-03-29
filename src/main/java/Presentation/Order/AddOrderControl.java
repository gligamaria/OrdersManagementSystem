package Presentation.Order;

import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import BusinessLogic.ReceiptMaker;
import Model.Orders;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddOrderControl implements ActionListener {
    AddOrder addOrder;
    OrderBLL orderBLL = new OrderBLL();
    ProductBLL productBLL = new ProductBLL();
    AddOrderControl(AddOrder addOrder){
        this.addOrder = addOrder;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(),"place")){
            if(addOrder.clientId != -1 && addOrder.productId != -1){
                int wantedQuantity = Integer.parseInt(addOrder.quantityText.getText());
                if(addOrder.currenctQuantity < wantedQuantity){
                    ErrorWindow errorWindow = new ErrorWindow();
                }
                else{
                    Product product = new Product();
                    product.setId(addOrder.productId);
                    product.setName(addOrder.productName);
                    product.setPrice(Float.parseFloat(addOrder.productPrice));
                    product.setCurrentStock(addOrder.currenctQuantity - wantedQuantity);

                    productBLL.updateProduct(product,addOrder.productId);

                    Orders orders = new Orders();
                    orders.setClientId(addOrder.clientId);
                    orders.setProductId(addOrder.productId);
                    orders.setQuantity(Integer.parseInt(addOrder.quantityText.getText()));
                    addOrder.dispose();
                    orderBLL.addOrder(orders);
                    orders.setId(orderBLL.returnLastId());
                    ReceiptMaker receiptMaker = new ReceiptMaker();
                    receiptMaker.makeReceipt(orders);

                    OrdersWindow ordersWindow = new OrdersWindow();
                }
            }
        }
    }
}
