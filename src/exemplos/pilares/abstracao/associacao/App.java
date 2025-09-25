package exemplos.pilares.abstracao.associacao;
import utilitarios.Teclado; //utilização de pacote e classe

/* Regras de encapsulamento e visibilidade não são aplicados nesta App. Propositalmente!
   Objetivo de aplicar abstração, sobrepor construtor padrão, separar partes de abstração
   em classes distintas e "definir" onde ficam partes do código e razão!
   Utilizar classes utilitárias.
   Praticar lógica.
 * Autor: Professor Lorenzon 2025-2.
 */

public class App {
    public static void main(String[] args) {
        Jogador j1 = new Jogador(Teclado.readString("Informe o nome do Jogador 1: "));
        Jogador j2 = new Jogador(Teclado.readString("Informe o nome do Jogador 2: "));

        if (j1.nome.equals(j2.nome)) {
            System.out.println("Ajustando jogadores com nomes iguais...");
            j1.nome = j1.nome + " 1";
            j2.nome = j2.nome + " 2";
        }

        Dado dado = new Dado(Teclado.readInt("Quantos lados tem o dado?"));
        JogoDeDados jogo = new JogoDeDados(j1, j2, dado);

        jogo.iniciar();
    }
}
