package ShoppingSimulator.Communication;

import ShoppingSimulator.Common.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteShop extends Remote {
    List<Product> getProducts() throws RemoteException;
}
