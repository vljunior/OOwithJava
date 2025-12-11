package projeto.example;
import utilitarios.Video;
import crud.CrudCliente;
import apresentacao.menu.*;

public class App {
    public static void main(String[] args) {
        
        int opcao;
        CrudCliente crudCliente = new CrudCliente();
       
        do {
            Video.cabecalho("Restaurante Os Três Chefes");
            System.out.println("Seja muito bem-vindo ao Restaurante Os Três Chefes!\n" + //
                                "Aqui, cada prato é preparado com carinho para tornar sua experiência inesquecível.\n" + 
                                "\nBom apetite!");
            Video.separador();
            Video.pausa();

            do{
                opcao = Menu.exibirExemplo();
                switch (opcao) {
                    case 1:
                        MenuCliente.exibir(crudCliente);
                        break;
                    
                    case 2:
                        MenuPedido.exibir(crudCliente);
                        break;
                    
                    default:
                        break;
                        
                }
            }while(opcao != 3);

        } while (opcao != 3);

        Video.mensagemOk("Obrigado por utilizar nosso restaurante!");
        Video.finalizarTela();
    }
}