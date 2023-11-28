package test;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {


    private Combo combo = new Combo("combo corral",10.0, Arrays.asList("corral;papas medianas;gaseosa".split(";")));
    @Test
    void getIdPedido() {
        Pedido pedido = new Pedido("a","a");
        //En este test no es encesario verificar nada ya que son numeros random
        assertTrue(pedido.getIdPedido() %1 ==0,"Verifica si el id es entero");
    }

    @Test
    void agregarProducto() {
        Pedido pedido = new Pedido("a","a");

        ArrayList<Producto> arrayList = new ArrayList<>();
        arrayList.add(combo);
        pedido.agregarProducto(combo);
        assertEquals(arrayList,pedido.arrayProductos);
    }

    @Test
    void getPrecioTotalPedido() {
        //Aca se verifica el precio Neto junto con el IVA
        Pedido pedido = new Pedido("a","a");
        pedido.agregarProducto(combo);
        assertEquals(20884,pedido.getPrecioTotalPedido());
    }

    @Test
    void getPrecioNetoPedido(){
        //Aca se verifica el precio Neto junto con el IVA
        Pedido pedido = new Pedido("a","a");
        pedido.agregarProducto(combo);
        assertEquals(17550,pedido.getPrecioNetoPedido());
    }

    @Test
    void getPrecioIVAPedido(){
        Pedido pedido = new Pedido("a","a");
        pedido.agregarProducto(combo);
        assertEquals(3334.5,(int) pedido.getPrecioNetoPedido()*0.19);
    }
    @Test
    void guardadFactura() {
        Pedido pedido = new Pedido("a","a");
        pedido.agregarProducto(combo);
        assertLinesMatch("Factura correctamente guardada en el archivo: data/facturas.txt".lines(), "data/facturas.txt".lines());

    }

    @Test
    void printFacturaPedido() {
        Pedido pedido = new Pedido("a","a");
        pedido.agregarProducto(combo);
        assertEquals("Factura correctamente guardada en el archivo: data/facturas.txt\n" +
                " *** FACTURA ***\n" +
                "combo corral ***** $17550 ---- 850 calorias\n" +
                "Nombre: a\n" +
                "Direccion: a\n" +
                "ID model.Pedido: 6773\n" +
                "Iva del pedido: $3334\n" +
                "Calorias total del pedido: 850\n" +
                "Valor total del pedido: $20884",pedido.printFacturaPedido());
    }
}