package escolaIdiomas.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import escolaIdiomas.interfaces.Gerenciavel;
import escolaIdiomas.interfaces.MensalidadeCalculavel;
import escolaIdiomas.utilitarios.Video;

public class Curso implements Serializable, Gerenciavel<Turma, String>, MensalidadeCalculavel {
    private static final long serialVersionUID = 1L;

    private final int idCurso;
    private final String idioma;
    private String modalidade;
    private double valorBase;
    private final List<Turma> turmas;
    private double mensalidade;

    public Curso(int idCurso, String idioma, String modalidade, double valorBase) {
        this.idCurso = idCurso;
        this.idioma = idioma;
        setModalidade(modalidade); // usa validação
        setValorBase(valorBase);
        this.turmas = new ArrayList<>();
    }

    @Override
    public double calcularMensalidade() {
        if (modalidade.equalsIgnoreCase("online")) { //modalidade online tem 10% de desconto
            mensalidade = valorBase * 0.9; 
        } else {
            mensalidade = valorBase;
        }
        return mensalidade;
    }

    @Override
    public void adicionar(Turma turma, String nome) {
        if (turma != null) {
            turmas.add(turma);
            Video.mensagemOk("A turma foi criada com sucesso!" + idioma);
        }
    }

    @Override
    public void remover(Turma turma, String nome) {
        if (turma != null) {
            turmas.remove(turma);
            System.out.println("Turma fechada do curso: " + idioma);
        }
    }

    @Override
    public String toString() {
    String texto = "Curso ID = " + idCurso + "\n" +
                   " Idioma = " + idioma + "\n" +
                   " Modalidade = " + modalidade + "\n" +
                   " Valor (já calculado) = R$" + mensalidade + "\n" +
                   " Turmas vinculadas: ";

        if (turmas == null || turmas.isEmpty()) {
            texto += "Nenhuma turma cadastrada.";
        }else {
            texto += "\n";
        for (Turma t : turmas) {
            texto += "- Turma ID: " + t.getIdTurma() + "\n";
        }
        }

        return texto;
    }
    public void listar() {
        System.out.println("Turmas do curso de: " + idioma);
        for (Turma t : turmas) {
            System.out.println("id: " + t.getIdTurma());
        }
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade){
        if (modalidade == null || modalidade.isBlank()) {
            throw new IllegalArgumentException("Modalidade inválida.");
        }
        this.modalidade = modalidade;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        if (valorBase < 0) {
            throw new IllegalArgumentException("Valor base não pode ser negativo.");
        }
        this.valorBase = valorBase;
    }
}
