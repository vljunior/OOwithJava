// Arquivo: Padaria/model/interfaces/Cliente.java
package Padaria.model.interfaces;



public interface ClienteInterface {
    
    int getId();

    String getNome();
    void setNome(String nome);
    
    
    String getTelefone();
    void setTelefone(String telefone);
    
    
    String getEmail();
    void setEmail(String email);
    
    
    String getEndereco();
    void setEndereco(String endereco);
    
   
    String toString();
}