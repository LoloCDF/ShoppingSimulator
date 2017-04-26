package ShoppingSimulator.Communication;

import ShoppingSimulator.Common.Product;

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
                    remoteHost.concat(":8888/ShoppingSimulator.Communication.RemoteShop")));
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
}
