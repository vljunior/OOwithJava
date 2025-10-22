package exemplos.pilares.abstracao.associacao;

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
