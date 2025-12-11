package gerenciador;


import java.util.ArrayList;
import utils.Teclado;
import cliente.Cliente;
import execoes.IdNotFind;
public class GerenciadorDeClientes{

    ArrayList<Cliente> lista;
    public GerenciadorDeClientes(){
        this.lista = new ArrayList<>();
    }

    public String cadastrarCliente(){
        Cliente cliente;
        
        System.out.println("<><>Cadastrar Pessoa<><>");

        String nome = Teclado.readString("Digite o nome do cliente:");
        int id_cliente = Teclado.readInt("Digite o ID do cliente:");
        String telefone = Teclado.readString("Digite o telefone do cliente:");
        String observacao = Teclado.readString("Digite uma observação sobre o cliente:");

        cliente = new Cliente (id_cliente, nome, telefone, observacao);

        lista.add(cliente);
        return "Cliente cadastrado com sucesso!";
        
    }

    public Cliente buscarId(int id){
        for(Cliente c : lista){
            if(c.getIdCliente() == id){
                return c;
            }
        }
        throw new IdNotFind("ID não encontrado");
    }


    public void listarCLientes(){
        System.out.printf("<><>LISTA DE CLIENTES<><>\n");
        for (Cliente c : lista){
            System.out.println(c);
        }
    }

    public void atualizarCliente(){
        try{
            System.out.printf("<><>Atualizar cliente<><>\n");

                int id = Teclado.readInt("Selecione pelo ID do cliente:");
                Cliente cliente = buscarId(id);
                String newNome = Teclado.readString("Digite o novo nome:");
                String newTelefone = Teclado.readString("Digite o novo telefone:");
                String newObservacao = Teclado.readString("Digite a nova observação:");

                cliente.setNomeCliente(newNome);
                cliente.setTelefone(newTelefone);
                cliente.setObservacao(newObservacao);
                System.out.println("Cliente atualizado com sucesso!");
        }catch(IdNotFind e){
            System.out.println(e.getMessage());
        }
    }


public String removerCliente(){
        try{
            System.out.printf("<><>Remover cliente<><>\n");

                int id = Teclado.readInt("Selecione pelo ID do cliente:");
                Cliente cliente = buscarId(id);
                lista.remove(cliente);
                return "Cliente removido com sucesso!";
        }catch(IdNotFind e){
            return e.getMessage();
        }
    }

    public void localizarCliente(){
        try{
            System.out.printf("<><>Localizar cliente<><>\n");

                int id = Teclado.readInt("Selecione pelo ID do cliente:");
                Cliente cliente = buscarId(id);
                System.out.println(cliente);
        }catch(IdNotFind e){
            System.out.println(e.getMessage());
        }
    }

}



