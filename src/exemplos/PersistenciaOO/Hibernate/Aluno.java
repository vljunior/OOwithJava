package exemplos.PersistenciaOO.Hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(length = 20, nullable = false)
    private String matricula; // PK

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    // Construtor default exigido pelo JPA
    protected Aluno() {}

    public Aluno(String matricula, String nome, int idade) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
    }

    // getters/setters
    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }

    @Override
    public String toString() {
        return "Aluno{matricula='" + matricula + "', nome='" + nome + "', idade=" + idade + "}";
    }
}