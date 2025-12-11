package org.example.view;

import org.example.*;
import org.example.menus.MenuContratos;
import org.example.utilitarios.Teclado;
import org.example.utilitarios.Video;

import java.util.List;

public class VendaView {

    private PedidoController pedidoController;
    private ClienteController clienteController;
    private ProdutoController produtoController;

    public VendaView() {
        this.pedidoController = new PedidoController();
        this.clienteController = new ClienteController();
        this.produtoController = new ProdutoController();
    }

    public void executar() {
        boolean executando = true;
        while (executando) {
            int opcao = MenuContratos.exibir();
            switch (opcao) {
                case 1:
                    registrarNovaVenda();
                    break;
                case 2:
                    listarVendas();
                    break;
                case 3:
                    new ProdutoView().executar();
                    break;
                case 4:
                    executando = false;
                    break;
            }
        }
    }

    private void registrarNovaVenda() {
        Video.cabecalho("Registrar Nova Venda");

        System.out.println("Clientes Disponíveis");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            Video.mensagemErro("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }
        long clienteId = Teclado.readInt("Digite o ID do Cliente: ");

        long funcId = Teclado.readInt("Digite o ID do Funcionário (ou 0 se não houver): ");

        Pedido novoPedido = pedidoController.iniciarNovoPedido(clienteId, (funcId == 0) ? null : funcId);
        if (novoPedido == null) {
            Video.mensagemErro("Erro ao iniciar pedido.");
            return;
        }

        boolean adicionandoItens = true;
        while (adicionandoItens) {
            System.out.println("\n--- Produtos Disponíveis ---");
            List<Produto> produtos = produtoController.listarProdutos();
            if (produtos.isEmpty()) {
                Video.mensagemErro("Nenhum produto cadastrado.");
                adicionandoItens = false;
                continue;
            }
            for (Produto p : produtos) {
                System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | R$ " + p.getPreco() + " | Estoque: " + p.getQuantidadeEstoque());
            }

            long produtoId = Teclado.readInt("\nDigite o ID do Produto (ou 0 para finalizar): ");
            if (produtoId == 0) {
                adicionandoItens = false;
                continue;
            }

            int qtd = Teclado.readInt("Digite a Quantidade: ");

            boolean sucessoAdd = pedidoController.adicionarItemAoPedido(novoPedido, produtoId, qtd);
            if (sucessoAdd) {
                Video.mensagemOk("Item adicionado!");
            } else {
                Video.mensagemErro("Erro ao adicionar item (Produto não encontrado ou estoque insuficiente).");
            }
        }

        if (novoPedido.getItens().isEmpty()) {
            Video.mensagemAlerta("Pedido cancelado (nenhum item adicionado).");
            return;
        }

        // 6. Finalizar Pagamento
        System.out.println("\nValor Total do Pedido: R$" + novoPedido.getValorTotal());
        int tipoPgto = Teclado.readInt("Forma de Pagamento: (1) Dinheiro (2) PIX: ");

        MetodoPagamento metodo;
        if (tipoPgto == 1) {
            metodo = new PagamentoDinheiro();
        } else if (tipoPgto == 2) {
            metodo = new PagamentoPix();
        } else {
            Video.mensagemErro("Pagamento cancelado. Pedido não salvo.");
            return;
        }

        boolean sucessoFinal = pedidoController.finalizarPedido(novoPedido, metodo);
        if (sucessoFinal) {
            Video.mensagemOk("Venda registrada e salva no banco!");
        } else {
            Video.mensagemErro("Falha no pagamento.");
        }
        Video.pausa();
    }

    private void listarVendas() {
        Video.cabecalho("Listar Vendas Registradas");
        List<Pedido> pedidos = pedidoController.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        }

        for (Pedido pedido : pedidos) {
            System.out.println(Video.NEGRITO + "\nPedido ID: " + pedido.getId() + " Data: " + pedido.getDataPedido() + Video.RESET);
            System.out.println("Cliente: " + pedido.getCliente().getNome());

            if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
                System.out.println("Itens:");
                for (ItemPedido item : pedido.getItens()) {
                    System.out.println("  - " + item.getProduto().getNome() + " (Qtd: " + item.getQuantidade() + ")");
                }
            } else {
                System.out.println("Itens: (Informação indisponível)");
            }

            System.out.println("Total: R$" + pedido.getValorTotal() + " Pago: " + pedido.isPago());
        }
        Video.pausa();
    }
}