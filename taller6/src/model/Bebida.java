package model;

public class Bebida implements Producto{

    String nombre;
    public int precioBase;
    int calorias;

    public Bebida(String nombre, int precioBase, int calorias){
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.calorias = calorias;
    }
    public int getCalorias(){return calorias;};

    public String getNombre(){return nombre;};
    public int getPrecio(){return precioBase;}
    public String generarTextoFactura(){return getNombre()+ " ***** $" +getPrecio();}

}
