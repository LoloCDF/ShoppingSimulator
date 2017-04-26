package ShoppingSimulator.Communication;


import ShoppingSimulator.Common.Product;
import ShoppingSimulator.Common.RemoteShop;
import ShoppingSimulator.Common.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteShopImpl extends UnicastRemoteObject implements RemoteShop {
    XMLReader reader = null;
    List<Product> products = null;
    List<User> users = null;

    public RemoteShopImpl() throws RemoteException {
        reader = new XMLReader("resources/products.xml", "resources/users.xml");
        products = reader.loadProducts();
        users = reader.loadUsers();
    }



    public List<Product> getProducts(){
        System.out.println("A client asked for the products.");
        return products;
    }

    public List<Product> getMyProducts(String name){
        boolean result = false;

        User buser = null;
        for(User user: users)
            if(user.getName().equals(name))
                buser = user;

        List<Product> products = null;
        if(buser != null)
            products = buser.getProducts();

        System.out.println("A client asked for its products.");

        return products;
    }

    public boolean login(String name){
        boolean result = false;

        for (User user:users){
            if(name.equals(user.getName()))
                result = true;
        }

        if(result)
            System.out.println("A user has logged.");

        return result;
    }

    public boolean buy(String name, List<Product> products){
        boolean result = false;

        // We look for the user
        User buyer = null;
        for(User user:users)
            if(user.getName().equals(name))
                buyer = user;

        if(buyer != null) {
            for (Product product : products)
                buyer.addProduct(product);

            result = true;
        }

        if(result)
            System.out.println("A user has bought products");

        return result;
    }
}
