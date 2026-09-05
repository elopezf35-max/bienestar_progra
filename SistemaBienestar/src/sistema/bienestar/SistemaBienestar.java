package sistema.bienestar;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBienestar {

    private static Scanner teclado = new Scanner(System.in);

    private static ArrayList<Persona> registros
            = new ArrayList<>();

    public static void main(String[] args) {
        int seleccion = 0;

        while (seleccion != 4) {
            mostrarMenu();

            seleccion = leerEntero();

            switch (seleccion) {
                case 1:
                    guardarRegistro();
                    break;

                case 2:
                    imprimirRegistros();
                    break;

                case 3:
                    consultarRegistro();
                    break;

                case 4:
                    System.out.println(
                            "Gracias por utilizar el sistema");
                    break;

                default:
                    System.out.println(
                            "La opcion ingresada no existe");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println(
                "\nCONTROL DE BIENESTAR UNIVERSITARIO");
        System.out.println("1. Ingresar nuevo registro");
        System.out.println("2. Consultar listado general");
        System.out.println("3. Localizar registro");
        System.out.println("4. Cerrar sistema");
        System.out.print("Ingrese una opcion: ");
    }

    // Lee un entero validando que el usuario no escriba texto
    private static int leerEntero() {
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Lee un decimal validando el formato
    private static double leerDecimal() {
        while (true) {
            try {
                return Double.parseDouble(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print(
                        "Valor invalido, ingrese solo numeros: ");
            }
        }
    }

    // Lee una fecha validando el formato AAAA-MM-DD
    private static LocalDate leerFecha() {
        while (true) {
            try {
                return LocalDate.parse(teclado.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.print(
                        "Formato invalido, use AAAA-MM-DD: ");
            }
        }
    }

    public static void guardarRegistro() {
        System.out.println("\nNUEVO REGISTRO");

        System.out.print("Nombre y apellido: ");
        String nombre = teclado.nextLine();

        System.out.print(
                "Fecha de nacimiento (AAAA-MM-DD): ");
        LocalDate fecha = leerFecha();

        System.out.print("Peso en kg: ");
        double peso = leerDecimal();

        System.out.print("Estatura en metros: ");
        double estatura = leerDecimal();

        System.out.print("Lugar de residencia: ");
        String direccion = teclado.nextLine();

        System.out.print("Numero de celular: ");
        String telefono = teclado.nextLine();

        System.out.print("Correo: ");
        String correo = teclado.nextLine();

        Persona nuevoRegistro = new Persona(
                nombre,
                fecha,
                peso,
                estatura,
                direccion,
                telefono,
                correo
        );

        if (nuevoRegistro.aceptarRegistro()) {
            registros.add(nuevoRegistro);

            System.out.println(
                    "Registro almacenado correctamente");

            System.out.printf(
                    "IMC obtenido: %.2f%n",
                    nuevoRegistro.obtenerIndiceCorporal());

            System.out.println(
                    "Estado: "
                    + nuevoRegistro.obtenerEstadoNutricional());
        } else {
            System.out.println(
                    "Solamente se aceptan personas adultas");
        }
    }

    public static void imprimirRegistros() {
        if (registros.size() == 0) {
            System.out.println(
                    "El listado se encuentra vacio");
        } else {
            for (int posicion = 0;
                    posicion < registros.size();
                    posicion++) {

                System.out.println(
                        "\nRegistro numero " + (posicion + 1));

                registros.get(posicion).imprimirFicha();
            }
        }
    }

    public static void consultarRegistro() {
        System.out.print(
                "Escriba el nombre que desea localizar: ");

        String texto = teclado.nextLine().toLowerCase();
        boolean existe = false;

        for (Persona registro : registros) {
            if (registro.getNombre()
                    .toLowerCase().contains(texto)) {

                registro.imprimirFicha();
                existe = true;
            }
        }

        if (!existe) {
            System.out.println(
                    "No se encontraron coincidencias");
        }
    }
}
