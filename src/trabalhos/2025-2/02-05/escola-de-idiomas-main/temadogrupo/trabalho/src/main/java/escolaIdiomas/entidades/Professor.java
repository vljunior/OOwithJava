package escolaIdiomas.entidades;

import java.io.Serializable;

public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idProfessor;
    private String nome;
    private  String idioma;

    public Professor(int idProfessor, String nome, String idioma){
        this.idProfessor = idProfessor;
        setNome(nome);     
        setIdioma(idioma);
    }

    public int getIdProfessor(){
        return idProfessor;
    }
    public String getNome(){
        return nome;
    }

    public String getIdioma(){
        return idioma;
    }

    public void setIdioma(String idioma) {
        if (idioma == null || idioma.isBlank()) {
            throw new IllegalArgumentException("Idioma inválido.");
        }
        this.idioma = idioma;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do professor não pode ser vazio.");
        }
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Professor: " + nome +
        "\nId professor: " + idProfessor +
        "\nIdioma que leciona: " + idioma;
    }
}
//aaaaaaaaaaaaaaaaaaaaaa