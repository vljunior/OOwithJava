package Padaria.model.menus;

import Padaria.model.interfaces.Menu;
import Padaria.services.VendaService;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class Cardapio implements Menu {

    private VendaService vendaService;
    
    public Cardapio() {
        this.vendaService = new VendaService();
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
        Video.cabecalho("CARDÁPIO E VENDAS");
        Video.separador();
    }

    @Override
    public void exibirOpcoes() {
        Video.mensagem("[1] Realizar Venda");
        Video.mensagem("[2] Listar Produtos Disponíveis");
        Video.mensagem("[0] Voltar");
    }

    @Override 
    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                vendaService.realizarVenda(); 
                break;
            case 2:
                Video.mensagemInfo("Os produtos serão listados durante a venda.");
                break;
            case 0:
                Video.mensagem("Voltando ao menu principal...");
                break;
            default:
                Video.mensagemErro("Opção inválida!");
        }
    }
}