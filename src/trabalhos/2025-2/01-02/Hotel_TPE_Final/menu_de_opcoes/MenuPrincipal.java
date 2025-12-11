package menu_de_opcoes;

import gerencia_hospede.GerenciadorHospede;
import gerencia_quarto.GerenciadorQuarto;
import gerencia_reserva.GerenciadorReserva;
import utilitarios.Teclado;
import utilitarios.Video;

public class MenuPrincipal {
    private GerenciadorQuarto hotel;
    private GerenciadorReserva gerenciadorReserva;
    private GerenciadorHospede gerenciadorHospede;

    public MenuPrincipal() {
        this.hotel = new GerenciadorQuarto();
        this.gerenciadorReserva = new GerenciadorReserva(this.hotel);
        this.gerenciadorHospede = new GerenciadorHospede();
    }

    public void exibirMenu() {
        int opcao;

        do {
            Video.escreverLento("Iniciando o sistema...", 100);
            Video.barraProgresso(40, 100);
            System.out.println("\n================================" );
            System.out.println("===== Sistema de Hotelaria ====="   );
            System.out.println("1. Gerencia de Reservas"            );
            System.out.println("2. Gerencia de Quartos"             );
            System.out.println("3. Gerencia de Hospedes"            );
            System.out.println("0. Sair"                            );
            System.out.println("================================"   );
            System.out.print("Escolha: ");
            
            try {
                opcao = Teclado.readInt();

                processarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                Teclado.readString();
            }
        } while (true);
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                Video.limparTela();
                MenuGerenciaReservas menuReserva = new MenuGerenciaReservas(gerenciadorReserva, hotel);
                menuReserva.exibirMenu();
                break;

            case 2:
                Video.limparTela();
                MenuGerenciaQuartos menuQuartos = new MenuGerenciaQuartos(hotel);
                menuQuartos.exibirMenu();
                break;

            case 3:
                Video.limparTela();
                MenuGerenciaHospedes menuHospedes = new MenuGerenciaHospedes(gerenciadorHospede);
                menuHospedes.exibirMenu();
                break;

            case 0:
                Video.limparTela();
                Video.escreverLento("Encerrando o sistema...", 100);
                Video.barraProgresso(40, 100);
                System.exit(0);
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }
}