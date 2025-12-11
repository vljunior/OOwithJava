package Padaria.model.entities;

import java.io.Serializable;

import Padaria.utilitarios.GeradorIdentificadorUnico;

public class Cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    
    public Cliente(String nome, String telefone, String email, String endereco) {
        this.id = GeradorIdentificadorUnico.gerarIDCliente();

        this.nome = nome;

        this.telefone = telefone;

        this.email = email;

        this.endereco = endereco;
    }
    
    public int getId() { return id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    @Override
    public String toString() {
        return String.format("Cliente [ID: %d] %s - Tel: %s", id, nome, telefone);
    }
    
    public String toStringCompleto() {
        return String.format("ID: %d | Nome: %s | Tel: %s | Email: %s", id, nome, telefone, email);
    }
}