package model;
import exception.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {

    public ArrayList<Combo> arrayCombo = new ArrayList<>();
    public ArrayList<ProductoMenu> arrayMenu = new ArrayList<>();
    public ArrayList<Ingrediente> arrayIngrediente = new ArrayList<>();
    public ArrayList<Bebida> arrayBebida = new ArrayList<>();
    private ArrayList<Pedido> arrayPedido = new ArrayList<>();

    private CustomExceptions customExceptions = new CustomExceptions();
    public Restaurante() {
        cargarInformacionRestaurante();
    }

    public void iniciarPedido(String nombre, String direccion) {
        Pedido pedido = new Pedido(nombre, direccion);
        arrayPedido.add(pedido);
    }

    public void cerrarGuardarPedido(){
        Pedido pedido = getPedidoEnCurso();
        pedido.guardadFactura("data/facturas.txt");
    }

    public Pedido getPedidoEnCurso() {
        return arrayPedido.get(arrayPedido.size() - 1);
    }

    private void pedidoIdentico(){
        for (Pedido pedido : arrayPedido) {
            if (pedido.equals(getPedidoEnCurso())){
                System.out.println("Si hubo un pedido igual en el día");
            }
        }

    }

    public void addProductoPedido(int tipoProducto, int indexOrden){
        try {
            Pedido pedido = getPedidoEnCurso();
            Producto producto = null;
            if (tipoProducto == 1) {//Bebidas
                producto = arrayBebida.get(indexOrden - 1);
            } else if (tipoProducto == 2) {//Menu
                producto = arrayMenu.get(indexOrden - 1);
            } else if (tipoProducto == 3) {//model.Combo
                producto = arrayCombo.get(indexOrden - 1);
            }
            customExceptions.checkPedidoSuperiorProducto(pedido,producto);
            pedido.agregarProducto(producto);

        }catch (PedidoException e){
            System.out.println(e.getMessage());
        }
    }

    public void addProductoAjustado(int indexOrder, ArrayList<Integer> ingredientesAddInteger, ArrayList<Integer> ingredientesDelInteger){
        Pedido pedido = getPedidoEnCurso();

        ArrayList<Ingrediente> ingredientesAdd = new ArrayList<>();
        ArrayList<Ingrediente> ingredientesDel = new ArrayList<>();

        if (!ingredientesAddInteger.isEmpty()) {
            for (Integer i : ingredientesAddInteger) {
                ingredientesAdd.add(arrayIngrediente.get(i - 1));
            }
        }


        if (!ingredientesDelInteger.isEmpty()){
            for (Integer i : ingredientesDelInteger) {
                ingredientesDel.add(arrayIngrediente.get(i-1));
            }
        }
        pedido.agregarProducto(new ProductoAjustado(arrayMenu.get(indexOrder-1), ingredientesAdd,ingredientesDel));
    }
    public void printFactura(){
        Pedido pedido = getPedidoEnCurso();
        System.out.println(pedido.printFacturaPedido());
    }

    public ArrayList<ProductoMenu> getMenuBase() {
        return arrayMenu;
    }


    public ArrayList<Ingrediente> getIngredientes(){ return  arrayIngrediente;}

    public void cargarInformacionRestaurante(){
        cargarIngredientes();
        cargarMenu();
        cargarCombos();
        cargarBebidas();
    }

    private void cargarBebidas(){
        try(BufferedReader buffer = new BufferedReader(new FileReader("data/bebidas.txt"))){
            String ln;
            while((ln = buffer.readLine())!=null){
                List<String> lnSplit = List.of(ln.split(";"));
                Bebida bebida = new Bebida(lnSplit.get(0),Integer.parseInt(lnSplit.get(1)),Integer.parseInt(lnSplit.get(2)));
                arrayBebida.add(bebida);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void cargarIngredientes(){

        try(BufferedReader buffer = new BufferedReader(new FileReader("data/ingredientes.txt"))){
            String ln;
            while((ln = buffer.readLine())!=null){
                List<String> lnSplit = List.of(ln.split(";"));
                Ingrediente ingrediente = new Ingrediente(lnSplit.get(0),Integer.parseInt(lnSplit.get(1)),Integer.parseInt(lnSplit.get(2)));
                arrayIngrediente.add(ingrediente);
            }
            customExceptions.checkRepeatedIngrediente(arrayIngrediente);
        }catch (IOException e){
            e.printStackTrace();
        } catch (HamburguesaException e) {
            System.out.println("------IMPORTANTE------");
            System.out.println(e.getMessage());
        }

    }

    private void cargarMenu(){

        try(BufferedReader buffer = new BufferedReader(new FileReader("data/menu.txt"))){
            String ln;
            while((ln = buffer.readLine())!=null){
                List<String> lnSplit = List.of(ln.split(";"));
                ProductoMenu productoMenu = new ProductoMenu(lnSplit.get(0),Integer.parseInt(lnSplit.get(1)), Integer.parseInt(lnSplit.get(2)));
                arrayMenu.add(productoMenu);
            }
            customExceptions.checkRepeatedProductoMenu(arrayMenu);
        }catch (IOException e){
            e.printStackTrace();
        }catch (HamburguesaException e) {
            System.out.println("------IMPORTANTE------");
            System.out.println(e.getMessage());
        }

    }

    private void cargarCombos(){

        try(BufferedReader buffer = new BufferedReader(new FileReader("data/combos.txt"))){
            String ln;
            while((ln = buffer.readLine())!=null){
                List<String> lnSplit = List.of(ln.split(";", 3));
                Combo combo = new Combo(lnSplit.get(0),Double.parseDouble(lnSplit.get(1).trim().replace("%","")), List.of(lnSplit.get(2).split(";")));
                arrayCombo.add(combo);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
