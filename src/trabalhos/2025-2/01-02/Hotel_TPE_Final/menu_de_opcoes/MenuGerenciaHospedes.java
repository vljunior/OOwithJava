package menu_de_opcoes;

import gerencia_hospede.GerenciadorHospede;
import utilitarios.Teclado;
import utilitarios.Video;

public class MenuGerenciaHospedes {
    private GerenciadorHospede gerenciadorHospede;

    public MenuGerenciaHospedes(GerenciadorHospede gerenciadorHospede) {
        this.gerenciadorHospede = gerenciadorHospede;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n================================" );
            System.out.println("===== Gerencia de Hospedes ====="   );
            System.out.println("1. Listar hospedes"                 );
            System.out.println("2. Cadastrar novo hospede"          );
            System.out.println("3. Buscar hospede"                  );
            System.out.println("4. Excluir hospede"                 );
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
                Video.limparTela();
                gerenciadorHospede.listarHospedes();
                return true;

            case 2:
                Video.limparTela();
                gerenciadorHospede.cadastrarNovoHospede();
                return true;

            case 3:
                Video.limparTela();
                gerenciadorHospede.buscarHospede();
                return true;

            case 4:
                Video.limparTela();
                gerenciadorHospede.excluirHospede();
                return true;

            case 0:
                Video.limparTela();
                System.out.println("Voltando ao menu principal...");
                return false;

            default:
                Video.mensagemAlerta("Opção inválida!");
                return true;
        }
    }

}