package console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import exception.CustomExceptions;
import exception.PedidoException;
import model.*;
public class Aplicacion {

    CustomExceptions customExceptions =new CustomExceptions();
    public static Restaurante restaurante = new Restaurante();
    private static Aplicacion aplicacion = new Aplicacion();

    //private static model.InputManager inputManager = new model.InputManager();
    public void mostrarMenu(){
        boolean condition = true;

        while (condition){
            System.out.println("\nOpciones de la aplicacion\n");
            System.out.println("1. Mostrar el menu");
            System.out.println("2. Iniciar el pedido");
            System.out.println("3. Salir");
            try {
                int opcionSeleccionada = Integer.parseInt(InputManager.input("Por favor seleccione una opción"));

                ejecutarOpcion(opcionSeleccionada);

                if (opcionSeleccionada == 3){
                    condition = false;
                } else if (opcionSeleccionada == 2) {
                    aplicacion = new Aplicacion();
                }

            }catch (NumberFormatException e){
                System.out.println("Seleccione uno de los números de las opciones disponibles.");
            }
        }


    }

    public void ejecutarOpcion(int opcionSeleccionada){

        System.out.println("\n-------- MENU --------\n");
        if (opcionSeleccionada == 1){

            System.out.println("\nEstos son los combos del restaurante: ");
            int counter = 1;
            for (Combo combo : restaurante.arrayCombo) {

                System.out.println(counter+". " + combo.getNombre() +": $"+combo.getPrecio() + " === Contenido: "+ String.join(" ,",combo.getListCombo())+".");
                counter++;
            }
            int counter2 = 1;

            System.out.println("\nEste es el menu del restaurante: ");
            for (ProductoMenu arrayMenu : restaurante.arrayMenu) {
                System.out.println(counter2+". "+ arrayMenu.getNombre() + ": $"+arrayMenu.precioBase);
                counter2++;
            }

            int counter3 = 1;
            System.out.println("\nEstas son las bebidas disponibles: ");
            for (Bebida bebida : restaurante.arrayBebida) {
                System.out.println(counter3+". "+ bebida.getNombre() + ": $"+bebida.precioBase);
                counter3++;
            }


        } else if (opcionSeleccionada == 2) {
            inicioPedido();

        } else if (opcionSeleccionada == 3) {
            System.out.println("Saliendo de la aplicación...");
        }else {
            System.out.println("Por favor seleccione una opción válida.");
        }

    }

    public  void inicioPedido(){
        boolean condition = true;
        System.out.println("\n *** PEDIDO ***");
        String nombre = InputManager.input("Ingrese su nombre");
        String direccion = InputManager.input("Ingrese su dirección");
        restaurante.iniciarPedido(nombre,direccion);

        System.out.println("");

        while (condition){
            String menu = "1. Productos del menú general\n" + "2. Combos\n" + "3. Bebidas";
            System.out.println(menu);

            int opcion= Integer.parseInt(InputManager.input("\n Ingrese el numero de acuerdo al tipo de producto que desea pedir"));

            if (opcion== 1){
                ordenMenu();
            } else if (opcion == 2) {
                ordenCombos();
            } else if (opcion == 3) {
                ordenBebidas();
            }else{
                System.out.println("Seleccione una opcion valida!!");
            }

            int addOpcion = Integer.parseInt(InputManager.input("Agregar otro tipo producto(MENU, COMBO, BEBIDAS) \n1.Si \n2.No\n Marque su opción"));

            if (addOpcion == 2){
                restaurante.cerrarGuardarPedido();
                restaurante.printFactura();
                condition = false;
            }
        }

    }

    public void ordenMenu(){
        boolean condition = true;
        while (condition) {
            ArrayList<Integer> ingredientesAddInteger = new ArrayList<>();
            ArrayList<Integer> ingredientesDelInteger = new ArrayList<>();

            int indexOrder = Integer.parseInt(InputManager.input("Digite el numero que corresponde al producto del menú que usted quiere"));

            int addOpcion = Integer.parseInt(InputManager.input("Agregar ingrediente a su producto \n1.Si \n2.No\n Marque su opción"));

            if (addOpcion == 1) {

                boolean innerCondition = true;

                while (innerCondition){

                    int contador = 1;
                    System.out.println("\n--- INGREDIENTES --");
                    for (Ingrediente ingrediente : restaurante.arrayIngrediente) {
                        System.out.println(contador+". "+ingrediente.getNombre()+ " ****** $" + ingrediente.getCostoAdicional());
                        contador +=1;
                    }
                    int indexOrderIngredientes = Integer.parseInt(InputManager.input("Digite el numero que corresponde al producto del menú que usted quiere"));
                    try {
                        customExceptions.checkPedidoSuperiorIngrediente(restaurante.getPedidoEnCurso(),restaurante.arrayIngrediente.get(indexOrderIngredientes - 1), restaurante.arrayMenu.get(indexOrder-1));
                        ingredientesAddInteger.add(indexOrderIngredientes);
                    }catch (PedidoException e){
                        System.out.println("\n ---- IMPORTANTE ----");
                        System.out.println(e.getMessage());
                        System.out.println(" ---- IMPORTANTE ---- \n");
                    }


                    int addOpcionInner = Integer.parseInt(InputManager.input("¿Quiere seguir añadiendo ingredientes? \n1.Si \n2.No\n Marque su opción"));
                    if (addOpcionInner == 2){innerCondition = false;}
                }
            } else if (addOpcion == 2) {

            } else{
                System.out.println("Escriba una opcion valida!!");
            }

            int delOpcion = Integer.parseInt(InputManager.input("Eliminar ingrediente a su producto \n1.Si \n2.No\n Marque su opción"));
            if (delOpcion == 1) {

                boolean innerCondition = true;

                while (innerCondition){

                    int contador = 1;
                    System.out.println("--- INGREDIENTES --");
                    for (Ingrediente ingrediente : restaurante.arrayIngrediente) {
                        System.out.println(contador+". "+ingrediente.getNombre()+ " ****** $" + ingrediente.getCostoAdicional());
                        contador +=1;
                    }
                    int indexOrderIngredientes = Integer.parseInt(InputManager.input("Digite el numero que corresponde al producto del menú que usted quiere"));
                    ingredientesDelInteger.add(indexOrderIngredientes);

                    int addOpcionInner = Integer.parseInt(InputManager.input("¿Quiere seguir eliminando ingredientes? \n1.Si \n2.No\n Marque su opción"));
                    if (addOpcionInner == 2){innerCondition = false;}
                }
            } else if (delOpcion == 2) {

            } else{
                System.out.println("Escriba una opcion valida!!");
            }

            if (delOpcion == 2 && addOpcion == 2){
                restaurante.addProductoPedido(2,indexOrder);
            } else{
                restaurante.addProductoAjustado(indexOrder, ingredientesAddInteger, ingredientesDelInteger);
            }

            int exitOpcion = Integer.parseInt(InputManager.input("¿Quiere seguir ordenando productos del menú? \n1.Si \n2.No\n Marque su opción"));
            if (exitOpcion == 2){condition = false;}
        }
    }
    public void ordenCombos(){
        boolean condition = true;
        while (condition){
            int indexOrder = Integer.parseInt(InputManager.input("Digite el numero que corresponde al combo que usted quiere"));
            int times = Integer.parseInt(InputManager.input("¿Cuantos combos de "+restaurante.arrayCombo.get(indexOrder-1).getNombre()+" quiere?\n Digite el número de productos(tiene que ser entero)"));

            for (int i = 0; i < times; i++) {
                restaurante.addProductoPedido(3,indexOrder);
            }

            int opcion = Integer.parseInt(InputManager.input("\n¿Desea continuar ordenando combos?\n Marque su opción:\n 1.Si\n 2.No\n"));

            if (opcion == 2){
                condition = false;
            }
        }
    }
    public void ordenBebidas(){

        boolean condition = true;
        while (condition){
            int indexOrder = Integer.parseInt(InputManager.input("Digite el numero que corresponde a la bebida que usted quiere"));
            int times = Integer.parseInt(InputManager.input("¿Cuantas bebidas de "+restaurante.arrayBebida.get(indexOrder-1).getNombre()+" quiere?\n Digite el número de productos(tiene que ser entero)"));

            for (int i = 0; i < times; i++) {
                restaurante.addProductoPedido(1,indexOrder);
            }

            int opcion = Integer.parseInt(InputManager.input("\n¿Desea continuar ordenando bebidas?\n Marque su opción:\n 1.Si\n 2.No\n"));

            if (opcion == 2){
                condition = false;
            }
        }
    }
    public static void main(String[] args){

        aplicacion.mostrarMenu();
    }

}
