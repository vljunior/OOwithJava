package escolaIdiomas.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import escolaIdiomas.interfaces.Gerenciavel;

public class Turma implements Serializable, Gerenciavel <Aluno, String> {
    private static final long serialVersionUID = 1L;

    private final int idTurma;
    private Curso curso;
    private Professor professor;
    private final List<Aluno> alunos;

    public Turma(int idTurma, Curso curso, Professor professor) {
        this.idTurma = idTurma;
        this.curso = curso; 
        this.professor = professor; 
        this.alunos = new ArrayList<>();
    }

    public int getIdTurma() {
        return idTurma;
    }
    public Curso getCurso() {
        return curso;
    }
    public Professor getProfessor() {
        return professor;
    }

    public void setCurso(Curso curso) {
    if (curso == null) {
        throw new IllegalArgumentException("O curso não pode ser nulo.");
    }
    this.curso = curso;
    }

    public void setProfessor(Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("O professor não pode ser nulo.");
        }
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos); //retorna copia para evitar modificacoes na lista
    }

    @Override
    public void adicionar(Aluno aluno, String nome) {
        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }
    
    @Override
    public void remover(Aluno aluno, String nome) {
        if (aluno != null && alunos.contains(aluno)) {
            alunos.remove(aluno);
        }
    }

    @Override
    public String toString() {
        return "Turma ID: " + idTurma +
           "\nCurso: " + (curso != null ? curso.getIdioma() + " (" + curso.getModalidade() + ")" : "Nenhum") +
           "\nProfessor: " + 
            (professor != null 
                ? professor.getNome() + " - Idioma: " + professor.getIdioma()
                : "Nenhum");
}
}
