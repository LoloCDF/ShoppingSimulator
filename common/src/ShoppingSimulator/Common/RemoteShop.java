package ShoppingSimulator.Common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interfaz de la tienda remota
public interface RemoteShop extends Remote {
    List<Product> getProducts() throws RemoteException;
    List<Product> getMyProducts(String name) throws RemoteException;
    boolean login(String name) throws RemoteException;
    boolean buy(String name, List<Product> products) throws RemoteException;
}
