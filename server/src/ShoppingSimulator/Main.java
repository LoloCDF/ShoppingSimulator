package ShoppingSimulator;
import ShoppingSimulator.Communication.RemoteShopImpl;

import java.rmi.*;

class Main  {
    static public void main (String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            RemoteShopImpl srv = new RemoteShopImpl();
            Naming.rebind("rmi://localhost:8888/RemoteShop", srv);
        }
        catch (RemoteException e) {
            System.err.println(new String("Communication error: ").concat(e.toString()));
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Exception in RemoteShopImpl:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
