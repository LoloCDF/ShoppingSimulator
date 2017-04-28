package ShoppingSimulator.Frames;

import ShoppingSimulator.Common.Product;
import ShoppingSimulator.Frames.Layouts.WrapLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/*
 * Esta clase se encarga de establecer como debe ser el frame
 * que presente la descripcion de un producto.
 */
public class ProductDescription extends JPanel {

    // Este es el producto del cual vamos a mostrar la descripcion
    private Product product = null;

    public ProductDescription(Product product){

        // Inicializamos
        this.setName(product.getName());

        this.product = product;

        setLayout(new BorderLayout());

        // Mostramos titulo del producto
        JLabel title = new JLabel(product.getName());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(800, 100));
        add(title,BorderLayout.PAGE_START);

        // Mostramos la descripcion del producto en un area de texto con scroll
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea text = new JTextArea(product.getDescription());
        text.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll, BorderLayout.CENTER);
        add(panel,BorderLayout.CENTER);

        // Mostramos la imagen del producto, que se obtiene a traves de internet
        try {
            URL url = new URL(product.getThumbnail());
            Image image = ImageIO.read(url);

            JLabel thumbnail = new JLabel(new ImageIcon(image
                    .getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            thumbnail.setPreferredSize(new Dimension(300, 300));
            thumbnail.setHorizontalAlignment(SwingConstants.CENTER);
            thumbnail.setVerticalAlignment(SwingConstants.TOP);
            add(thumbnail, BorderLayout.LINE_START);
        } catch (Exception e){
            System.err.println("Cannot get image from url.");
        }

        // Por ultimo mostramos el precio del producto
        JLabel price = new JLabel(Float.toString(product.getPrice()).concat("€"));
        price.setPreferredSize(new Dimension(150, 300));
        price.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        price.setForeground(Color.red);
        price.setHorizontalAlignment(SwingConstants.LEFT);
        price.setVerticalAlignment(SwingConstants.TOP);
        add(price,BorderLayout.LINE_END);
    }
}