package exception;

import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class IngredienteRepetidoException extends HamburguesaException {

    public IngredienteRepetidoException() {
        super("Existe un ingrediente repetido!");
    }
}

class ProductoRepetidoException extends HamburguesaException {

    public ProductoRepetidoException() {
        super("Existe un producto del menú repetido!");
    }
}

class PedidoSuperiorException extends PedidoException{

    public PedidoSuperiorException() {
        super("Su pedido ha superado el limite permitido de $150000");
    }
}
public class CustomExceptions {

    public void checkRepeatedIngrediente(ArrayList<Ingrediente> list) throws HamburguesaException {
        Set<String> ingredientesSet = new HashSet<String>();

        for (Ingrediente ingrediente : list) {
            if (!ingredientesSet.add(ingrediente.getNombre())) {
                throw new IngredienteRepetidoException();
            }
        }
    }


    public void checkRepeatedProductoMenu(ArrayList<ProductoMenu> list) throws HamburguesaException {
        Set<String> productosSet = new HashSet<String>();

        for (ProductoMenu producto : list) {
            if (!productosSet.add(producto.getNombre())) {
                throw new ProductoRepetidoException();
            }
        }
    }

    public void checkPedidoSuperiorProducto(Pedido pedido, Producto producto) throws PedidoException{

        if ((pedido.getPrecioTotalPedido() + producto.getPrecio())>=150000)  throw new PedidoSuperiorException();

    }
    public void checkPedidoSuperiorIngrediente(Pedido pedido, Ingrediente ingrediente, ProductoMenu productoMenu) throws PedidoException {
        if ((pedido.getPrecioTotalPedido() + productoMenu.getPrecio()+ ingrediente.getCostoAdicional())>=150000) throw new PedidoSuperiorException();
    }
}
