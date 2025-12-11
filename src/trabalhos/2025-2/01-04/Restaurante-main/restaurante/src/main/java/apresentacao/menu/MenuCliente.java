package apresentacao.menu;
import crud.CrudCliente;
import utilitarios.Video;

public class MenuCliente {

    public static void exibir(CrudCliente crudCliente) {
        int opcaoCrud;

        do {
            opcaoCrud = MenuPessoa.exibir(); // mostra o menu de opções de cliente

            switch (opcaoCrud) {
                case 1:
                    crudCliente.adicionarCliente();
                    break;
                case 2:
                    crudCliente.listarClientes();
                    break;
                case 3:
                    crudCliente.atualizarCliente();
                    break;
                case 4:
                    crudCliente.deletarCliente();
                    break;
                case 5:
                    Video.mensagem("Voltando ao menu principal...");
                    break;
                default:
                    Video.mensagemErro("Opção inválida! Tente novamente.");
            }

        } while (opcaoCrud != 5); 
    }
}
