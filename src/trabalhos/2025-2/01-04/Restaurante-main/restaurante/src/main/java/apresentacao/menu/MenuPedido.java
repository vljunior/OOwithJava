package apresentacao.menu;
import crud.CrudCliente;
import modelos.*;
import utilitarios.*;

public class MenuPedido {
    public static void exibir( CrudCliente crudCliente) {
        Video.limparTela();
        Video.separador();

        if(crudCliente.getQuantidadeClientes() == 0) {

            Video.mensagemAlerta("Nenhum cliente cadastrado no sistema!");
            
            String opcao = Teclado.readString("\nDeseja cadastrar um cliente? (s/n): ");

            if (Character.toLowerCase(opcao.charAt(0)) == 'n') {
                Video.limparTela();
                Video.mensagemInfo("Precissa cadastrar um cliente para continuar.");
                Video.pausa();
                return;
            }
            if (Character.toLowerCase(opcao.charAt(0)) == 's') {
                Video.limparTela();
                crudCliente.adicionarCliente();
                Video.pausa();
            }
            if (Character.toLowerCase(opcao.charAt(0)) != 's') {
                Video.mensagemInfo("opcao invalida. Voltando ao menu principal...");
                Video.pausa();
                return;
            }
        }

        crudCliente.listarClientes();
        System.out.print("Digite o número do cpf do cliente que fará o pedido: "); 
        String cpf = Teclado.readString();
        Cliente cliente = crudCliente.buscarClientePorCpf(cpf);

        if (cliente == null) {
            Video.mensagemErro("Cliente inválido. Voltando ao menu principal.");
            Video.finalizarTela();
            return;
        }

        Garcom garcom = new Garcom("ivonei", 18, "12345678900", 01, 0.10);
        Pedido pedido = new Pedido(cliente, garcom);
        Cardapio cardapio = new Cardapio();
        
        int opcaoPedido;

        do {
            Video.limparTela();
            Video.cabecalho("Menu de pedidos");
            System.out.println("1 - Ver cardapio");
            System.out.println("2 - Adicionar prato ao pedido");
            System.out.println("3 - Ver resumo do pedido");
            System.out.println("4 - Finalizar pedido");
            Video.separador();

            opcaoPedido = Teclado.readInt("Escolha uma opção: ");
            switch (opcaoPedido) {
                case 1:
                    cardapio.exibirPratos();
                    Video.finalizarTela();
                    break;

                case 2:
                    cardapio.exibirPratos();
                    int pratoEscolhido = Teclado.readInt("Digite o número do prato que deseja adicionar: ") - 1;
                    if (pratoEscolhido < 0 || pratoEscolhido >= cardapio.getQuantidadePratos()) {
                        Video.mensagemErro("Prato inválido.");
                    } 
                    else {
                        pedido.adicionarPrato(cardapio.getPrato(pratoEscolhido));
                        Video.mensagemOk("Prato adicionado ao pedido.");
                    }
                    Video.finalizarTela();
                    break;
                    
                case 3:
                    pedido.exibirDetalhesPedido();
                    Video.finalizarTela();
                    break;
                case 4:
                    Video.limparTela();
                    MenuPagamento.exibir(pedido);
                    Video.finalizarTela();
                    break;
                default:
                    Video.mensagemErro("Opção inválida. Tente novamente.");
            }

        } while (opcaoPedido != 4);
    }        
}
