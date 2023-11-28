package test;
import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductoAjustadoTest {
    private ArrayList<Ingrediente> ingredientesAdd;
    private ArrayList<Ingrediente> ingredientesDel;

    private ProductoMenu base = new ProductoMenu("corral",14000,500);


    //Get nombre build no es necesario ya que se verifica con getNombre()
    @Test
    void getPrecio(){
        ingredientesAdd = new ArrayList<>();
        ingredientesDel = new ArrayList<>();

        ingredientesAdd.add(new Ingrediente("piña",2500,120));
        ingredientesDel.add(new Ingrediente("piña",2500,120));

        ProductoAjustado productoAjustado = new ProductoAjustado(base,ingredientesAdd,ingredientesDel);

        assertEquals(14000,productoAjustado.getPrecio());
    }

    @Test
    void getCalorias() {
        ingredientesAdd = new ArrayList<>();
        ingredientesDel = new ArrayList<>();

        ingredientesAdd.add(new Ingrediente("piña",2500,120));
        ingredientesDel.add(new Ingrediente("piña",2500,120));

        ProductoAjustado productoAjustado = new ProductoAjustado(base,ingredientesAdd,ingredientesDel);

        assertEquals(500,productoAjustado.getCalorias());
    }

    @Test
    void generarTextoFactura() {
        ingredientesAdd = new ArrayList<>();
        ingredientesDel = new ArrayList<>();

        ingredientesAdd.add(new Ingrediente("piña",2500,120));
        ingredientesDel.add(new Ingrediente("piña",2500,120));

        ProductoAjustado productoAjustado = new ProductoAjustado(base,ingredientesAdd,ingredientesDel);

        assertEquals("corral con piña sin piña ***** $14000",productoAjustado.generarTextoFactura());

    }

}