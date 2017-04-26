package ShoppingSimulator.Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id = 0;
    private String name = null;
    private int age = 0;
    private List<Product> products = null;

    public User(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
        this.products = new ArrayList<Product>();
    }

    public int getId(){ return id; }

    public String getName(){ return name; }

    public int getAge(){ return age; }

    public List<Product> getProducts() { return products; }

    public void addProduct(Product product) { products.add(product);}
}
