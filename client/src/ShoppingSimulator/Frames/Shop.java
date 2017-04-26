package ShoppingSimulator.Frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ShoppingSimulator.Common.*;
import ShoppingSimulator.Communication.Communication;
import ShoppingSimulator.Frames.Layouts.*;

public class Shop extends JFrame {
    private JTabbedPane tabs = null;
    private List<Product> myproducts = null;
    private List<Product> products = null;
    private List<Product> cart = null;

    private ItemContainer container = null;
    private ItemContainer pcontainer = null;

    private JPanel pmyproducts = null;
    private JLabel cartstate = null;

    private Communication commun = null;

    public Shop (){
        super("ShoppingSimulator: Shop(Proyecto SSDD)");

        setLayout(new BorderLayout());

        // Init communications
        commun = new Communication("localhost");

        tabs = new JTabbedPane();
        cart = new ArrayList<Product>();
        myproducts = new ArrayList<Product>();

        // Preparing "Buy" panel
        products = new ArrayList<Product>();
        products = commun.getProducts();
        container = new ItemContainer(products);

        // Preparing "My products" panel
        pmyproducts = new JPanel();
        pcontainer = new ItemContainer(myproducts);
        pmyproducts.add(pcontainer);

        // We add the panels to the tabs
        JScrollPane pscroll = new JScrollPane(pcontainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pscroll.getVerticalScrollBar().setUnitIncrement(16);
        JScrollPane scroll = new JScrollPane(container,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        tabs.addTab("Buy",scroll);
        tabs.addTab("My Products",pscroll);
        tabs.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        add(tabs, BorderLayout.CENTER);

        // We have to create the bottom bar
        JPanel bottom = new JPanel(new FlowLayout());
        cartstate = new JLabel(new String("Number of products in my cart: ").concat(
                Integer.toString(cart.size())));
        JButton buy = new JButton("Buy");
        buy.addMouseListener(new BuyClicked());
        JButton view = new JButton("View cart");
        view.addMouseListener(new ViewClicked());
        JButton empty = new JButton("Empty cart");
        empty.addMouseListener(new EmptyClicked());
        bottom.add(cartstate);
        bottom.add(buy);
        bottom.add(view);
        bottom.add(empty);

        add(bottom,BorderLayout.PAGE_END);
    }

    private class ItemContainer extends JPanel {
        public ItemContainer(List<Product> products) {
            setLayout(new WrapLayout());

            for(int i=0; i<products.size(); i++){
                JPanel boxcontainer = new JPanel(new BorderLayout());

                JLabel title = new JLabel(products.get(i).getName());
                title.setHorizontalAlignment(SwingConstants.CENTER);
                boxcontainer.add(title,BorderLayout.PAGE_START);

                try {
                    URL url = new URL(products.get(i).getThumbnail());
                    Image image = ImageIO.read(url);
                    JLabel thumbnail = new JLabel(new ImageIcon(image
                            .getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                    thumbnail.setPreferredSize(new Dimension(120, 120));
                    boxcontainer.add(thumbnail,BorderLayout.CENTER);
                } catch (Exception e){
                    System.err.println("Cannot get the image via URL.");
                }

                JLabel price = new JLabel(Float.toString(products.get(i).getPrice()));
                price.setHorizontalAlignment(SwingConstants.CENTER);
                boxcontainer.add(price,BorderLayout.PAGE_END);

                boxcontainer.addMouseListener(new ItemClicked());

                add(boxcontainer);
            }
        }

        public void addItem(Product product){
            JPanel boxcontainer = new JPanel(new BorderLayout());

            JLabel title = new JLabel(product.getName());
            title.setHorizontalAlignment(SwingConstants.CENTER);
            boxcontainer.add(title,BorderLayout.PAGE_START);

            try {
                URL url = new URL(product.getThumbnail());
                Image image = ImageIO.read(url);
                JLabel thumbnail = new JLabel(new ImageIcon(image
                        .getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                thumbnail.setPreferredSize(new Dimension(120, 120));
                boxcontainer.add(thumbnail,BorderLayout.CENTER);
            } catch (Exception e){
                System.err.println("Cannot get the image via URL.");
            }
            JLabel price = new JLabel(Float.toString(product.getPrice()));
            price.setHorizontalAlignment(SwingConstants.CENTER);
            boxcontainer.add(price,BorderLayout.PAGE_END);

            boxcontainer.addMouseListener(new ItemClicked());

            add(boxcontainer);
        }

        public void refresh(){
            validate();
            repaint();
        }
    }

    private class ItemClicked implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            ProductDescription productDescription = new ProductDescription(products.get(e.getButton()));
            productDescription.setSize(800,600);
            JOptionPane optionPane = new JOptionPane(
                    productDescription,
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);
            JDialog dialog = optionPane.createDialog(new String("Do you wish to continue buying ")
                    .concat(products.get(e.getButton()).getName().concat("?")));
            dialog.setVisible(true);

            if ((int) optionPane.getValue() == 0) {
                cart.add(products.get(e.getButton()));
                cartstate.setText(new String("Number of products in my cart: ").concat(
                        Integer.toString(cart.size())));
            }
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

    private class BuyClicked implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            cartstate.setText("Number of products in my cart: 0");

            int nproducts = cart.size();

            for(int i = nproducts-1; i >=0 ; i--){
                myproducts.add(cart.get(i));
                pcontainer.addItem(cart.get(i));
                pcontainer.refresh();
                cart.remove(i);
            }
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

    private class EmptyClicked implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            cartstate.setText("Number of products in my cart: 0");

            int nproducts = cart.size();

            for(int i = nproducts-1; i >= 0; i--){
                cart.remove(i);
            }
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

    private class ViewClicked implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showConfirmDialog(e.getComponent().getParent().getParent(),new ItemContainer(cart),
                    "My cart",JOptionPane.PLAIN_MESSAGE);
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
}
