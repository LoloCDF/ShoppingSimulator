package ShoppingSimulator.Frames;

import ShoppingSimulator.Common.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProductDescription extends JPanel {
    private Product product = null;

    public ProductDescription(Product product){
        this.setName(product.getName());

        this.product = product;

        setLayout(new BorderLayout());

        JLabel title = new JLabel(product.getName());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(800, 100));
        add(title,BorderLayout.PAGE_START);

        JLabel description = new JLabel(product.getDescription());
        description.setHorizontalAlignment(SwingConstants.LEFT);
        description.setVerticalAlignment(SwingConstants.TOP);
        description.setSize(150,200);
        add(description,BorderLayout.CENTER);

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
        JLabel price = new JLabel(Float.toString(product.getPrice()).concat("€"));
        price.setPreferredSize(new Dimension(150, 300));
        price.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        price.setForeground(Color.red);
        price.setHorizontalAlignment(SwingConstants.LEFT);
        price.setVerticalAlignment(SwingConstants.TOP);
        add(price,BorderLayout.LINE_END);
    }
}