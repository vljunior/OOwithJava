package escolaIdiomas.servicos;

import java.util.List;

import escolaIdiomas.entidades.Curso;
import escolaIdiomas.entidades.Professor;
import escolaIdiomas.entidades.Turma;
import escolaIdiomas.entidades.Aluno;
import escolaIdiomas.repositorios.Repositorio.RepositorioTurma;
import escolaIdiomas.utilitarios.LimparBuffer;
import escolaIdiomas.utilitarios.Teclado;
import escolaIdiomas.utilitarios.Video;

public class ServicoTurma {

    public final RepositorioTurma repositorio;
    public final ServicoCurso servicoCurso;
    public final ServicoProfessor servicoProfessor;
    public final ServicoAluno servicoAluno;

    public ServicoTurma(RepositorioTurma repositorio, ServicoCurso servicoCurso, ServicoProfessor servicoProfessor, ServicoAluno servicoAluno) {
        this.repositorio = repositorio;
        this.servicoCurso = servicoCurso;
        this.servicoProfessor = servicoProfessor;
        this.servicoAluno = servicoAluno;
    }

    public void cadastrarTurma() {
        Video.cabecalho("     Cadastro de Turma ");
        Video.mensagemOk("Turma criada.");
        System.out.println("Adicione um curso e um professor na função de adição a turma.");
        int idTurma = repositorio.gerarIdTurma();
        Turma t = new Turma(idTurma,null, null);
        repositorio.salvar(t);
    }

    public void listarTurmas() {
        Video.cabecalho("          Lista de Turmas");
        List<Turma> lista = repositorio.listarTodos();
        if (lista.isEmpty()) {
            Video.mensagemAlerta("Nenhuma turma cadastrada.");
            return;
        }
        for (Turma t : lista) {
            System.out.println(t);
            System.out.print("-----------------------------\n");
        }
        System.out.println("Quer listar os alunos de uma turma? (s/n)");
        String resposta = Teclado.readString().toLowerCase();

        if (resposta.equals("s")) {
            System.out.print("Informe o ID da turma: ");
            int idTurma = Teclado.readInt();
            LimparBuffer.limpar(Teclado.getScanner());

            Turma turma = repositorio.buscarPorId(idTurma);
            if (turma == null) {
                Video.mensagemAlerta("Turma não encontrada.");
                return;
            }

            List<Aluno> alunos = turma.getAlunos();
            if (alunos.isEmpty()) {
                Video.mensagemAlerta("Nenhum aluno cadastrado nesta turma.");
                return;
            }

            Video.cabecalho("Alunos da Turma ID: " + turma.getIdTurma());
            for (Aluno a : alunos) {
                System.out.println(a);
                System.out.print("-----------------------------\n");
            }
            System.out.println();
        }
    }

    public boolean removerTurma() {
        listarTurmas();
        System.out.print("Informe o ID da turma para remover: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        boolean ok = repositorio.remover(id);
        System.out.println(ok ? "Turma removida." : "Turma não encontrada.");
        return ok;
    }

    public void adicionarAlunoOuProfessorATurma() {
    Video.cabecalho("Adicionar Aluno ou Professor à Turma");

    // escolher a turma
    System.out.print("Qual o ID da turma que você quer adicionar alguém? ");
    int idTurma = Teclado.readInt();
    LimparBuffer.limpar(Teclado.getScanner());

    Turma turma = repositorio.buscarPorId(idTurma);
    if (turma == null) {
        Video.mensagemAlerta("Turma não encontrada!");
        return;
    }

    System.out.println("\nVocê selecionou a turma:");
    System.out.println(turma);

    System.out.println("\nO que deseja adicionar à turma?");
    System.out.println("1 - Aluno");
    System.out.println("2 - Professor");
    System.out.print("Escolha: ");
    int opcao = Teclado.readInt();
    LimparBuffer.limpar(Teclado.getScanner());

    if (opcao == 1) {
        List<Aluno> alunos = servicoAluno.repositorio.listarTodos();

        if (alunos.isEmpty()) {
            Video.mensagemAlerta("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\nAlunos disponíveis:");
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            System.out.println((i + 1) + " - " + a.getNome() +
                " (CPF: " + a.getCpf() + ") | Matrícula: " + a.getMatricula());
        }

        System.out.print("\nEscolha o número do aluno: ");
        int escolha = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        if (escolha < 1 || escolha > alunos.size()) {
            Video.mensagemErro("Opção inválida!");
            return;
        }

        Aluno selecionado = alunos.get(escolha - 1);

        turma.adicionar(selecionado, selecionado.getNome());
        repositorio.atualizar(turma);

        Video.mensagemOk("\nAluno '" + selecionado.getNome() + "' adicionado à turma com sucesso!");
        return;
    }

    if (opcao == 2) {
        List<Professor> profs = servicoProfessor.repositorio.listarTodos();

        if (profs.isEmpty()) {
            Video.mensagemAlerta("Nenhum professor cadastrado.");
            return;
        }

        System.out.println("\nProfessores disponíveis:");
        for (int i = 0; i < profs.size(); i++) {
            Professor p = profs.get(i);
            System.out.println((i + 1) + " - " + p.getNome() +
                " | Idioma: " + p.getIdioma());
        }

        System.out.print("\nEscolha o número do professor: ");
        int escolha = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        if (escolha < 1 || escolha > profs.size()) {
            Video.mensagemErro("Opção inválida!");
            return;
        }

        Professor selecionado = profs.get(escolha - 1);

        turma.setProfessor(selecionado);
        repositorio.atualizar(turma);

        Video.mensagemOk("\nProfessor '" + selecionado.getNome() + "' adicionado à turma com sucesso!");
        return;
    }

    Video.mensagemErro("Opção inválida.");
}

 public void adicionarTurmaACurso() {
        Video.cabecalho("Adicionar Turma ao Curso ");
        System.out.print("Informe o ID do curso: ");
        int idCurso = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        Curso curso = servicoCurso.repositorio.buscarPorId(idCurso);
        if (curso == null) {
            Video.mensagemAlerta("Curso não encontrado.");
            return;
        }

        List<Turma> turmas = repositorio.listarTodos();
        if (turmas.isEmpty()) {
            Video.mensagemAlerta("Nenhuma turma cadastrada.");
            return;
        }

        Video.cabecalho("\n          Turmas disponíveis:");
        for (int i = 0; i < turmas.size(); i++) {
            System.out.println((i + 1) + " - ID: " + turmas.get(i).getIdTurma());
        }

        System.out.print("Escolha o número da turma: ");
        int opc = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        if (opc < 1 || opc > turmas.size()) {
            Video.mensagemErro("Opção inválida!");
            return;
        }

        Turma t = turmas.get(opc - 1);
        curso.adicionar(t, curso.getIdioma()); //associa turma existente a curso
        servicoCurso.repositorio.atualizar(curso);
        Video.mensagemOk("Turma adicionada ao curso com sucesso!");
    }
}
