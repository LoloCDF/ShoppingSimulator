package ShoppingSimulator.Frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ShoppingSimulator.Common.*;
import ShoppingSimulator.Communication.Communication;
import ShoppingSimulator.Frames.Layouts.*;

/*
 * Esta clase contiene el frame tienda, que muestra los productos y nos
 * permite interaccionar con el servidor.
 */
public class Shop extends JFrame {
    // Panel que contiene la pestaña de "mis productos"
    private JPanel pmyproducts = null;

    // Etiqueta que muestra el estado del carrito
    private JLabel cartstate = null;

    // Pestañas
    private JTabbedPane tabs = null;

    // Lista de productos que representa los productos que tengo ahora en posesion
    private List<Product> myproducts = null;

    // Productos que ofrece el servidor
    private List<Product> products = null;

    // Productos en el carrito
    private List<Product> cart = null;

    // Contenedores scrollables de productos y mis productos
    private ItemContainer container = null;
    private ItemContainer pcontainer = null;

    // Para una comunicacion transparente
    private Communication commun = null;

    // Nombre de usuario
    private String name = null;

    public Shop (String name, String server){
        // Inicializamos
        super("ShoppingSimulator: Shop(Proyecto SSDD)");

        setLayout(new BorderLayout());
        this.name = name;

        // Inicializamos las comunicaciones
        commun = new Communication(server);

        // Creamos las pestañas
        tabs = new JTabbedPane();
        cart = new ArrayList<Product>();
        myproducts = new ArrayList<Product>();

        // Preparamos la pestaña "Buy"
        products = new ArrayList<Product>();
        products = commun.getProducts();
        container = new ItemContainer(products);

        // Preparamos la pestaña "My products"
        pmyproducts = new JPanel();
        myproducts = commun.getMyProducts(name);
        pcontainer = new ItemContainer(myproducts);
        pmyproducts.add(pcontainer);

        // Añadimos los paneles a las pestañas
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

        // Creamos la barra de opciones de abajo
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

    /*
     * Esta clase extiende a JPanel para conseguir una clase propia
     * adaptada a mostrar los productos.
     */
    private class ItemContainer extends JPanel {
        public ItemContainer(List<Product> products) {
            // Este layout no viene en las librerias de Swing, es descargado
            setLayout(new WrapLayout());

            // Para cada producto, creamos una cajita y la añadimos al panel
            for(int i=0; i<products.size(); i++){
                // Cajita
                JPanel boxcontainer = new JPanel(new BorderLayout());

                // Titulo
                JLabel title = new JLabel(products.get(i).getName());
                title.setHorizontalAlignment(SwingConstants.CENTER);
                boxcontainer.add(title,BorderLayout.PAGE_START);

                // Imagen
                try {
                    URL url = new URL(products.get(i).getThumbnail());
                    Image image = ImageIO.read(url);
                    JLabel thumbnail = new JLabel(new ImageIcon(image
                            .getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                    thumbnail.setPreferredSize(new Dimension(120, 120));
                    boxcontainer.add(thumbnail,BorderLayout.CENTER);
                } catch (Exception e){
                    e.printStackTrace();
                }

                // Precio
                JLabel price = new JLabel(Float.toString(products.get(i).getPrice()).concat("€"));
                price.setHorizontalAlignment(SwingConstants.CENTER);
                boxcontainer.add(price,BorderLayout.PAGE_END);

                // Añadimos el listener que capture el evento de click
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

    // Un producto se ha clickado, mostramos la descripcion en una nueva ventana
    private class ItemClicked implements MouseListener{
        public void mouseClicked(MouseEvent e) {

            // Primero queremos obtener el producto que se ha clickado
            Product product = null;
            JPanel container = (JPanel)e.getComponent();
            String nproduct = ((JLabel) container.getComponent(0)).getText();

            if (nproduct != null){
                for(Product aux: products)
                    if(aux.getName().equals(nproduct))
                        product = aux;
            }

            if(product!=null) {
                // Mostramos un nuevo frame del estilo "ProductDescripcion"
                ProductDescription productDescription = new ProductDescription(product);
                productDescription.setPreferredSize(new Dimension(800, 600));
                JOptionPane optionPane = new JOptionPane(
                        productDescription,
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.YES_NO_OPTION);
                JDialog dialog = optionPane.createDialog(new String("Do you wish to continue buying ")
                        .concat(product.getName().concat("?")));
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);

                // Si el usuario a clickado "si" añadimos el producto al carrito
                if ((int) optionPane.getValue() == 0) {
                    cart.add(product);
                    cartstate.setText(new String("Number of products in my cart: ").concat(
                            Integer.toString(cart.size())));
                }
                // si no salimos sin hacer nada
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

    // Cuando el usuario pulse este boton, se mandan todos los productos del carrito
    // al servidor para que este valide la compra
    private class BuyClicked implements MouseListener{
        public void mouseClicked(MouseEvent e) {

            // Compramos los productos, le pasamos nuestro nombre de usuario y el carrito
            boolean result = commun.buy(name,cart);

            if(result) {
                // Vaciamos el carrito de la compra y refrescamos mis productos
                cartstate.setText("Number of products in my cart: 0");

                int nproducts = cart.size();
                myproducts = commun.getMyProducts(name);
                for (int i = cart.size()-1; i >= 0; i--) {
                    pcontainer.addItem(cart.get(i));
                    pcontainer.refresh();
                    cart.remove(i);
                }
            } else {
                JOptionPane optionPane = new JOptionPane(
                        "Failed to buy, please check your connection and try again later.",
                        JOptionPane.ERROR_MESSAGE,
                        JOptionPane.DEFAULT_OPTION);
                JDialog dialog = optionPane.createDialog("ERROR");
                dialog.setVisible(true);
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

    // Para vaciar el carrito
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

    // Para ver los productos del carrito
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
