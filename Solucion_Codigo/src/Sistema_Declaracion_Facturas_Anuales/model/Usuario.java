package Sistema_Declaracion_Facturas_Anuales.model;

import Sistema_Declaracion_Facturas_Anuales.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private double[] sueldosMensuales = new double[12];
    private List<Factura> facturas = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void registrarSueldo(int mes, double sueldo) {
        if (mes >= 1 && mes <= 12) {
            sueldosMensuales[mes - 1] = sueldo;
        }
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
    }

    public double calcularIngresoAnual() {
        double total = 0;
        for (double sueldo : sueldosMensuales) {
            total += sueldo;
        }
        return total;
    }

    public double calcularDeducciones() {
        double total = 0;
        for (Factura factura : facturas) {
            total += factura.getValor();
        }
        return Math.min(total, 5000);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }
}
