package model;

public class ProductoMenu implements Producto{

    String nombre;
    public int precioBase;
    int calorias;
    public ProductoMenu(String nombre, int precioBase,int calorias){
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.calorias = calorias;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPrecio(){
        return precioBase;
    }

    public int getCalorias(){return  calorias;}

    public String generarTextoFactura(){
        return getNombre()+" ***** $"+getPrecio();
    }
}
