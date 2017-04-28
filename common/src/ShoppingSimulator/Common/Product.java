package ShoppingSimulator.Common;

import java.io.*;

// Clase que representa a un producto
public class Product implements Serializable {
    private int id = 0;
    private String name = null;
    private String description = null;
    private float price = 0;
    private String urlthumbnail = null;

    public Product (String name, int id, String description, float price, String urlthumbnail){
        this.name = name;
        this.id = id;
        this.description=description;
        this.price=price;
        this.urlthumbnail = urlthumbnail;
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

    public String getThumbnail(){
        return urlthumbnail;
    }

    public String toString(){
        return name + " | " + Integer.toString(id);
    }
}
