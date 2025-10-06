package exercicios.dado;

class Jogador {
    String nome;
    int ultimaJogada;

    Jogador(String nome) {
        this.nome = nome;
    }

    void jogarDado(Dado dado) {
        ultimaJogada = dado.rolar();
    }
}
