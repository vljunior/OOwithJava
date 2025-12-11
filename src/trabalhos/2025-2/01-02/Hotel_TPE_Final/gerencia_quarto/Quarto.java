package gerencia_quarto;

public abstract class Quarto {
    private int numero;
    private String tipo;
    private String status;

    public Quarto(int numero, String tipo, String status) {
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public abstract double getValorDiaria();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String exibirInfo() {
        return "Quarto " + numero + " (" + tipo + ") - " + status;
    }

    @Override
    public String toString() {
        return numero + ";" + tipo + ";" + status;
    }
}