package org.example;

import java.util.List;

public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController() {
        this.produtoService = new ProdutoService();
    }


    public void salvarProduto(Produto produto) {
        produtoService.salvar(produto);
    }


    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }


    public Produto buscarProduto(Long id) {
        return produtoService.buscarPorId(id);
    }


    public void atualizarProduto(Produto produto) {
        produtoService.atualizar(produto);
    }


    public void removerProduto(Long id) {
        produtoService.remover(id);
    }
}