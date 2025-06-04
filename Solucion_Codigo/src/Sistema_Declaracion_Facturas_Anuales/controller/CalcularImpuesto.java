package Sistema_Declaracion_Facturas_Anuales.controller;

import Sistema_Declaracion_Facturas_Anuales.*;

public class CalcularImpuesto {
    public static double calcularImpuesto(double base) {
        if (base <= 11722) return 0;
        else if (base <= 14930) return (base - 11722) * 0.05;
        else if (base <= 19385) return (base - 14930) * 0.1 + 160;
        else if (base <= 25638) return (base - 19385) * 0.12 + 606;
        else return (base - 25638) * 0.15 + 1313;
    }
}
