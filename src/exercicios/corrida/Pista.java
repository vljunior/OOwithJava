package exercicios.corrida;

public class Pista {
    private String nome;
    private int distanciaPorVolta;
    private int voltasTotais;

    public Pista(String nome, int distanciaPorVolta, int voltasTotais) {
        this.nome = nome;
        this.distanciaPorVolta = distanciaPorVolta;
        this.voltasTotais = voltasTotais;
    }

    public String getNome() {
        return nome;
    }

    public int getDistanciaPorVolta() {
        return distanciaPorVolta;
    }

    public int getVoltasTotais() {
        return voltasTotais;
    }
}
