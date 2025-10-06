package exercicios.corrida;

public class Carro {
    private String escuderia;
    private int numero;
    private Piloto piloto;
    private int velocidade;
    private int combustivel;
    private final int VELOCIDADE_MAX = 320;
    private final int COMBUSTIVEL_MAX = 100;

    public Carro(String escuderia, int numero, Piloto piloto) {
        this.escuderia = escuderia;
        this.numero = numero;
        this.piloto = piloto;
        this.velocidade = 0; 
        this.combustivel = COMBUSTIVEL_MAX; 
    }

    public void acelerar() {
        if (combustivel > 0) {
            velocidade += 25;
            if (velocidade > VELOCIDADE_MAX) {
                velocidade = VELOCIDADE_MAX;
            }
            combustivel -= 2;
            if (combustivel < 0) combustivel = 0;
        }
    }

    public void frear() {
        velocidade -= 20;
        if (velocidade < 0) {
            velocidade = 0;
        }
    }

    public void reabastecer(int qtd) {
        combustivel += qtd;
        if (combustivel > COMBUSTIVEL_MAX) {
            combustivel = COMBUSTIVEL_MAX;
        }
    }

    public void reabastecerTotal() {
        combustivel = COMBUSTIVEL_MAX;
    }

    public String getEscuderia() {
        return escuderia;
    }

    public int getNumero() {
        return numero;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getCombustivel() {
        return combustivel;
    }

    public boolean temCombustivel() {
        return combustivel > 0;
    }
}
