package exemploaluno;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @EmbeddedId
    private CPF cpf;

    @Column(nullable = false, unique = true, length = 20)
    private String matricula;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private int idade;

    protected Aluno() {} // exigido pelo JPA

    public Aluno(CPF cpf, String matricula, String nome, int idade) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
    }

    public CPF getCpf() { return cpf; }
    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }

    @Override
    public String toString() {
        return "Aluno{" +
                "cpf=" + cpf +
                ", matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
