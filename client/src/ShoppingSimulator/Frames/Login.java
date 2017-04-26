package ShoppingSimulator.Frames;

import ShoppingSimulator.Communication.Communication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login extends JFrame {
    private boolean running = false;

    private JLabel welcome = null;
    private JTextField username = null;
    private JButton submit = null;

    private Communication commu = null;

    public Login() {
        super("ShoppingSimulator: Login (Proyecto SSDD)");

        commu = new Communication("localhost");

        setLayout(new BorderLayout());

        running = true;

        // Welcome banner
        welcome = new JLabel("Welcome to ShoppingSimulator!");
        welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setVerticalAlignment(SwingConstants.CENTER);
        welcome.setPreferredSize(new Dimension(1080, 200));
        add(welcome, BorderLayout.PAGE_START);

        // Login field
        username = new JTextField("USERNAME");
        username.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 50));
        username.setForeground(Color.LIGHT_GRAY);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setPreferredSize(new Dimension(600, 100));
        username.addMouseListener(new EmptyText());

        JPanel pusername = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        pusername.add(username);

        add(pusername, BorderLayout.CENTER);

        // Submit button
        submit = new JButton("Login");
        submit.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setPreferredSize(new Dimension(300, 50));
        submit.addActionListener(new SendInformation());

        JPanel psubmit = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        psubmit.add(submit);
        add(psubmit, BorderLayout.PAGE_END);
    }

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

    private class SendInformation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean result = false;

            result = commu.login(username.getText());

            if(result) {
                Shop shopping = new Shop(username.getText());
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