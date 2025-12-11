package modelo;

import java.io.Serializable;
// Pessoa é a classe "pai"
public abstract class Pessoa implements Serializable { 
    private static final long serialVersionUID = 1L;
    protected String nome;
    
    public Pessoa() {}
    
    public Pessoa(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public abstract String getTipo();
    @Override
    public String toString() {
        return nome;
    }
}
