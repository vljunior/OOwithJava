package exercicios.corrida;

public class Piloto {
    private String nome;

    public Piloto(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Piloto [getNome()=" + getNome() + "]";
    }

    public String getNome() {
        return nome;
    }
}

