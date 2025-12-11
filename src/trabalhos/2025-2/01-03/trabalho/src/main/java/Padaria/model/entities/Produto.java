package Padaria.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Padaria.utilitarios.GeradorIdentificadorUnico;

public class Produto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private final int id;
    private String nome;
    private double preco;
    private int estoque;
    private List<ItemReceita> receita;
    
    public Produto(String nome, double preco) {

        this.id = GeradorIdentificadorUnico.gerarIDProduto();
        this.nome = nome;
        this.preco = preco;
        this.estoque = 0;
        this.receita = new ArrayList<>();
    }

    public int getId() { return id; }

    public String getNome() { return nome; }

    public double getPreco() { return preco; }

    public int getQuantidadeAtual() { return estoque; }

    public List<ItemReceita> getReceita() { return receita; }

    public void setNome(String nome) { this.nome = nome; }

    public void setPreco(double preco) { this.preco = preco; }

    public void setEstoque(int estoque) { this.estoque = estoque; }

    public boolean estaDisponivel(int quantidade) { 
        return estoque >= quantidade; 
    }
    
    public void adicionarEstoque(int quantidade) { 
        if (quantidade > 0) this.estoque += quantidade; 
    }
    
    public boolean removerEstoque(int quantidade) { 
        if (estoque >= quantidade) {
            estoque -= quantidade;
            return true;
        }
        return false;
    }

    public void adicionarInsumoReceita(Insumo insumo, float quantidade) {
        receita.add(new ItemReceita(insumo, quantidade));
    }

    @Override
    public String toString() {
        return String.format("Produto [ID: %d] %s - R$ %.2f - Estoque: %d", id, nome, preco, estoque);
    }
}