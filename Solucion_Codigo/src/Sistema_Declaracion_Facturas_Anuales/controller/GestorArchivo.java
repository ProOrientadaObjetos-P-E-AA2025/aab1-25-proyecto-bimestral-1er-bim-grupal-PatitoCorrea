package Sistema_Declaracion_Facturas_Anuales.controller;

import Sistema_Declaracion_Facturas_Anuales.*;
import java.io.FileWriter;
import java.io.IOException;

public class GestorArchivo {
  public static void guardarEnArchivo(String nombre, double ingreso, double deduccion, double base, double impuesto) {
        try (FileWriter writer = new FileWriter("declaracion_" + nombre + ".txt")) {
            writer.write("DECLARACIÓN DE IMPUESTOS\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Ingreso Anual: $" + ingreso + "\n");
            writer.write("Deducciones: $" + deduccion + "\n");
            writer.write("Base Imponible: $" + base + "\n");
            writer.write("Impuesto a Pagar: $" + impuesto + "\n");
            System.out.println("Declaración guardada exitosamente en archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar la declaración: " + e.getMessage());
        }
    }
}
