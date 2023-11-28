package model;

import java.util.ArrayList;
import java.util.StringJoiner;

public class ProductoAjustado implements Producto{

    private  ArrayList<Ingrediente> ingredientesAdd;
    private  ArrayList<Ingrediente> ingredientesDel;
    private ProductoMenu base;
    public ProductoAjustado(ProductoMenu base, ArrayList<Ingrediente> ingredientesAdd , ArrayList<Ingrediente> ingredientesDel){
        this.base =base;
        this.ingredientesAdd = ingredientesAdd;
        this.ingredientesDel= ingredientesDel;
    }

    public String getNombre() {
        String nombreBase = base.getNombre() + " ";
        String stringAdd = nombreBuild("con", ingredientesAdd);
        String stringDel = nombreBuild("sin", ingredientesDel);

        return nombreBase + stringAdd + " " + stringDel;
    }

    private String nombreBuild(String prefijo, ArrayList<Ingrediente> ingredientes) {
        if (ingredientes.isEmpty()) {
            return "";
        }

        StringJoiner joiner = new StringJoiner(", ");

        for (Ingrediente ingrediente : ingredientes) {
            joiner.add(ingrediente.getNombre());
        }

        return prefijo + " " + joiner;
    }


    public int getPrecio(){
        int precio = 0;
        precio += base.getPrecio();

        for (Ingrediente ingrediente : ingredientesAdd) {
            precio += ingrediente.getCostoAdicional();

        }
        return  precio;
    }
    public int getCalorias(){
        int calorias =0;
        calorias += base.getCalorias();

        for (Ingrediente ingrediente : ingredientesAdd) {
            calorias += ingrediente.getCalorias();
        }

        for (Ingrediente ingrediente : ingredientesDel) {
            calorias -= ingrediente.getCalorias();
        }


        return calorias;}

    public String generarTextoFactura(){
        return getNombre()+" ***** $"+getPrecio();
    }
}
