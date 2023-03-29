package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Product;

public class ProductBLL {
    ProductDAO productDAO = new ProductDAO();

    /**
     * The class connects the rest of the application to the DataAccess part, when considering the products
     */

    public String[] getColumns(){ return productDAO.getColumns();
    }

    public String[][] getData(){
        return productDAO.getData();
    }

    public void addProduct(Product product){
        productDAO.insert(product);
    }

    public void deleteProduct(int id){
        productDAO.delete(productDAO.findById(id),"id" );
    }

    public void updateProduct(Product product, int id){
        productDAO.update(product,id);
    }
}
