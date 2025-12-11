package org.example.view;

import org.example.Bolo;
import org.example.Pao;
import org.example.Produto;
import org.example.ProdutoController;
import org.example.menus.MenuProdutos;
import org.example.utilitarios.Teclado;
import org.example.utilitarios.Video;

import java.util.List;

public class ProdutoView {

    private ProdutoController produtoController;

    public ProdutoView() {
        this.produtoController = new ProdutoController();
    }

    public void executar() {
        boolean executando = true;
        while(executando) {
            int opcao = MenuProdutos.exibir();
            switch(opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    adicionar();
                    break;
                case 3:
                    atualizar();
                    break;
                case 4:
                    remover();
                    break;
                case 5:
                    executando = false;
                    break;
            }
        }
    }

    private void listar() {
        Video.cabecalho("Listar Produtos");
        List<Produto> produtos = produtoController.listarProdutos();
        if (produtos.isEmpty()) System.out.println("Nenhum produto cadastrado.");

        for (Produto p : produtos) {
            String tipo = (p instanceof Pao) ? "Pão" : (p instanceof Bolo) ? "Bolo" : "Produto";
            System.out.println("ID: " + p.getId() + " | Tipo: " + tipo + " | Nome: " + p.getNome() + " | R$: " + p.getPreco() + " | Estoque: " + p.getQuantidadeEstoque());
        }
        Video.pausa();
    }

    private void adicionar() {
        Video.cabecalho("Adicionar Produto");
        int tipo = Teclado.readInt("Tipo (1) Pão (2) Bolo: ");
        String nome = Teclado.readString("Nome: ");
        double preco = Teclado.readDouble("Preço: ");
        int estoque = Teclado.readInt("Estoque Inicial: ");

        Produto novoProduto;
        if (tipo == 1) {
            novoProduto = new Pao();
        } else if (tipo == 2) {
            novoProduto = new Bolo();
        } else {
            Video.mensagemErro("Tipo inválido.");
            return;
        }
        novoProduto.setNome(nome);
        novoProduto.setPreco(preco);
        novoProduto.setQuantidadeEstoque(estoque);

        produtoController.salvarProduto(novoProduto);
        Video.mensagemOk("Produto salvo!");
        Video.pausa();
    }

    private void atualizar() {
        Video.cabecalho("Atualizar Estoque/Preço");
        long idAtt = Teclado.readInt("ID do Produto: ");
        Produto pAtt = produtoController.buscarProduto(idAtt);

        if (pAtt == null) {
            Video.mensagemErro("Produto não encontrado.");
            return;
        }

        System.out.println("Atualizando: " + pAtt.getNome());
        double novoPreco = Teclado.readDouble("Novo Preço (Atual: " + pAtt.getPreco() + "): ");
        int novoEstoque = Teclado.readInt("Novo Estoque (Atual: " + pAtt.getQuantidadeEstoque() + "): ");

        pAtt.setPreco(novoPreco);
        pAtt.setQuantidadeEstoque(novoEstoque);

        produtoController.atualizarProduto(pAtt);
        Video.mensagemOk("Produto atualizado!");
        Video.pausa();
    }

    private void remover() {
        Video.cabecalho("Remover Produto");
        long idRem = Teclado.readInt("ID do Produto a remover: ");
        Produto pRem = produtoController.buscarProduto(idRem);

        if (pRem == null) {
            Video.mensagemErro("Produto não encontrado.");
            return;
        }

        produtoController.removerProduto(idRem);
        Video.mensagemOk("Produto '" + pRem.getNome() + "' removido!");
        Video.pausa();
    }
}