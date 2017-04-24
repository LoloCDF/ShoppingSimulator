package ShoppingSimulator.Frames;

import javax.swing.*;

public class Shop extends JFrame {
    private JLabel text = null;

    public Shop (){
        super("ShoppingSimulator: Shop(Proyecto SSDD)");

        text = new JLabel("Contenido");
        add(text);
    }
}
