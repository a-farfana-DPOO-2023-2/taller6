package model;

import exception.CustomExceptions;
import exception.PedidoException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
public class Pedido{

    //private CustomExceptions customExceptions = new CustomExceptions();
    private int numeroPedidos = 0;

    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;
    private ArrayList<Producto> arrayProductos = new ArrayList<>();

    public Pedido(String nombreCliente, String direccionCliente){
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        setIdPedido();
    }

    private void setIdPedido(){
        Random random = new Random();
        this.idPedido = 1000+random.nextInt(9000);
    }
    public int getIdPedido(){
        return idPedido;
    }

    public void agregarProducto(Producto nuevoItem) {

        arrayProductos.add(nuevoItem);
    }

    private int getCaloriasPedido(){
        int calorias = 0;
        for (Producto arrayProducto : arrayProductos) {
            calorias += arrayProducto.getCalorias();
        }
        return calorias;
    }
    private int getPrecioNetoPedido(){
        int precioNeto = 0;
        for (Producto arrayProducto : arrayProductos) {
            precioNeto += arrayProducto.getPrecio();
        }
        return precioNeto;
    }

    public int getPrecioTotalPedido(){
        return getPrecioNetoPedido() + getPrecioIVAPedido();
    }

    private int getPrecioIVAPedido(){
        return (int) (getPrecioNetoPedido()*0.19);
    }

    private String generarTextoFactura() {
        StringBuilder facturaStringBuilder = new StringBuilder();

        facturaStringBuilder.append(" *** FACTURA ***\n");

        for (Producto arrayProducto : arrayProductos) {
            facturaStringBuilder.append(arrayProducto.generarTextoFactura()).append(" ---- ").append(arrayProducto.getCalorias()).append(" calorias\n");
        }

        facturaStringBuilder.append("Nombre: ").append(nombreCliente).append("\n");
        facturaStringBuilder.append("Direccion: ").append(direccionCliente).append("\n");
        facturaStringBuilder.append("ID model.Pedido: ").append(getIdPedido()).append("\n");
        facturaStringBuilder.append("Iva del pedido: ").append("$"+getPrecioIVAPedido()).append("\n");
        facturaStringBuilder.append("Calorias total del pedido: ").append(getCaloriasPedido()).append("\n");
        facturaStringBuilder.append("Valor total del pedido: ").append("$"+getPrecioTotalPedido()).append("\n");

        return facturaStringBuilder.toString();
    }

    public void guardadFactura(String archivo){
        String factura = "********\n"+generarTextoFactura()+"\n********\n";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))){
            writer.write(factura);
            System.out.println("Factura correctamente guardada en el archivo: "+ archivo);

        }catch (IOException e){
            System.out.println("No se pudo escribir en el archivo"+ e.getMessage()  );
        }
    }

    public String printFacturaPedido(){return  generarTextoFactura();}
}
