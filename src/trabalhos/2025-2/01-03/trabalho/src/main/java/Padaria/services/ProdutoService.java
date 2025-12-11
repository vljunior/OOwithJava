package Padaria.services;

import java.util.List;
import java.util.function.Consumer;

import Padaria.model.entities.Produto;
import Padaria.repository.RepositorioBinario;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class ProdutoService {
    private final RepositorioBinario<Produto> repoProdutos;
    private final ReceitaService receitaService;
    
    public ProdutoService() {
        this.repoProdutos = new RepositorioBinario<>("produtos");
        this.receitaService = new ReceitaService();
    }
    
    public void cadastrarProduto() {
        Video.cabecalho("CADASTRAR PRODUTO");
        Produto produto = criarProdutoViaInput();
        repoProdutos.salvar(produto);
        Video.mensagemOk("Produto cadastrado! ID: " + produto.getId());
    }
    
    public void listarProdutos() {
        Video.cabecalho("PRODUTOS CADASTRADOS");
        List<Produto> produtos = repoProdutos.listarTodos();
        if (produtos.isEmpty()) {
            Video.mensagemInfo("Nenhum produto.");
            return;
        }
        produtos.forEach(p -> Video.mensagem(p.toString()));
    }

    public void editarProduto() {
        Video.cabecalho("EDITAR PRODUTO");
        Produto produto = selecionarProduto();
        if (produto == null) return;
        processarEdicao(produto);
    }
    
    public void removerProduto() {
        Video.cabecalho("REMOVER PRODUTO");
        Produto produto = selecionarProduto();
        if (produto == null) return;
        processarRemocao(produto);
    }

    public void fabricarProdutos() {
        Video.cabecalho("FABRICAR PRODUTOS");
        Produto produto = selecionarProdutoComReceita();
        if (produto == null) return;
        processarFabricacao(produto);
    }

    public void diagnosticoProducao() {
        Video.cabecalho("DIAGNÓSTICO PRODUÇÃO");
        List<Produto> produtos = repoProdutos.listarTodos();
        Video.mensagem("Total de produtos: " + produtos.size());
        produtos.forEach(this::exibirDiagnosticoProduto);
    }
    
    // DELEGAÇÃO PARA RECEITA SERVICE
    public void cadastrarReceita() {
        receitaService.cadastrarReceita();
    }
    
    public void recadastrarReceita() {
        receitaService.recadastrarReceita();
    }
    
    public void visualizarReceita() {
        receitaService.visualizarReceita();
    }
    
    // MÉTODOS PRIVADOS
    private Produto criarProdutoViaInput() {
        String nome = Teclado.readString("Nome:");
        double preco = Teclado.readDouble("Preço:");
        return new Produto(nome, preco);
    }
    
    private Produto selecionarProduto() {
        List<Produto> produtos = repoProdutos.listarTodos();
        if (produtos.isEmpty()) {
            Video.mensagemErro("Nenhum produto cadastrado.");
            return null;
        }
        exibirProdutosParaSelecao(produtos);
        return buscarProdutoPorInput();
    }
    
    private void exibirProdutosParaSelecao(List<Produto> produtos) {
        Video.mensagem("PRODUTOS DISPONÍVEIS:");
        produtos.forEach(p -> Video.mensagem("ID: " + p.getId() + " - " + p.getNome() + " - R$ " + p.getPreco()));
    }
    
    private Produto buscarProdutoPorInput() {
        int id = Teclado.readInt("ID do produto:");
        Produto produto = repoProdutos.buscarPorId(id);
        if (produto == null) {
            Video.mensagemErro("Produto não encontrado!");
            return null;
        }
        return produto;
    }
    
    private void processarEdicao(Produto produto) {
        Video.mensagem("Editando: " + produto.getNome());
        editarCamposProduto(produto);
        repoProdutos.atualizar(produto);
        Video.mensagemOk("Produto atualizado!");
    }
    
    private void editarCamposProduto(Produto produto) {
        editarCampo("nome", produto.getNome(), produto::setNome);
        editarCampoDouble("preço", produto.getPreco(), produto::setPreco);
    }
    
    private void processarRemocao(Produto produto) {
        Video.mensagemAlerta("Remover: " + produto.getNome() + " - R$ " + produto.getPreco());
        String confirmacao = Teclado.readString("Confirmar? (S/N):");
        if (!confirmacao.equalsIgnoreCase("S")) {
            Video.mensagemInfo("Remoção cancelada.");
            return;
        }
        repoProdutos.deletar(produto.getId());
        Video.mensagemOk("Produto removido!");
    }
    
    private Produto selecionarProdutoComReceita() {
        List<Produto> produtos = repoProdutos.listarTodos();
        if (produtos.isEmpty()) {
            Video.mensagemErro("Nenhum produto cadastrado.");
            return null;
        }
        
        List<Produto> produtosComReceita = filtrarProdutosComReceita(produtos);
        if (produtosComReceita.isEmpty()) {
            Video.mensagemErro("Nenhum produto com receita cadastrada.");
            return null;
        }
        
        exibirProdutosComReceita(produtosComReceita);
        Produto produto = buscarProdutoPorInput();
        if (produto == null) return null;
        
        if (!receitaService.validarReceitaProduto(produto)) {
            return null;
        }
        return produto;
    }
    
    private List<Produto> filtrarProdutosComReceita(List<Produto> produtos) {
        return produtos.stream()
            .filter(p -> !p.getReceita().isEmpty())
            .toList();
    }
    
    private void exibirProdutosComReceita(List<Produto> produtos) {
        Video.mensagem("PRODUTOS COM RECEITA DISPONÍVEIS:");
        produtos.forEach(p -> Video.mensagem("ID: " + p.getId() + " - " + p.getNome() + " - Estoque: " + p.getQuantidadeAtual()));
    }
    
    private void processarFabricacao(Produto produto) {
        int quantidade = solicitarQuantidadeValida();
        if (quantidade <= 0) return;
        
        if (!receitaService.verificarEstoqueParaProducao(produto, quantidade)) {
            Video.mensagemErro("Estoque insuficiente para fabricação!");
            return;
        }
        
        if (!confirmarFabricacao(produto, quantidade)) {
            Video.mensagemInfo("Fabricação cancelada.");
            return;
        }
        executarFabricacao(produto, quantidade);
    }
    
    private int solicitarQuantidadeValida() {
        int quantidade;
        do {
            quantidade = Teclado.readInt("Quantidade a fabricar: ");
            if (quantidade <= 0) {
                Video.mensagemErro("Quantidade deve ser maior que zero!");
            }
        } while (quantidade <= 0);
        return quantidade;
    }
    
    private boolean confirmarFabricacao(Produto produto, int quantidade) {
        Video.separador(40);
        Video.mensagem("CONFIRMAÇÃO DE FABRICAÇÃO");
        Video.mensagem("Produto: " + produto.getNome());
        Video.mensagem("Quantidade: " + quantidade);
        Video.mensagem("Estoque atual: " + produto.getQuantidadeAtual());
        Video.mensagem("Estoque após: " + (produto.getQuantidadeAtual() + quantidade));
        exibirInsumosParaFabricacao(produto, quantidade);
        Video.separador(40);
        
        String confirmacao = Teclado.readString("Confirmar fabricação? (S/N):");
        return confirmacao.equalsIgnoreCase("S");
    }
    
    private void exibirInsumosParaFabricacao(Produto produto, int quantidade) {
        Video.mensagemInfo("INSUMOS A SEREM CONSUMIDOS:");
        produto.getReceita().forEach(item -> {
            float totalNecessario = item.getQuantidadeNecessaria() * quantidade;
            Video.mensagem("  - " + item.getInsumo().getNome() + ": " + totalNecessario + " " + item.getInsumo().getUnidadeMedida());
        });
    }
    
    private void executarFabricacao(Produto produto, int quantidade) {
        try {
            Video.mensagemInfo("INICIANDO FABRICAÇÃO...");
            receitaService.consumirInsumosProducao(produto, quantidade);
            
            int estoqueAntes = produto.getQuantidadeAtual();
            produto.adicionarEstoque(quantidade);
            repoProdutos.atualizar(produto);
            
            exibirResultadoFabricacao(produto, quantidade, estoqueAntes);
        } catch (Exception e) {
            Video.mensagemErro("Erro durante a fabricação: " + e.getMessage());
        }
    }
    
    private void exibirResultadoFabricacao(Produto produto, int quantidade, int estoqueAntes) {
        Video.separador(40);
        Video.mensagemOk("FABRICAÇÃO CONCLUÍDA COM SUCESSO!");
        Video.mensagem("Produto: " + produto.getNome());
        Video.mensagem("Quantidade fabricada: " + quantidade);
        Video.mensagem("Estoque anterior: " + estoqueAntes);
        Video.mensagem("Estoque atual: " + produto.getQuantidadeAtual());
        Video.separador(40);
    }
    
    private void exibirDiagnosticoProduto(Produto produto) {
        Video.mensagem("\nProduto: " + produto.getNome() + " (ID: " + produto.getId() + ")");
        Video.mensagem("Estoque: " + produto.getQuantidadeAtual());
        Video.mensagem("Receita: " + produto.getReceita().size() + " ingredientes");
        receitaService.exibirReceitaProduto(produto);
    }
    
    private void editarCampo(String nomeCampo, String valorAtual, Consumer<String> setter) {
        String novoValor = Teclado.readString("Novo " + nomeCampo + " [" + valorAtual + "]:");
        if (novoValor.trim().isEmpty()) return;
        setter.accept(novoValor);
    }
    
    private void editarCampoDouble(String nomeCampo, double valorAtual, Consumer<Double> setter) {
        String novoValorStr = Teclado.readString("Novo " + nomeCampo + " [" + valorAtual + "]:");
        if (novoValorStr.trim().isEmpty()) return;
        
        try {
            setter.accept(Double.parseDouble(novoValorStr));
        } catch (NumberFormatException e) {
            Video.mensagemErro("Valor inválido.");
        }
    }
}