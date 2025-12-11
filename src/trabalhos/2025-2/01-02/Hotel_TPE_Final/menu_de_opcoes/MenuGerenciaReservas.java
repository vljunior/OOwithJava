package menu_de_opcoes;

import gerencia_quarto.GerenciadorQuarto;
import gerencia_reserva.GerenciadorReserva;
import utilitarios.Teclado;
import utilitarios.Video;

public class MenuGerenciaReservas {
    private GerenciadorReserva gerenciadorReserva;

    public MenuGerenciaReservas(GerenciadorReserva gerenciadorReserva, GerenciadorQuarto hotel) {
        this.gerenciadorReserva = gerenciadorReserva;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n==========================================" );
            System.out.println("========== Gerencia de Reservas =========="   );
            System.out.println("1. Nova reserva     (em desenvolvimento)"     );
            System.out.println("2. Listar reservas  (em desenvolvimento)"     );
            System.out.println("3. Check-in"                                  );
            System.out.println("4. Check-out"                                 );
            System.out.println("5. Cancelar reserva (em desenvolvimento)"     );
            System.out.println("6. Buscar reserva   (em desenvolvimento)"     );
            System.out.println("0. Voltar ao menu principal"                  );
            System.out.println("=========================================="   );
            System.out.print("Escolha: ");
            
            try {                
                opcao = Teclado.readInt();

                if (!processarOpcao(opcao)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                Teclado.readString();
            }
        } while (true);
    }

    private boolean processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Funcionalidade desativada.");
                return true;

            case 2:
                System.out.println("Funcionalidade desativada.");
                return true;

            case 3:
                Video.limparTela();
                gerenciadorReserva.realizarCheckIn();
                return true;

            case 4:
                Video.limparTela();
                gerenciadorReserva.realizarCheckOut();
                return true;

            case 5:
                System.out.println("Funcionalidade desativada.");
                return true;

            case 6:
                System.out.println("Funcionalidade desativada.");
                return true;

            case 0:
                Video.limparTela();
                System.out.println("Voltando ao menu principal...");
                return false;

            default:
                System.out.println("Opção inválida!");
                return true;
        }
    }
}