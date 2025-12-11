import menus.MenuPrincipal;
import menus.MenuPedido;
import menus.MenuPessoa;
import gerenciador.GerenciadorDeClientes;
import gerenciador.GerenciadorPedidos;
import utils.Teclado;

public class App {

    public static void main(String[] args) {

        GerenciadorDeClientes gerenciadorClientes = new GerenciadorDeClientes();
        GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos(gerenciadorClientes);

        while (true) {
            int opc = MenuPrincipal.exibir();
            switch (opc) {
                case 1: 
                    boolean voltar = false;
                    while (!voltar) {
                        int escolha = MenuPessoa.exibir();
                        switch (escolha) {
                            case 1:
                                gerenciadorClientes.listarCLientes();
                                break;
                            case 2:
                                gerenciadorClientes.localizarCliente();
                                break;
                            case 3:
                            System.out.println(gerenciadorClientes.cadastrarCliente());
                                break;
                            case 4:
                                gerenciadorClientes.listarCLientes();
                                System.out.println(gerenciadorClientes.removerCliente());
                                break;
                            case 5:
                                gerenciadorClientes.atualizarCliente();
                                break;
                        
                            case 6:
                                voltar = true;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }  
                        if (!voltar) {
                            System.out.println("Pressione Enter para continuar...");
                            try { System.in.read(); } catch (Exception e) { }
                        }
                    }
                    break;
                case 2: 
                    int opcaoPedido = MenuPedido.exibir();
                    switch (opcaoPedido) {
                        case 1:
                            gerenciadorPedidos.listarPedidos();
                            break;
                        case 2:
                            int idLocalizar = Teclado.readInt("Digite o ID do pedido que deseja localizar:");
                            gerenciadorPedidos.localizarPedido(idLocalizar);
                            break;
                        case 3:
                            gerenciadorPedidos.cadastrarPedido();
                            break;
                            case 4:
                                gerenciadorPedidos.removerPedido();
                                break;
                            case 5:
                                gerenciadorPedidos.atualizarPedido();
                                break;
                        case 6:
                            voltar = true;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            try { System.in.read(); } catch (Exception e) { }

                            break;
                    }
                    System.out.println("Pressione Enter para continuar...");
                    try { System.in.read(); } catch (Exception e) { }
                    break;
                case 3: // Sair
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida no menu principal.");
            }
        }
    }
}
