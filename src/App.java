import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Notacion nt = new Notacion();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n==== CONVERSOR DE NOTACION INFIJA A POSTFIJA ====");
            System.out.println("1. Convertir cadena");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("\nIngrese la expresión infija: ");
                    String cadena = scanner.nextLine();
                    nt.convertir(cadena);
                    break;
                case "2":
                    continuar = false;
                    System.out.println("Cerrando el programa");
                    break;
                default:
                    System.out.println("Opción no valida. Intente nuevamente.\n");
                    break;
            }
        }

        scanner.close();
    }
}
