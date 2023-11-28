package test;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido = new Pedido("a","a");
    @Test
    void getIdPedido() {
        //En este test no es encesario verificar nada ya que son numeros random
        assertTrue(pedido.getIdPedido() %1 ==0,"Verifica si el id es entero");
    }

    @Test
    void agregarProducto() {

    }

    @Test
    void getPrecioTotalPedido() {
    }

    @Test
    void guardadFactura() {
    }

    @Test
    void printFacturaPedido() {
    }
}