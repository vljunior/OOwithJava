package Padaria.services;

import java.util.List;

import Padaria.model.entities.Insumo;
import Padaria.model.entities.ItemReceita;
import Padaria.model.entities.Produto;
import Padaria.repository.RepositorioBinario;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class ReceitaService {
    private RepositorioBinario<Produto> repoProdutos = new RepositorioBinario<>("produtos");
    private InsumoService insumoService = new InsumoService();
    private static ReceitaService instance;
    
    public static ReceitaService getInstance() {
        if (instance == null) instance = new ReceitaService();
        return instance;
    }
    
    public void cadastrarReceita() {
        Video.cabecalho("CADASTRAR RECEITA");
        Produto produto = selecionarProduto();
        if (produto != null && validarInsumos()) adicionarIngredientes(produto);
    }

    public void recadastrarReceita() {
        Video.cabecalho("RECADASTRAR RECEITA");
        Produto produto = selecionarProduto();
        if (produto == null) return;
        produto.getReceita().clear();
        Video.mensagemInfo("Receita anterior limpa.");
        cadastrarReceita();
    }
    
    public void visualizarReceita() {
        Video.cabecalho("VISUALIZAR RECEITA");
        Produto produto = selecionarProduto();
        if (produto != null && !produto.getReceita().isEmpty()) exibirReceitaCompleta(produto);
        else Video.mensagemErro("Produto sem receita!");
    }
    
    public boolean validarReceitaProduto(Produto produto) {
        if (produto.getReceita().isEmpty()) {
            Video.mensagemErro("Produto sem receita!");
            return false;
        }
        for (ItemReceita item : produto.getReceita()) {
            if (item.getInsumo() == null) {
                Video.mensagemErro("Receita com insumos inválidos!");
                return false;
            }
        }
        exibirReceita(produto);
        return true;
    }
    
    public void exibirReceitaProduto(Produto produto) {
        Video.mensagemInfo("RECEITA:");
        produto.getReceita().forEach(item -> 
            Video.mensagem("  - " + item.getInsumo().getNome() + ": " + 
                item.getQuantidadeNecessaria() + " " + item.getInsumo().getUnidadeMedida())
        );
    }
    
    public boolean verificarEstoqueParaProducao(Produto produto, int quantidade) {
    Video.mensagemInfo("VERIFICANDO ESTOQUE...");
    boolean estoqueSuficiente = true;
    
    for (ItemReceita item : produto.getReceita()) {
        Insumo insumo = item.getInsumo();
        Insumo insumoAtual = insumoService.buscarInsumoPorId(insumo.getId());
        
        if (insumoAtual == null) {
            Video.mensagemErro("Insumo não encontrado: " + insumo.getNome());
            return false;
        }
        
        float necessario = item.getQuantidadeNecessaria() * quantidade;
        int disponivel = insumoAtual.getQuantidadeAtual();
        
        if (disponivel < necessario) {
            Video.mensagemErro("INSUFICIENTE: " + insumoAtual.getNome() + 
                " (" + necessario + " " + insumoAtual.getUnidadeMedida() + 
                " > " + disponivel + " " + insumoAtual.getUnidadeMedida() + ")");
            estoqueSuficiente = false;
        } else {
            Video.mensagemOk(insumoAtual.getNome() + ": " + disponivel + 
                "/" + necessario + " " + insumoAtual.getUnidadeMedida());
        }
    }
    
    return estoqueSuficiente;
}
    
    public void consumirInsumosProducao(Produto produto, int quantidade) {
    Video.mensagemInfo("CONSUMINDO INSUMOS...");
    
    for (ItemReceita item : produto.getReceita()) {
        Insumo insumo = item.getInsumo();
        Insumo insumoAtual = insumoService.buscarInsumoPorId(insumo.getId());
        
        if (insumoAtual == null) {
            Video.mensagemErro("Insumo não encontrado: " + insumo.getNome());
            continue;
        }
        
        int consumir = (int) Math.ceil(item.getQuantidadeNecessaria() * quantidade);
        
        if (!insumoAtual.estaDisponivel(consumir)) {
            Video.mensagemErro("Estoque insuficiente de " + insumoAtual.getNome() + 
                " (necessário: " + consumir + ", disponível: " + insumoAtual.getQuantidadeAtual() + ")");
            continue;
        }
        
        int estoqueAntes = insumoAtual.getQuantidadeAtual();
        boolean removido = insumoAtual.removerEstoque(consumir);
        
        if (!removido) {
            Video.mensagemErro(" Falha ao consumir " + insumoAtual.getNome());
            continue;
        }

        insumoService.atualizarInsumo(insumoAtual);
        Video.mensagemOk("Consumido: " + consumir + " " + insumoAtual.getUnidadeMedida() + 
            " de " + insumoAtual.getNome() + " | Estoque: " + estoqueAntes + " → " + insumoAtual.getQuantidadeAtual());
    }
    
    Video.mensagemOk(" Processo de consumo de insumos finalizado!");
}


    private Produto selecionarProduto() {
        List<Produto> produtos = repoProdutos.listarTodos();
        if (produtos.isEmpty()) {
            Video.mensagemErro("Nenhum produto cadastrado.");
            return null;
        }
        produtos.forEach(produto -> Video.mensagem("ID: " + produto.getId() + " - " + produto.getNome()));
        Produto produto = repoProdutos.buscarPorId(Teclado.readInt("ID do produto:"));
        if (produto == null) Video.mensagemErro("Produto não encontrado!");
        return produto;
    }
    
    private boolean validarInsumos() {
        List<Insumo> insumos = insumoService.listarTodosInsumos();
        if (insumos.isEmpty()) {
            Video.mensagemErro("Nenhum insumo cadastrado!");
            return false;
        }
        insumos.forEach(insumo -> Video.mensagem("ID: " + insumo.getId() + " - " + insumo.getNome() + " (" + insumo.getUnidadeMedida() + ")"));
        return true;
    }
    
    private void adicionarIngredientes(Produto produto) {
        boolean continuar = true;
        while (continuar) {
            Insumo insumo = selecionarInsumo();
            if (insumo != null) {
                float quantidade = solicitarQuantidade(insumo);
                if (quantidade > 0) {
                    produto.adicionarInsumoReceita(insumo, quantidade);
                    Video.mensagemOk(insumo.getNome() + " adicionado!");
                    exibirReceitaAtual(produto);
                }
            }
            continuar = Teclado.readString("Adicionar outro? (S/N):").equalsIgnoreCase("S");
        }
        repoProdutos.atualizar(produto);
        Video.mensagemOk("Receita cadastrada para " + produto.getNome() + "!");
    }
    
    private Insumo selecionarInsumo() {
        Insumo insumo = insumoService.buscarInsumoPorId(Teclado.readInt("ID do insumo:"));
        if (insumo == null) Video.mensagemErro("Insumo não encontrado!");
        return insumo;
    }
    
    private float solicitarQuantidade(Insumo insumo) {
        double quantidade = Teclado.readDouble("Quantidade de " + insumo.getNome() + " (" + insumo.getUnidadeMedida() + "):");
        if (quantidade <= 0) {
            Video.mensagemErro("Quantidade inválida!");
            return 0;
        }
        return (float) quantidade;
    }
    
    private void exibirReceitaAtual(Produto produto) {
        Video.mensagem("RECEITA ATUAL:");
        if (produto.getReceita().isEmpty()) {
            Video.mensagem("  Nenhum ingrediente");
        } else {
            produto.getReceita().forEach(item -> 
                Video.mensagem("  " + item.getInsumo().getNome() + ": " + 
                    item.getQuantidadeNecessaria() + " " + item.getInsumo().getUnidadeMedida())
            );
        }
    }
    
    private void exibirReceitaCompleta(Produto produto) {
        Video.mensagem("RECEITA: " + produto.getNome());
        Video.mensagem("Preço: R$ " + produto.getPreco());
        Video.mensagem("Estoque: " + produto.getQuantidadeAtual());
        Video.separador(30);
        Video.mensagem("INGREDIENTES:");
        produto.getReceita().forEach(item -> {
            Insumo insumo = item.getInsumo();
            Video.mensagem("  - " + insumo.getNome() + ": " + 
                item.getQuantidadeNecessaria() + " " + insumo.getUnidadeMedida() +
                " (Estoque: " + insumo.getQuantidadeAtual() + ")");
        });
    }
    
    private void exibirReceita(Produto produto) {
        Video.mensagemInfo("RECEITA:");
        produto.getReceita().forEach(item -> 
            Video.mensagem("  - " + item.getInsumo().getNome() + ": " + 
                item.getQuantidadeNecessaria() + " " + item.getInsumo().getUnidadeMedida())
        );
    }
}