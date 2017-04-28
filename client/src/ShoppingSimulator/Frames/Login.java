package ShoppingSimulator.Frames;

import ShoppingSimulator.Communication.Communication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
 * Frame que presenta un campo para que el usuario introduzca su nombre.
 * Luego tambien se encarga de inicializar el siguiente frame con la tienda
 * en si. Recibe la IP del servidor RMI al que se debe conectar.
 */
public class Login extends JFrame {

    // Texto con la cabecera de bienvenida
    private JLabel welcome = null;

    // Nombre de usuario
    private JTextField username = null;

    // Boton de enviar
    private JButton submit = null;

    // Clase comunicacion para acceder de manera transparente a los metodos del servidor
    private Communication commu = null;

    // Direccion IP del servidor
    private String server = null;

    // El constructor recibe la direccion IP del servidor
    public Login(String server) {

        // Establecemos el titulo de la ventana e inicializamos las variables
        super("ShoppingSimulator: Login (Proyecto SSDD)");

        this.server = server;

        commu = new Communication(server);

        setLayout(new BorderLayout());

        // Creamos y añadimos el banner del titulo
        welcome = new JLabel("Welcome to ShoppingSimulator!");
        welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setVerticalAlignment(SwingConstants.CENTER);
        welcome.setPreferredSize(new Dimension(1080, 200));
        add(welcome, BorderLayout.PAGE_START);

        // IDEM con el campo de usuario
        username = new JTextField("USERNAME");
        username.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 50));
        username.setForeground(Color.LIGHT_GRAY);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setPreferredSize(new Dimension(600, 100));
        username.addMouseListener(new EmptyText());

        JPanel pusername = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        pusername.add(username);

        add(pusername, BorderLayout.CENTER);

        // Y con el boton de enviar
        submit = new JButton("Login");
        submit.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setPreferredSize(new Dimension(300, 50));
        submit.addActionListener(new SendInformation());

        JPanel psubmit = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        psubmit.add(submit);
        add(psubmit, BorderLayout.PAGE_END);
    }

    /*
     * Clases encargadas de capturar eventos y procesarlos
     */

    // Vacia el campo de texto cuando el usuario hace click
    private class EmptyText implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            username.setText("");
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    // Manda la informacion, se loguea en el servidor y crea el frame Shop
    private class SendInformation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean result = false;

            result = commu.login(username.getText());

            // Si el servidor me ha verificado creo el frame
            if(result) {
                Shop shopping = new Shop(username.getText(), server);
                shopping.setSize(1080, 720);
                shopping.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                shopping.setVisible(true);
                setVisible(false);
                dispose();
            } else {
                JOptionPane optionPane = new JOptionPane(
                        "Login failed, please check password and connectivity",
                        JOptionPane.ERROR_MESSAGE,
                        JOptionPane.DEFAULT_OPTION);
                JDialog dialog = optionPane.createDialog(new String("ERROR"));
                dialog.setVisible(true);
            }
        }
    }
}