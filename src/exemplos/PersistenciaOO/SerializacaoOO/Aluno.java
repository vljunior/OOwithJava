package exemplos.PersistenciaOO.SerializacaoOO;

import java.io.*;

/* 1. Aluno (ou qualquer Entidade)
 *    Função: representar os dados do domínio (modelo).
 *    Por que existe?
 *    Segue o SRP (Single Responsibility Principle): é apenas um objeto de negócio 
 *    com atributos (nome, idade) e construtor.
 *    Não mistura persistência nem regras de negócio.
 *    É o “molde” para criar objetos reais que queremos guardar.
 */

public class Aluno implements Serializable, SerializableTxt {
    private String nome;
    private int idade;

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { 
        return nome; 
    }
    
    public int getIdade() { 
        return idade; 
    }

    @Override    
    public String toString() {
        return nome + ";" + idade; // formato simples de lista
    }

    /* Estes métodos fazem parte do processo, gera o aluno serlizado em txt e
     * que recebe uma string lista com separador por ponto e vírgula 
     * e reconstroi um objeto     
     */

    @Override    
    public String toSerializableTxt() {
        return nome + ";" + idade; // formato simples de lista
    }

    
    @Override
    public Aluno fromSerializableTxt(String linha) {
        //split quebra em parters pelo delimitador, colocando no vetor        
        //pegando cada elemento do vetor criado a partir de linha
        //e fazendo split pelo separador ";"
        //Jogando no construtor para retomar o objeto e o retornando        
        String[] partes = linha.split(";");
        return new Aluno(partes[0], Integer.parseInt(partes[1]));
    }
}
