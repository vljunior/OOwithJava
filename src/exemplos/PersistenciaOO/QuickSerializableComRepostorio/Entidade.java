import java.io.*;

public class Entidade implements Serializable{
    
    private String nome;
    private int valor;
    
    public Entidade(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() { 
        return nome; 
    }
    
    public int getValor() { 
        return valor; 
    }

    @Override    
    public String toString() {
        return nome + ", " + valor; 
    }
    
}
