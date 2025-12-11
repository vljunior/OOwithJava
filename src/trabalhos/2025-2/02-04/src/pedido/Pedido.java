package pedido;

import cliente.Cliente;
import produto.Produto;
import java.util.ArrayList;
import execoes.IdNotFind;
import gerenciador.GerenciadorDeClientes;

import java.io.Serializable;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    GerenciadorDeClientes gerenciador = new GerenciadorDeClientes();
    private Cliente cliente = new Cliente(0, "", "", "");
    private int id_pedido;
    private boolean statusPago;
    private ArrayList<Produto> listaProdutosPedidos;

    public Pedido(int id_pedido, Cliente cliente){

        System.out.println("<><>Cadastro de Pedido<><>" );
        this.cliente = cliente;
        this.id_pedido = id_pedido;
        this.statusPago = false;
        this.listaProdutosPedidos = new ArrayList<>();
    }

    public Cliente getCliente(){
        return cliente;
    }

    public int GetIdPedido(){
        return id_pedido;
    }

    public boolean getStatusPago(){
        return statusPago;
    }

    public ArrayList<Produto> getlistaProdutos(){
        return listaProdutosPedidos;
    }


    public void setStatusPagamento(String senha, boolean statusPago){ //pagamento só poderá ser alterado por meio de validação!
        String senhaAdm = "senha123";
        if (senhaAdm.equals(senha)){
            this.statusPago = statusPago;
            System.out.printf("Pagamento confirmado.");
        }else{
            System.out.printf("Acesso negado!");
        }
    }

    public void buscarProduto(int id){
        if (listaProdutosPedidos == null || id < 0 || id >= listaProdutosPedidos.size()) {
            throw new IdNotFind("Produto não encontrado no pedido");
        }
        System.out.println(listaProdutosPedidos.get(id));
    }
}
