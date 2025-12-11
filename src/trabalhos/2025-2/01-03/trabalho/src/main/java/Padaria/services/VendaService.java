package Padaria.services;

import java.util.List;
import Padaria.model.entities.Cliente;
import Padaria.model.entities.Produto;
import Padaria.repository.RepositorioBinario;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class VendaService {

    private final ClienteService clienteService;
    private final RepositorioBinario<Produto> repoProdutos;
    
    public VendaService() {
        this.clienteService = new ClienteService();
        this.repoProdutos = new RepositorioBinario<>("produtos"); 
    }
    
    public void realizarVenda() {
        Video.cabecalho("REALIZAR VENDA");
        
        Cliente cliente = selecionarCliente();
        if (cliente == null) return;
        
        List<Produto> carrinho = new java.util.ArrayList<>();
        List<Integer> quantidades = new java.util.ArrayList<>();
        double total = 0.0;
        
        boolean continuar = true;
        while (continuar) {
            Video.limparTela();
            exibirCarrinho(cliente, carrinho, quantidades, total);
            
            Produto produto = selecionarProduto();
            if (produto == null) {
                continuar = confirmar("Produto não selecionado. Continuar?");
                continue;
            }
            
            int qtd = solicitarQuantidade(produto);
            if (qtd <= 0) {
                continuar = confirmar("Quantidade inválida. Continuar?");
                continue;
            }
            
            if (!produto.estaDisponivel(qtd)) {
                Video.mensagemErro("Estoque insuficiente! Disponível: " + produto.getQuantidadeAtual());
                continuar = confirmar("Continuar?");
                continue;
            }
            
            carrinho.add(produto);
            quantidades.add(qtd);
            total += produto.getPreco() * qtd;
            Video.mensagemOk(produto.getNome() + " adicionado!");
            
            continuar = confirmar("Adicionar outro produto?");
        }
        
        if (carrinho.isEmpty()) {
            Video.mensagemInfo("Nenhum produto selecionado.");
            return;
        }
        
        exibirResumoFinal(cliente, carrinho, quantidades, total);
        
        if (!confirmar("Confirmar venda?")) {
            Video.mensagemInfo("Venda cancelada.");
            return;
        }
        
        processarEstoque(carrinho, quantidades);
        Video.mensagemOk("Venda finalizada! Total: R$ " + String.format("%.2f", total));
    }
    
    private void exibirCarrinho(Cliente cliente, List<Produto> carrinho, List<Integer> quantidades, double total) {
        Video.mensagem("Cliente: " + cliente.getNome());
        Video.separador(30);
        
        if (carrinho.isEmpty()) {
            Video.mensagem("Carrinho vazio");
        } else {
            Video.mensagem("ITENS NO CARRINHO:");
            for (int i = 0; i < carrinho.size(); i++) {
                Produto p = carrinho.get(i);
                int qtd = quantidades.get(i);
                Video.mensagem(String.format("  %s x%d = R$ %.2f", p.getNome(), qtd, p.getPreco() * qtd));
            }
            Video.mensagem("TOTAL PARCIAL: R$ " + String.format("%.2f", total));
        }
        Video.separador(30);
    }
    
    private void exibirResumoFinal(Cliente cliente, List<Produto> carrinho, List<Integer> quantidades, double total) {
        Video.limparTela();
        Video.cabecalho("RESUMO DA VENDA");
        Video.mensagem("Cliente: " + cliente.getNome());
        Video.separador(30);
        
        Video.mensagem("ITENS:");
        for (int i = 0; i < carrinho.size(); i++) {
            Produto p = carrinho.get(i);
            int qtd = quantidades.get(i);
            Video.mensagem(String.format("  %s x%d = R$ %.2f", p.getNome(), qtd, p.getPreco() * qtd));
        }
        
        Video.separador(20);
        Video.mensagem("TOTAL: R$ " + String.format("%.2f", total));
        Video.separador(30);
    }
    
    private void processarEstoque(List<Produto> carrinho, List<Integer> quantidades) {
        for (int i = 0; i < carrinho.size(); i++) {
            Produto produto = carrinho.get(i);
            produto.removerEstoque(quantidades.get(i));
            repoProdutos.atualizar(produto);
        }
    }
    
    private Cliente selecionarCliente() {
        List<Cliente> clientes = clienteService.listarTodosClientes();
        if (clientes.isEmpty()) {
            Video.mensagemErro("Nenhum cliente cadastrado.");
            return null;
        }
        
        clientes.forEach(c -> Video.mensagem("ID: " + c.getId() + " - " + c.getNome()));
        int id = Teclado.readInt("ID do cliente:");
        Cliente cliente = clienteService.buscarCliente(id);
        
        if (cliente == null) Video.mensagemErro("Cliente não encontrado!");
        return cliente;
    }
    
    private Produto selecionarProduto() {
        listarProdutos();
        int id = Teclado.readInt("ID do produto:");
        Produto produto = repoProdutos.buscarPorId(id);
        
        if (produto == null) Video.mensagemErro("Produto não encontrado!");
        return produto;
    }
    
    private void listarProdutos() {
        List<Produto> produtos = repoProdutos.listarTodos();
        if (produtos.isEmpty()) {
            Video.mensagemInfo("Nenhum produto cadastrado.");
            return;
        }
        produtos.forEach(p -> 
            Video.mensagem("ID: " + p.getId() + " - " + p.getNome() + " - R$ " + p.getPreco() + " - Estoque: " + p.getQuantidadeAtual())
        );
    }
    
    private int solicitarQuantidade(Produto produto) {
        Video.mensagem("Produto: " + produto.getNome());
        Video.mensagem("Preço: R$ " + produto.getPreco());
        Video.mensagem("Estoque: " + produto.getQuantidadeAtual());
        int quantidade = Teclado.readInt("Quantidade:");
        
        if (quantidade <= 0) Video.mensagemErro("Quantidade inválida!");
        return quantidade;
    }
    
    private boolean confirmar(String mensagem) {
        return Teclado.readString(mensagem + " (S/N):").equalsIgnoreCase("S");
    }
}