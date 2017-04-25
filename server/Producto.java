import java.io.*;

class Producto implements Serializable {
     private String nombre;
     private String iD;
     private String descripcion;
     private float precio;
     Producto(String n, String i, String d, float p) {
         nombre = n;
         iD = i;
         descripcion=d;
         precio=p;
     }
     public String getNombre() {
         return nombre;
     }
     public String getPrecio() {
         return precio;
     }
     public String toString() {
         return nombre + " : " + precio;
     }
}
