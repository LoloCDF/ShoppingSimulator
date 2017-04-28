package ShoppingSimulator.Communication;


import ShoppingSimulator.Common.Product;
import ShoppingSimulator.Common.RemoteShop;
import ShoppingSimulator.Common.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

// Clase que implementa la funcionalidad de la RemoteShop
public class RemoteShopImpl extends UnicastRemoteObject implements RemoteShop {
    // Lector de fichero xml que hemos creado
    XMLReader reader = null;

    // Lista de productos que ofrece la tienda
    List<Product> products = null;

    // Lista de usuarios que pertenecen a la tienda
    List<User> users = null;

    // Inicializamos la tienda, cargando productos y usuarios en las listas
    public RemoteShopImpl() throws RemoteException {
        reader = new XMLReader("resources/products.xml", "resources/users.xml");
        products = reader.loadProducts();
        users = reader.loadUsers();
    }

    // Un usuario ha solicitado la lista de productos
    public List<Product> getProducts(){
        System.out.println("A client asked for the products.");
        return products;
    }

    // Un usuario ha solicitado su lista de productos
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

    // Un usuario quiere loguearse
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

    // Un usuario desea finalizar su compra
    public boolean buy(String name, List<Product> products){
        boolean result = false;

        // Buscamos al usuario
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
