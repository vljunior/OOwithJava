package escolaIdiomas.sistema;


import escolaIdiomas.menu.MenuPessoa;
import escolaIdiomas.menu.MenuPrincipal;
import escolaIdiomas.repositorios.Repositorio.RepositorioAluno;
import escolaIdiomas.repositorios.Repositorio.RepositorioCurso;
import escolaIdiomas.repositorios.Repositorio.RepositorioProfessor;
import escolaIdiomas.repositorios.Repositorio.RepositorioTurma;
import escolaIdiomas.servicos.ServicoAluno;
import escolaIdiomas.servicos.ServicoCurso;
import escolaIdiomas.servicos.ServicoProfessor;
import escolaIdiomas.servicos.ServicoTurma;
import escolaIdiomas.utilitarios.LimparBuffer;
import escolaIdiomas.utilitarios.Teclado;
import escolaIdiomas.utilitarios.Video;

public class AppTester {

    private final ServicoAluno servicoAluno;
    private final ServicoProfessor servicoProfessor;
    private final ServicoCurso servicoCurso;
    private final ServicoTurma servicoTurma;

    public AppTester() {

        RepositorioAluno reposAluno = new RepositorioAluno();
        RepositorioProfessor reposProf = new RepositorioProfessor();
        RepositorioCurso reposCurso = new RepositorioCurso();
        RepositorioTurma reposTurma = new RepositorioTurma();

        this.servicoAluno = new ServicoAluno(reposAluno);
        this.servicoProfessor = new ServicoProfessor(reposProf);
        this.servicoCurso = new ServicoCurso(reposCurso);
        this.servicoTurma = new ServicoTurma(reposTurma, servicoCurso, servicoProfessor, servicoAluno);
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = MenuPrincipal.exibir();
            LimparBuffer.limpar(Teclado.getScanner());

            switch (opcao) {
                case 1: gerenciarPessoas();break;
                case 2: gerenciarTurmas();break;
                case 3: gerenciarCursos();break;
                case 4: listarTudo();break;
                case 5: Video.escreverLento("Saindo...", 10);break;
                default: Video.mensagemErro("Opção inválida.");
            }
        } while (opcao != 5);
    }
        
  private void gerenciarPessoas() {
    int opcao;
    int subOpcao;

    do {
        opcao = MenuPessoa.exibir(); 
        LimparBuffer.limpar(Teclado.getScanner());

        switch (opcao) {
            case 1:
            System.out.println("1 - Listar Alunos.");
            System.out.println("2 - Listar Professores.");
            System.out.println("3 - Voltar.");
            System.out.print("Escolha uma opção: ");

                subOpcao = Teclado.readInt();
                LimparBuffer.limpar(Teclado.getScanner());

                switch (subOpcao) {
                    
                    case 1: servicoAluno.listarAlunos(); break;
                    case 2: servicoProfessor.listarProfessores(); break;
                    case 3: return;
                    default: Video.mensagemErro("Opção inválida.");
                    }

                break;

            case 2:
            System.out.println("1 - Procurar Aluno por ID.");
            System.out.println("2 - Procurar Professor por ID.");
            System.out.println("3 - Voltar.");
            System.out.println("Escolha uma opção: ");
                
                subOpcao = Teclado.readInt();
                LimparBuffer.limpar(Teclado.getScanner());

                switch (subOpcao) {
                    
                    case 1: servicoAluno.procurarPorMatricula(); break;
                    case 2: servicoProfessor.procurarProfessor(); break;
                    case 3: return;
                    default: Video.mensagemErro("Opção inválida.");
                    }

                break;

            case 3:
            System.out.println("1 - Cadastrar novo Aluno.");
            System.out.println("2 - Cadastrar novo Professor.");
            System.out.println("3 - Voltar.");
            System.out.println("Escolha sua opção: ");
                subOpcao = Teclado.readInt();
                LimparBuffer.limpar(Teclado.getScanner());

                switch (subOpcao) {

                    case 1: servicoAluno.cadastrarAluno(); break;
                    case 2: servicoProfessor.cadastrarProfessor(); break;
                    case 3: return;
                    default: Video.mensagemErro("Opção inválida.");
                    }

                break;

            case 4:
            System.out.println("1 - Atualizar dados de um Aluno.");
            System.out.println("2 - Atualizar dados de um Professor.");
            System.out.println("3 - Voltar.");
            System.out.println("Escolha sua opção: ");
                subOpcao = Teclado.readInt();
                LimparBuffer.limpar(Teclado.getScanner());

                switch (subOpcao) {
                
                    case 1: servicoAluno.atualizarAluno(); break;
                    case 2: servicoProfessor.atualizarProfessor(); break;
                    case 3: return;

                    default: Video.mensagemErro("Opção inválida.");
                    }

                break;

            case 5:
            System.out.println("1 - Remover Aluno do sistema.");
            System.out.println("2 - Remover Professor do sistema.");
            System.out.println("3 - Voltar.");
            System.out.println("Escolha sua opção: ");

               subOpcao = Teclado.readInt();
               LimparBuffer.limpar(Teclado.getScanner());
               
               switch (subOpcao) {
                   
                    case 1: servicoAluno.removerAluno(); break;
                    case 2: servicoProfessor.removerProfessor(); break;
                    case 3: return;
                    default: Video.mensagemErro("Opção inválida.");
                }
                break;

            case 6: Video.escreverLento("Voltando...", 10); return;
                
            default: Video.mensagemErro("Opção inválida.");
            return;
        }

        Video.pausa();

    } while (true);
}


    private void gerenciarTurmas() {
        int opcao;
        do {
            Video.cabecalho(" Gerenciar Turmas ");
            System.out.println("1 - Cadastrar Turma");
            System.out.println("2 - Listar Turmas");
            System.out.println("3 - Adicionar aluno ou professor à Turma");
            System.out.println("4 - Remover Turma.");
            System.out.println("5 - Voltar");
                
            opcao = Teclado.readInt();
            LimparBuffer.limpar(Teclado.getScanner());

            switch (opcao) {
            case 1: servicoTurma.cadastrarTurma(); break;
            case 2: servicoTurma.listarTurmas(); break;
            case 3: servicoTurma.adicionarAlunoOuProfessorATurma();break;
            case 4: servicoTurma.removerTurma();
            case 5: Video.escreverLento("Voltando", 20);return;
            default: Video.mensagemErro("Opção inválida.");
            }

            Video.pausa();
        } while (true);
    }

    private void gerenciarCursos() {
    int opcao;
    do {
            Video.cabecalho(" Gerenciar Cursos ");
            System.out.println("1 - Cadastrar Curso");
            System.out.println("2 - Listar Cursos");
            System.out.println("3 - Remover Curso");
            System.out.println("4 - Atualizar valor da mensalidade");
            System.out.println("5 - Adicionar Turma a Curso");
            System.out.println("6 - Voltar");

            opcao = Teclado.readInt();
            LimparBuffer.limpar(Teclado.getScanner());

            switch (opcao) {
            case 1: servicoCurso.cadastrarCurso(); break;
            case 2: servicoCurso.listarCursos(); break;
            case 3: servicoCurso.removerCurso(); break;
            case 4: servicoCurso.atualizarValorCurso(); break;
            case 5: servicoTurma.adicionarTurmaACurso(); break;

            case 6: 
                Video.escreverLento("Voltando...", 20);
                return;

            default: 
                Video.mensagemErro("Opção inválida.");
        }
        Video.pausa();
    } while (true);
}


    private void listarTudo() {
        servicoAluno.listarAlunos();
        servicoProfessor.listarProfessores();
        servicoTurma.listarTurmas();
        servicoCurso.listarCursos();

    }
}
