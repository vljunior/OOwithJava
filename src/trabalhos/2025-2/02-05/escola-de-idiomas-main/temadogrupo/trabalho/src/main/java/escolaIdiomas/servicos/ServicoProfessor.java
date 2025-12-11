package escolaIdiomas.servicos;

import java.util.List;

import escolaIdiomas.entidades.Professor;
import escolaIdiomas.repositorios.Repositorio.RepositorioProfessor;
import escolaIdiomas.utilitarios.LimparBuffer;
import escolaIdiomas.utilitarios.Teclado;
import escolaIdiomas.utilitarios.Video;

public class ServicoProfessor {

    public final RepositorioProfessor repositorio;

    public ServicoProfessor(RepositorioProfessor repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarProfessor() {
        Video.cabecalho("\n          Cadastro de Professor ");
        System.out.print("Nome: ");
        String nome = Teclado.readString();

        System.out.print("Idioma: ");
        String idioma = Teclado.readString();

        int novoId = repositorio.gerarIdProfessor();
        Professor p = new Professor(novoId, nome, idioma);
        repositorio.salvar(p);
        Video.mensagemOk("Professor cadastrado com sucesso!\n");
    }

    public void listarProfessores() {
        Video.cabecalho("          Lista de Professores ");
        List<Professor> lista = repositorio.listarTodos();
        if (lista.isEmpty()) {
            Video.mensagemAlerta("Nenhum professor cadastrado.\n");
            return;
        }
        for (Professor p : lista) {
            System.out.println(p);
            System.out.print("-----------------------------\n");
        }
    }

    public Professor buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public boolean removerProfessor() {
        listarProfessores();
        System.out.print("Informe o ID do professor para remover: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        boolean ok = repositorio.remover(id);
        System.out.println(ok ? "Professor removido." : "Professor não encontrado.");
        return ok;
    }

    public void procurarProfessor() {
        System.out.print("Informe o ID do professor: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        Professor p = repositorio.buscarPorId(id);

        if (p != null) {
            System.out.println("\nProfessor encontrado:");
            System.out.println(p);
        } else {
            Video.mensagemAlerta("\nProfessor não encontrado.");
        }
    }

    public void atualizarProfessor() { //permite atualizar somente nome e idioma
        System.out.print("Informe o ID do professor que deseja atualizar: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        Professor p = repositorio.buscarPorId(id);
        if (p != null) {
            System.out.print("Novo nome: ");
            String novoNome = Teclado.readString();
            p.setNome(novoNome);

            System.out.print("Novo idioma: ");
            String novoIdioma = Teclado.readString();
            p.setIdioma(novoIdioma);

            repositorio.atualizar(p);
            Video.mensagemOk("Dados atualizados com sucesso!\n");
        } else {
            Video.mensagemAlerta("Professor não encontrado.\n");
        }
    }
}
