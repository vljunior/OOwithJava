package org.example;

import java.util.List;

public class PedidoController {

    private PedidoService pedidoService;
    private ClienteService clienteService;
    private FuncionarioService funcionarioService;
    private ProdutoService produtoService;

    public PedidoController() {
        this.pedidoService = new PedidoService();
        this.clienteService = new ClienteService();
        this.funcionarioService = new FuncionarioService();
        this.produtoService = new ProdutoService();
    }

    public Pedido iniciarNovoPedido(Long clienteId, Long funcionarioId) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId);

        if (cliente == null) {
            System.out.println("CONTROLLER: Cliente com ID " + clienteId + " não encontrado. Pedido cancelado.");
            return null;
        }

        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(cliente);
        novoPedido.setFuncionario(funcionario);

        return novoPedido;
    }

    public boolean adicionarItemAoPedido(Pedido pedido, Long produtoId, int quantidade) {
        Produto produto = produtoService.buscarPorId(produtoId);

        if (produto == null) {
            System.out.println("CONTROLLER: Produto com ID " + produtoId + " não encontrado.");
            return false;
        }

        if (produto.getQuantidadeEstoque() < quantidade) {
            System.out.println("CONTROLLER: Estoque insuficiente para " + produto.getNome());
            return false;
        }

        ItemPedido item = new ItemPedido(produto, quantidade);
        pedido.adicionarItem(item);

        return true;
    }

    public boolean finalizarPedido(Pedido pedido, MetodoPagamento metodoPagamento) {
        pedido.setMetodoPagamento(metodoPagamento);

        boolean sucesso = pedido.finalizarPedido();

        if (sucesso) {
            pedidoService.salvar(pedido);
            System.out.println("CONTROLLER: Pedido salvo no banco de dados.");
        } else {
            System.out.println("CONTROLLER: Pagamento recusado. Pedido não foi salvo.");
        }

        return sucesso;
    }

    public List<Pedido> listarPedidos() {
        return pedidoService.listarTodos();
    }

    public Pedido buscarPedido(Long id) {
        return pedidoService.buscarPorId(id);
    }

    public void removerPedido(Long id) {
        pedidoService.remover(id);
    }
}