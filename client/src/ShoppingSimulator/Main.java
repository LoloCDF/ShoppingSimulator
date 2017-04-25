package ShoppingSimulator;

import ShoppingSimulator.Frames.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String args[]){

        // We have to ask for a username
        Login logwindow = new Login();
        logwindow.setSize(1080,720);
        logwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logwindow.setVisible(true);
    }
}