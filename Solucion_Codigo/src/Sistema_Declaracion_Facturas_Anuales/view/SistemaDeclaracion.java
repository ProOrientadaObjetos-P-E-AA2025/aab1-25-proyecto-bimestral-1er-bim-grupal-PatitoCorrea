package Sistema_Declaracion_Facturas_Anuales.view;

import Sistema_Declaracion_Facturas_Anuales.model.Usuario;
import Sistema_Declaracion_Facturas_Anuales.model.Factura;
import Sistema_Declaracion_Facturas_Anuales.controller.GestorArchivo;
import Sistema_Declaracion_Facturas_Anuales.controller.CalcularImpuesto;
import java.util.Scanner;

public class SistemaDeclaracion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        Usuario usuario = new Usuario(nombre);

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      REGISTRO DE SUELDOS MENSUALES       ║");
        System.out.println("╚══════════════════════════════════════════╝");
        for (int i = 1; i <= 12; i++) {
            System.out.print("Sueldo del mes " + i + ": $");
            double sueldo = scanner.nextDouble();
            usuario.registrarSueldo(i, sueldo);
        }

        System.out.println("╔════════════════════════════════╗");
        System.out.println("║      REGISTRO DE FACTURAS      ║");
        System.out.println("╚════════════════════════════════╝");
        boolean continuar = true;
        scanner.nextLine();
        while (continuar) {
            System.out.println("Categorías disponibles:");
            System.out.println("1. Vivienda\n2. Educación\n3. Alimentación\n4. Vestimenta\n5. Salud\n6. Turismo");
            System.out.print("Seleccione una categoría (1-6): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            String categoria;

            switch (opcion) {
                case 1 -> categoria = "Vivienda";
                case 2 -> categoria = "Educación";
                case 3 -> categoria = "Alimentación";
                case 4 -> categoria = "Vestimenta";
                case 5 -> categoria = "Salud";
                case 6 -> categoria = "Turismo";
                default -> {
                    System.out.println("Opción inválida. Intente de nuevo.");
                    continue;
                }
            }

            System.out.print("Ingrese el valor de la factura: $");
            double valor = scanner.nextDouble();
            usuario.agregarFactura(new Factura(categoria, valor));

            boolean respuestaValida = false;
            while (!respuestaValida) {
                System.out.print("¿Desea ingresar otra factura? (s/n): ");
                scanner.nextLine();
                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("s")) {
                    continuar = true;
                    respuestaValida = true;
                } else if (respuesta.equals("n")) {
                    continuar = false;
                    respuestaValida = true;
                } else {
                    System.err.println("Entrada inválida. Por favor ingrese 's' o 'n'.");
                }
            }
        }

        double ingresoAnual = usuario.calcularIngresoAnual();
        double deduccion = usuario.calcularDeducciones();
        double baseImponible = ingresoAnual - deduccion;
        double impuesto = CalcularImpuesto.calcularImpuesto(baseImponible);

        System.out.println("\n======================================================================");
        System.out.println("                          SERVICIO DE RENTAS INTERNAS");
        System.out.println("                       DECLARACIÓN ANUAL DE IMPUESTOS");
        System.out.println("======================================================================");
        System.out.printf ("Contribuyente        : %-50s\n", usuario.getNombre());
        System.out.printf ("Año Fiscal           : %-50s\n", "2023");
        System.out.printf ("Fecha de Emisión     : %-50s\n", java.time.LocalDate.now());
        System.out.println("----------------------------------------------------------------------");
        System.out.printf ("| %-30s | %-30s |\n", "Concepto", "Valor");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf ("| %-30s | $%28.2f |\n", "Ingreso Anual", ingresoAnual);
        System.out.printf ("| %-30s | $%28.2f |\n", "Deducciones Permitidas", deduccion);
        System.out.printf ("| %-30s | $%28.2f |\n", "Base Imponible", baseImponible);
        System.out.printf ("| %-30s | $%28.2f |\n", "Impuesto a Pagar", impuesto);
        System.out.println("======================================================================");
        System.out.println("                           GRACIAS POR SU DECLARACIÓN");
        System.out.println("                Para más información visite www.sri.gob.ec");
        System.out.println("======================================================================\n");

        GestorArchivo.guardarEnArchivo(usuario.getNombre(), ingresoAnual, deduccion, baseImponible, impuesto);

    }
}