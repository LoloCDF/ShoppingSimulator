package ShoppingSimulator.Communication;


import ShoppingSimulator.Common.Product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteShopImpl extends UnicastRemoteObject implements RemoteShop {
    List<Product> products = null;

    public RemoteShopImpl() throws RemoteException {
        products = new ArrayList<Product>();

        for (int i = 0;i<10;i++)
            products.add(new Product("ps4", 1, "Console",10.25f,
                    "https://images-na.ssl-images-amazon.com/images/I/41Nnygi-NEL.jpg"));
    }

    public List<Product> getProducts(){
        System.out.println("A client asked for the products.");
        return products;
    }
}
