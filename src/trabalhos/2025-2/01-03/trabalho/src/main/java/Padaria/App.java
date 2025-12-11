package Padaria;

import Padaria.model.interfaces.Menu;
import Padaria.model.menus.Cardapio;
import Padaria.model.menus.MenuClientes;
import Padaria.model.menus.MenuProducao;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class App {
    public static void iniciar(){
        
        Video.mensagem("Carregando...");
        Video.barraProgresso(50, 15);
        Video.limparTela();
    }
    
    public static void menuInicial(){
    int opcao;
    do {
        Video.limparTela();
        Video.cabecalho("Padaria");
        Video.mensagem("                                       Selecione uma opção:");
        Video.separador();
        Video.mensagem("[1] Aba Cardapio");
        Video.separador();
        Video.mensagem("[2] Aba Clientes");
        Video.separador();
        Video.mensagem("[3] Area da Producao");
        Video.separador();
        Video.mensagem("[0] Sair da Aplicação");
        Video.separador();
        Video.rodape("Selecione uma opçao");

        
        opcao = Teclado.readInt("");

      
        switch(opcao) {
                case 1:

                    Menu menuCardapio = new Cardapio();
                    menuCardapio.executarMenu();

                    break;
                case 2:
                   Menu menuClientes= new MenuClientes();
                   menuClientes.executarMenu();
                    break;
                case 3:

                    Menu menuProducao = new MenuProducao();
                    menuProducao.executarMenu();
                    break;
                    
                case 0:
                    Video.mensagem("Saindo...");
                    break;
                default:
                    Video.mensagemErro("Opção inválida!");
            }
        
    } while (opcao != 0);
}



    public static void main(String[] args) {
        iniciar();
        
        menuInicial();
       

    }
}
