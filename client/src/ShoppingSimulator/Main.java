package ShoppingSimulator;

import ShoppingSimulator.Frames.*;

import javax.swing.*;

/* Este es el cliente de Shopping simulator, si no especificamos ninguna ip
* el cliente apuntara automaticamente a localhost
*/
public class Main {

    public static void main(String args[]){
        String server = "localhost";

        if(args.length == 1)
            server = args[0];

        System.out.println(new String("Connecting to: ").concat(server));

        // Iniciamos el frame para preguntar por el nombre de usuario
        Login logwindow = new Login(server);
        logwindow.setSize(1080,720);
        logwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logwindow.setVisible(true);
    }
}