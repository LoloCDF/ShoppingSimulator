import java.rmi.*;
import java.util.*;

interface Tienda extends Remote {
    Cuenta newUsuario(Usuario t) throws RemoteException;
    List<Usuario> getUsuarios() throws RemoteException;
    Producto  newProducto (Producto p) throws RemoteException;
    List<Producto> getProductos() throws RemoteException;

}
