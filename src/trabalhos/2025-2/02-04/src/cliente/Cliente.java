package cliente;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int id_cliente;
    private String telefone;
    private String observacao;

   
    public Cliente(int id_cliente, String nome, String telefone, String observacao){
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.telefone = telefone;
        this.observacao = observacao;
    }
    public String getNomeCliente(){
        return nome;
    }

    public int getIdCliente(){
        return id_cliente;
    }

    String getTelefone(){
        return telefone;
    }

    String getObservacao(){
        return observacao;
    }

    public void setNomeCliente(String nome){
        this.nome = nome;
    }

    public void setObservacao(String observacao){
        this.observacao = observacao;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "cliente: " + nome + "\n(ID:" + id_cliente +")" + "\nTelefone: " + telefone + "\nObservação: " + observacao + "\n----------";
    }
}



