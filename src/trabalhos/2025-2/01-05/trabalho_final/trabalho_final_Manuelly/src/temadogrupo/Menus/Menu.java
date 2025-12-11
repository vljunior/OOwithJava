package temadogrupo.Menus;

import java.util.ArrayList;
import util.Teclado;
import util.Video;

public class Menu {
    private String titulo; 
    private ArrayList<String> opcoesMenu; // Lista das opções que o menu terá

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
        //só sai desse lopp quando a opção for válida
        do {
            System.out.print("Qual a sua opção: ");
            opcaoEscolhida = Teclado.readInt(); 
            if (opcaoEscolhida < 1 || opcaoEscolhida > opcoesMenu.size()) {
                Video.mensagemAlerta("Opção inválida, tente novamente!");
            }
        } while (opcaoEscolhida < 1 || opcaoEscolhida > opcoesMenu.size()); // Continua pedindo se for inválido
        return opcaoEscolhida;
    }
}

