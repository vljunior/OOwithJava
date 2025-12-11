package escolaIdiomas.servicos;

import java.util.List;

import escolaIdiomas.entidades.Aluno;
import escolaIdiomas.repositorios.Repositorio.RepositorioAluno;
import escolaIdiomas.utilitarios.LimparBuffer;
import escolaIdiomas.utilitarios.Teclado;
import escolaIdiomas.utilitarios.Video;

public class ServicoAluno {



    public final RepositorioAluno repositorio;

    public ServicoAluno(RepositorioAluno repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarAluno() {
        String cpf;
        Video.cabecalho("         Cadastro de Aluno ");
        System.out.print("Nome: ");
        String nome = Teclado.readString();

        System.out.print("E-mail: ");
        String email = Teclado.readString();

        while (true) { //loop que impede cadastro de cpf invalido
            System.out.print("CPF: ");
            cpf = Teclado.readString();

            try {
                String cpfLimpo = cpf.replace(".", "").replace("-", "");
                Aluno.validarCpf(cpfLimpo);
                break;
            }
            catch (Exception e) {
                System.out.println("CPF inválido: " + e.getMessage());
            }
        }

        int novaMatricula = repositorio.gerarMatricula();
        Aluno aluno = new Aluno(novaMatricula, nome, cpf, email);
        repositorio.salvar(aluno);
        Video.mensagemOk("Aluno cadastrado com sucesso!\n");
    }

    public void listarAlunos() {
        Video.cabecalho("          Lista de Alunos ");
        List<Aluno> lista = repositorio.listarTodos();
        if (lista.isEmpty()) {
            Video.mensagemAlerta("Nenhum aluno cadastrado.\n");
            return;
        }
        for (Aluno a : lista) {
            System.out.println(a);
            System.out.print("-----------------------------\n");
        }
        System.out.println();
    }

    public void procurarPorMatricula() {
        System.out.print("Informe o número da matrícula: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        Aluno a = repositorio.buscarPorId(id);

        if (a != null) {
            System.out.println("Aluno encontrado: " + a + "\n");
        } else {
            Video.mensagemAlerta("Aluno não encontrado.\n");
        }
    }

    public void atualizarAluno() {  //permite alteracao somente de nome e email
        System.out.print("Informe a matrícula do aluno que deseja atualizar: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        Aluno a = repositorio.buscarPorId(id);
        if (a != null) {
            System.out.print("Novo nome:\n ");
            String novoNome = Teclado.readString();
            a.setNome(novoNome);

            System.out.print("Novo email:\n ");
            String novoEmail = Teclado.readString();
            a.setEmail(novoEmail);

            Video.mensagemOk("Dados atualizados com sucesso!\n");
        } else {
            Video.mensagemAlerta("Aluno não encontrado.\n");
        }
    }

    public boolean removerAluno() {
        listarAlunos();
        System.out.print("Informe a matrícula para remover: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        boolean ok = repositorio.remover(id);
        System.out.println(ok ? "Aluno removido." : "Aluno não encontrado.");
        return ok;
    }
}
