package BusinessLogic;

import DataAccess.OrdersDAO;
import Model.Orders;

import java.util.ArrayList;
import java.util.List;

/**
 * The class connects the rest of the application to the DataAccess part, when considering the orders
 */

public class OrderBLL{

    OrdersDAO ordersDAO = new OrdersDAO();

    public String[] getColumns(){ return ordersDAO.getColumns();
    }

    public String[][] getData(){
        return ordersDAO.getData();
    }

    public void addOrder(Orders orders){
        ordersDAO.insert(orders);
    }

    public int returnLastId(){
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList = ordersDAO.findAll();
        return ordersList.get(ordersList.size() - 1).getId();
    }
}
