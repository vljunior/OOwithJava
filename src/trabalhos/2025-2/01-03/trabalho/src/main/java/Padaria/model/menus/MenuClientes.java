// Arquivo: Padaria/model/menus/MenuClientes.java
package Padaria.model.menus;

import Padaria.model.interfaces.Menu;
import Padaria.services.ClienteService;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class MenuClientes implements Menu {
    private ClienteService clienteService;
    
    public MenuClientes() {
        this.clienteService = new ClienteService();
    }
    
    @Override
    public void executarMenu() {
        int opcao;
        do {
            exibirCabecalho();
            exibirOpcoes();
            opcao = Teclado.readInt("Opção: ");
            processarOpcao(opcao);
            
            if (opcao != 0) {
                Video.pausa();
            }
            
        } while (opcao != 0);
    }
    
    @Override
    public void exibirCabecalho() {
        Video.limparTela();
        Video.cabecalho("GERENCIAR CLIENTES");
        Video.escreverLento("Cadastro e gestão de clientes", 40);
        Video.separador();
    }
    
    @Override
    public void exibirOpcoes() {
        Video.mensagem("[1] Cadastrar Novo Cliente");
        Video.mensagem("[2] Listar Todos os Clientes");
        Video.mensagem("[3] Editar Cliente");
        Video.mensagem("[4] Remover Cliente");
        Video.mensagem("[0] Voltar ao Menu Principal");
    }
    
    @Override
    public void processarOpcao(int opcao) {
        switch(opcao) {
            case 1:
                clienteService.cadastrarCliente();
                break;
            case 2:
                clienteService.listarClientes();
                break;
            case 3:
                clienteService.editarCliente();
                break;
            case 4:
                clienteService.removerCliente();
                break;
            case 0:
                Video.mensagem("Voltando ao menu principal...");
                break;
            default:
                Video.mensagemErro("Opção inválida!");
        }
    }
}