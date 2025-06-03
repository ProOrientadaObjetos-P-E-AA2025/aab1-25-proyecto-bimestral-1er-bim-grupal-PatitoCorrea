package Sistema_Declaracion_Facturas_Anuales;

public class Factura {
    private String categoria;
    private double valor;

    public Factura(String categoria, double valor) {
        this.categoria = categoria;
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValor() {
        return valor;
    }
}
