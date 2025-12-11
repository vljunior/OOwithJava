package Padaria.model.entities;

import java.io.Serializable;

import Padaria.utilitarios.GeradorIdentificadorUnico;

public class Insumo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final int id;
    private String nome;
    private String unidadeMedida;
    private int quantidadeAtual;
    private int estoqueMinimo;
    
    public Insumo(String nome, String unidadeMedida, int estoqueMinimo) {

        this.id = GeradorIdentificadorUnico.gerarIDInsumo();
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeAtual = 0;
        this.estoqueMinimo = estoqueMinimo;
    }
    
    public int getId() { return id; }

    public String getNome() { return nome; }

    public String getUnidadeMedida() { return unidadeMedida; }

    public int getQuantidadeAtual() { return quantidadeAtual; }

    public int getEstoqueMinimo() { return estoqueMinimo; }

    public void setNome(String nome) { this.nome = nome; }

    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }

    public boolean estaDisponivel(int quantidade) { 
        return quantidadeAtual >= quantidade; 
    }
    
    public void adicionarEstoque(int quantidade) { 
        if (quantidade > 0) this.quantidadeAtual += quantidade; 
    }
    
    public boolean removerEstoque(int quantidade) { 
        if (quantidadeAtual >= quantidade) {
            quantidadeAtual -= quantidade;
            return true;
        }
        return false;
    }
    
    public boolean precisaReposicao() {
        return quantidadeAtual < estoqueMinimo;
    }
    
    @Override
    public String toString() {
        return String.format("Insumo [ID: %d] %s - %d %s", id, nome, quantidadeAtual, unidadeMedida);
    }
}