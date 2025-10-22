package exercicios.dado;

class JogoDeDados {
    
    Jogador jogador1;
    Jogador jogador2;
    Dado dado;

    JogoDeDados(Jogador j1, Jogador j2, Dado d) {
        jogador1 = j1;
        jogador2 = j2;
        dado = d;
    }

    void iniciar() {
        System.out.println("\nJogo de dados entre dois jogadores!");
        System.out.println("O jogador que obtiver o maior lance ganhará ou haverá empate, tendo mais um lance!");
        System.out.printf("\nQue role o dado de %d lados!!!", dado.lados);

        boolean vencedor = false;

        do {
            jogador1.jogarDado(dado);
            jogador2.jogarDado(dado);

            System.out.println("\n" + jogador1.nome + " tirou " + jogador1.ultimaJogada);
            System.out.println(jogador2.nome + " tirou " + jogador2.ultimaJogada);

            if (jogador1.ultimaJogada > jogador2.ultimaJogada) {
                System.out.println(jogador1.nome + " venceu! Placar: " +
                                   jogador1.ultimaJogada + " x " + jogador2.ultimaJogada);
                vencedor = true;
            } else if (jogador2.ultimaJogada > jogador1.ultimaJogada) {
                System.out.println(jogador2.nome + " venceu! Placar: " +
                                   jogador1.ultimaJogada + " x " + jogador2.ultimaJogada);
                vencedor = true;
            } else {
                System.out.println("Empate! Nova jogada...");
            }
        } while (!vencedor);

        System.out.println("\nFim de jogo!");
    }
}
