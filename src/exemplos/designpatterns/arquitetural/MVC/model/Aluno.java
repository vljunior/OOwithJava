package exemplos.designpatterns.arquitetural.MVC.model;

public class Aluno implements EstudanteModel {
    private String nome;
    private int idade;

    public Aluno(String nome, int idade) {
        setNome(nome);
        this.idade = idade;
    }
    @Override
    public String getNome() {
        return nome;
    }
    @Override
    public int getIdade() {
        return idade;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }



    
}

