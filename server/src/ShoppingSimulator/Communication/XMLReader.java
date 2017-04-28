package ShoppingSimulator.Communication;


import ShoppingSimulator.Common.Product;
import ShoppingSimulator.Common.User;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Lectos de los ficheros XML que contienen los usuarios y los productos
public class XMLReader {
    // Para leer los productos
    private Document dproducts = null;

    // Para leer los usuarios
    private Document dusers = null;

    // Inicializamos la clase
    public XMLReader(String pproducts, String pusers){
        File fproducts = new File(pproducts);
        File fusers = new File(pusers);
        
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            dproducts = documentBuilder.parse(fproducts);
            dusers = documentBuilder.parse(fusers);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // Metodo que se encarga de leer los productos y crear una lista de productos
    public List<Product> loadProducts(){
        System.out.println("Loading products...");

        List<Product> result = new ArrayList<Product>();
        NodeList products = dproducts.getElementsByTagName("product");
        NodeList names = dproducts.getElementsByTagName("name");
        NodeList prices = dproducts.getElementsByTagName("price");
        NodeList urls = dproducts.getElementsByTagName("url");
        NodeList descriptions = dproducts.getElementsByTagName("description");

        for(int i=0; i < products.getLength(); i++){
            Product product = new Product(names.item(i).getTextContent(),
                    Integer.parseInt(products.item(i).getAttributes().getNamedItem("id").getNodeValue()),
                    descriptions.item(i).getTextContent(),
                    Float.parseFloat(prices.item(i).getTextContent()),
                    urls.item(i).getTextContent());
            result.add(product);
        }

        return result;
    }

    // Metodo que se encarga de leer los usuarios y crear una lista de usuarios
    public List<User> loadUsers(){
        System.out.println("Loading users...");
        List<User> result = new ArrayList<User>();
        NodeList users = dusers.getElementsByTagName("user");
        NodeList names = dusers.getElementsByTagName("name");
        NodeList ages = dusers.getElementsByTagName("age");
        
        for(int i=0; i < users.getLength(); i++){
            User user = new User(
                    Integer.parseInt(users.item(i).getAttributes().getNamedItem("id").getNodeValue()),
                    names.item(i).getTextContent(),
                    Integer.parseInt(ages.item(i).getTextContent())
            );
            result.add(user);
        }

        return result;
    }
}
