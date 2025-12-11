package gerenciador;

import pedido.Pedido;
import produto.Produto;
import execoes.IdNotFind;
import cliente.Cliente;
import java.util.ArrayList;
import java.util.List;
import repositorio.Repositorio;
import repositorio.RepositorioBinario;
import utils.Teclado;
    

public class GerenciadorPedidos {

    private List<Pedido> listaGerenciadorPedidos;
    private Repositorio<Pedido> repositorio;
    private GerenciadorDeClientes gerenciadorClientes; //guardando a instância de gerenciador de clientes por injeção
    public GerenciadorPedidos(GerenciadorDeClientes gerenciadorClientes){
        this.gerenciadorClientes = gerenciadorClientes;
        this.repositorio = new RepositorioBinario<>("data/pedidos.dat");
        try {
            this.listaGerenciadorPedidos = repositorio.listar();
        } catch (Exception e) {
            this.listaGerenciadorPedidos = new ArrayList<>();
            System.out.println("Aviso: não foi possível carregar pedidos persistidos: " + e.getMessage());
        }
    }

    public Pedido buscarPedidoGerenciador(int id){ //cada método deve ser único de sua classe, ficando óbvio que por qual classe é usada
        for (Pedido x : listaGerenciadorPedidos){        // Em casos como esse colocar o nome da classe ao final para diferenciar da classe pedido
            if (x.GetIdPedido() == id){
                return x;
            }
        }
        System.out.println("Pedido não encontrado!");
        return null;
    }
    
    public String cadastrarPedido() { // DEVE ADICIONAR UM CLIENTE AO PEDIDO JÁ CADASTRADO

    try {

        System.out.println("<><>Cadastrar Pedido<><>");
        int idPedido = Teclado.readInt("Digite o ID do pedido:");

        for (Pedido p : listaGerenciadorPedidos) {
            if (p.GetIdPedido() == idPedido) {
                return "ID de pedido já existe. Tente outro ID.";
            }
        }

        Pedido pedido = null;
        try{
        int idCliente = Teclado.readInt("Digite o ID do cliente para este pedido:");
        Cliente cliente = this.gerenciadorClientes.buscarId(idCliente);
        pedido = new Pedido(idPedido, cliente);
        } catch (IdNotFind e) {
            return "Cliente não encontrado. Cadastre o cliente antes de criar um pedido.";
        }

        boolean adicionando = true;
        while (adicionando) {
            String nomeProduto = Teclado.readString("Nome do produto:");
            double valorUnit = Teclado.readDouble("Valor unitário do produto:");
            int quantidade = Teclado.readInt("Quantidade que o cliente pediu:");
            
            Produto produto = new Produto(nomeProduto, valorUnit);
            produto.addProduto(nomeProduto, quantidade);

            for (int i = 0; i < quantidade; i++) {
                pedido.getlistaProdutos().add(produto);
            }
            System.out.println("Produto adicionado ao pedido.");

            String resp = Teclado.readString("Deseja adicionar outro produto? (s/n)");
            if (!resp.equalsIgnoreCase("s")) {
                adicionando = false;
            }
        }

        listaGerenciadorPedidos.add(pedido);
        try {
            repositorio.salvar(listaGerenciadorPedidos);
        } catch (Exception e) {
            System.out.println("Erro ao salvar pedidos: " + e.getMessage());
        }
        return "Pedido cadastrado com sucesso!";

    } catch (IdNotFind e) {
        return "Cliente não encontrado. Cadastre o cliente antes de criar um pedido.";
    } catch (Exception e) {
        return "Erro ao cadastrar pedido: " + e.getMessage();
    }
}

    public void listarPedidos(){

        if (this.listaGerenciadorPedidos == null || this.listaGerenciadorPedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
    }

    for (pedido.Pedido p : this.listaGerenciadorPedidos) {
        System.out.println("Pedido ID: " + p.GetIdPedido());
        cliente.Cliente c = p.getCliente();
        System.out.println("Cliente: " + c.getNomeCliente() + " (ID: " + c.getIdCliente() + ")");
        System.out.println("Status do pagamento: " + (p.getStatusPago() ? "Pago" : "Pendente"));

        java.util.ArrayList<produto.Produto> produtos = p.getlistaProdutos();
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Produtos: (nenhum)");
        } else {
            System.out.println("Produto: " + produtos.get(0).getNomeProduto());
            System.out.println("Total de produtos: " + produtos.size());
        }
        System.out.println("----------");
    }
    }

    public void removerPedido(){
        if (this.listaGerenciadorPedidos == null || this.listaGerenciadorPedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        int id = utils.Teclado.readInt("Digite o ID do pedido que deseja remover:");
        for (int i = 0; i < listaGerenciadorPedidos.size(); i++) {
            if (listaGerenciadorPedidos.get(i).GetIdPedido() == id) {
                listaGerenciadorPedidos.remove(i);
                System.out.println("Pedido removido com sucesso!");
                try { repositorio.salvar(listaGerenciadorPedidos); } catch (Exception e) { System.out.println("Erro ao salvar pedidos: " + e.getMessage()); }
                return;
            }
        }
        System.out.println("Pedido não encontrado.");
    }

    public void atualizarPedido(){
        if (this.listaGerenciadorPedidos == null || this.listaGerenciadorPedidos.isEmpty()){
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        int id = utils.Teclado.readInt("Digite o ID do pedido que deseja atualizar:");
        for (int i = 0; i < listaGerenciadorPedidos.size(); i++) {
            if (listaGerenciadorPedidos.get(i).GetIdPedido() == id) {
                listaGerenciadorPedidos.remove(i);
                System.out.println("Digite os novos dados do pedido:");
                this.cadastrarPedido();
                try { repositorio.salvar(listaGerenciadorPedidos); } catch (Exception e) { System.out.println("Erro ao salvar pedidos: " + e.getMessage()); }
                System.out.println("Pedido atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Pedido não encontrado.");
    }

    public Pedido localizarPedido(int id){
        if (this.listaGerenciadorPedidos == null || this.listaGerenciadorPedidos.isEmpty()) {
        System.out.println("Nenhum pedido cadastrado.");
        return null;
    }

    for (pedido.Pedido p : this.listaGerenciadorPedidos) {
        if (p.GetIdPedido() == id) {
            return p;
        }
    }
    System.out.println("Pedido não encontrado.");
    return null;
    }

}

