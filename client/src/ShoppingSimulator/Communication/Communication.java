package ShoppingSimulator.Communication;

import ShoppingSimulator.Common.Product;
import ShoppingSimulator.Common.RemoteShop;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

public class Communication {
    private String remoteHost = null;
    private RemoteShop shop = null;

    public Communication (String remoteHost){
        this.remoteHost = remoteHost;

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            shop = (RemoteShop) Naming.lookup(new String("//").concat(
                    remoteHost.concat(":8888/RemoteShop")));
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Exception en ClienteShop:");
            e.printStackTrace();
        }
    }

    public List<Product> getProducts(){
        List<Product> products = null;
        try {
            products = shop.getProducts();
        } catch (RemoteException e){
            e.printStackTrace();
        }

        return  products;
    }

    public boolean login(String name){
        boolean result = false;

        try {
            result = shop.login(name);
        } catch (RemoteException e){
            e.printStackTrace();
        }

        return result;
    }

    public List<Product> getMyProducts(String name){
        List<Product> products = null;
        try {
            products = shop.getMyProducts(name);
        } catch (RemoteException e){
            e.printStackTrace();
        }

        return  products;
    }

    public boolean buy(String name, List<Product> products){
        boolean result = false;
        try {
            result = shop.buy(name, products);
        } catch (RemoteException e){
            e.printStackTrace();
        }

        return  result;
    }
}
