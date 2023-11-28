package model;

import console.Aplicacion;

import java.util.List;

public class Combo implements Producto{

    private double descuento;
    private String nombreCombo;

    private List<String> listCombo;


    public Combo(String nombreCombo, double descuento,List<String> listCombo){
        this.descuento = descuento;
        this.nombreCombo = nombreCombo;
        this.listCombo = listCombo;
    }

    public int getPrecio(){

        int precioTotal = 0;


        for (ProductoMenu arrayMenu : Aplicacion.restaurante.arrayMenu) {
            if (listCombo.contains(arrayMenu.nombre)){
                precioTotal += (int) (arrayMenu.precioBase*(100-descuento)/100);
            }
        }
        return precioTotal;
    }

    public int getCalorias(){

        int calorias = 0;

        for (ProductoMenu arrayMenu : Aplicacion.restaurante.arrayMenu) {
            if (listCombo.contains(arrayMenu.nombre)){
                calorias += (int) (arrayMenu.calorias);
            }
        }

        for (Bebida bebida : Aplicacion.restaurante.arrayBebida) {
            if (listCombo.contains(bebida.nombre)){
                calorias+= (int) (bebida.calorias);
            }
        }

        return calorias;
    }
    public String getNombre(){return nombreCombo;}

    public List<String> getListCombo(){return listCombo;}
    public String generarTextoFactura(){

        return getNombre() + " ***** $"+ getPrecio();
    }
}
