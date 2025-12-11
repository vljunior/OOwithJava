package apresentacao.menu;
import java.util.ArrayList;
import utilitarios.*;

public class Menu {
    private String titulo;
    private ArrayList<String> opcoesMenu;

    public Menu(String titulo, ArrayList<String> opcoesMenu) {
        this.titulo = titulo;
        this.opcoesMenu = opcoesMenu;
    }

    public int exibir() {
        Video.limparTela();
        Video.cabecalho(titulo);
        System.out.println();

        for (int i = 0; i < opcoesMenu.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, opcoesMenu.get(i));
        }

        System.out.println();

        int opcaoEscolhida;

        do {            
            opcaoEscolhida = Teclado.readInt("Qual a sua opcao: "); 
            if (opcaoEscolhida < 1 || opcaoEscolhida > opcoesMenu.size()) {
                Video.mensagemAlerta("Opcao invalida, tente novamente!");
            }            
        } while (opcaoEscolhida < 1 || opcaoEscolhida > opcoesMenu.size());        

        return opcaoEscolhida;
    }

    public static int exibirExemplo() { 
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Menu cliente");
        opcoes.add("Menu restaurante\n");
        opcoes.add("Sair");
        Menu menu = new Menu("Menu de Opcoes", opcoes);
        return menu.exibir();
    }
}
