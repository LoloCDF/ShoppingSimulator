import java.io.*;

class Usuario implements Serializable {
     private String nombre;
     private String iD;
    
     Usuario(String n, String i) {
         nombre = n;
         iD = i;
     }
     public String getNombre() {
         return nombre;
     }
     public String getId() {
         return iD;
     }
     public String toString() {
         return nombre + " : " + iD;
     }
}
