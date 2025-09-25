package exemplos.objectCalisthenics;

import java.util.ArrayList;
import java.util.List;

/*
 * 8. “Use coleções de primeira classe”
 * Evitar que listas, mapas ou arrays sejam manipulados diretamente em várias partes do código.
 * A coleção deve ser encapsulada em uma classe própria, ganhando semântica, regras e comportamento.
 * Isso ajuda a manter o código expressivo e alinhado ao domínio, em vez de ficar cheio de List<Algo> espalhados.
 *  
 */

//O exemplo ruim

//Classe Aluno válida para os dois exemplos
class Aluno {
    private String nome;

    public Aluno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class TurmaRuim {
    private List<Aluno> alunos = new ArrayList<>();

    public List<Aluno> getAlunos() {
        return alunos;
    }
}

/*
  O uso:

  Turma turma = new Turma();
  turma.getAlunos().add(new Aluno("Maria"));
  turma.getAlunos().add(new Aluno("João"));

  getAlunos() expõe a lista bruta.
  Regras de negócio ficam espalhadas (qualquer parte do sistema pode adicionar/remover alunos sem restrição).
  Se houver validação (ex: não pode ter aluno duplicado), não está garantida.
  
*/

//A correção



class Alunos {
    private final List<Aluno> lista = new ArrayList<>();

    public void adicionar(Aluno aluno) {
        if (lista.contains(aluno)) {
            throw new IllegalArgumentException("Aluno já está na turma.");
        }
        lista.add(aluno);
    }

    public int quantidade() {
        return lista.size();
    }

    public boolean contem(Aluno aluno) {
        return lista.contains(aluno);
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}

class Turma {
    private final Alunos alunos = new Alunos();

    public void matricular(Aluno aluno) {
        alunos.adicionar(aluno);
    }

    public int totalDeAlunos() {
        return alunos.quantidade();
    }
}

public class ColecoesDePrimeiraClasse {
    public static void main(String[] args) {
        Turma turma = new Turma();
        turma.matricular(new Aluno("Maria"));
        turma.matricular(new Aluno("João"));

        System.out.println("Total de alunos: " + turma.totalDeAlunos());
    }
}
