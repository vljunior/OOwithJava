package Padaria.model.interfaces;

public interface Estocavel {
    
    int getQuantidadeAtual(); 


    boolean estaDisponivel(int quantidadeDesejada);  



    void adicionarEstoque(int quantidade);


    boolean removerEstoque(int quantidade);
    

    default boolean temEstoque(){
        return getQuantidadeAtual() > 0;
    }
}