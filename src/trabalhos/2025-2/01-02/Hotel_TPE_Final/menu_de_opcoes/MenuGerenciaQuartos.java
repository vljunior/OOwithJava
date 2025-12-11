package menu_de_opcoes;

import gerencia_quarto.GerenciadorQuarto;
import utilitarios.Teclado;
import utilitarios.Video;

public class MenuGerenciaQuartos {
    private GerenciadorQuarto gerenciadorQuarto;

    public MenuGerenciaQuartos(GerenciadorQuarto gerenciadorQuarto) {
        this.gerenciadorQuarto = gerenciadorQuarto;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n================================" );
            System.out.println("===== Gerencia de Quartos ====="    );
            System.out.println("1. Listar quartos"                  );
            System.out.println("2. Cadastrar novo quarto"           );
            System.out.println("3. Liberar Quarto"                  );
            System.out.println("4. Excluir quarto"                  );
            System.out.println("0. Voltar ao menu principal"        );
            System.out.println("================================"   );
            System.out.print("Escolha: ");
            
            try {
                opcao = Teclado.readInt();

                if (!processarOpcao(opcao)) {
                    break;
                }
            } catch (Exception e) {
                Video.mensagemErro("Erro inesperado: " + e.getMessage());
                Teclado.readString();
            }
        } while (true);
    }

    private boolean processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                gerenciadorQuarto.listarQuartos();
                return true;

            case 2:
                gerenciadorQuarto.cadastrarNovoQuarto();
                return true;

            case 3:
                gerenciadorQuarto.liberarQuarto();
                return true;

            case 4:
                gerenciadorQuarto.excluirQuarto();
                return true;

            case 0:
                System.out.println("Voltando ao menu principal...");
                return false;

            default:
                System.out.println("Opção inválida!");
                return true;
        }
    }
}