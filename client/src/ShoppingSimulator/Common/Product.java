package ShoppingSimulator.Common;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;
import java.awt.Image;
import java.net.URL;

public class Product implements Serializable {
    private int id = 0;
    private String name = null;
    private String description = null;
    private float price = 0;
    private Image thumbnail = null;

    public Product (String name, int id, String description, float price, String url){
        this.name = name;
        this.id = id;
        this.description=description;
        this.price=price;

        try {
            URL rurl = new URL(url);
            this.thumbnail = ImageIO.read(rurl);
        } catch (IOException e){
            System.out.println("Image couldn't be loaded");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public Image getThumbnail(){
        return thumbnail;
    }
}
