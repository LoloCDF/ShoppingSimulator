package ShoppingSimulator.Communication;


import ShoppingSimulator.Common.Product;
import ShoppingSimulator.Common.RemoteShop;
import ShoppingSimulator.Common.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteShopImpl extends UnicastRemoteObject implements RemoteShop {
    List<Product> products = null;
    List<User> users = null;

    public RemoteShopImpl() throws RemoteException {
        products = new ArrayList<Product>();
        users = new ArrayList<User>();

        for (int i = 0;i<10;i++)
            products.add(new Product("ps4", 1, "Console",10.25f,
                    "https://images-na.ssl-images-amazon.com/images/I/41Nnygi-NEL.jpg"));

        users.add(new User(1,"maragon", 21));
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
