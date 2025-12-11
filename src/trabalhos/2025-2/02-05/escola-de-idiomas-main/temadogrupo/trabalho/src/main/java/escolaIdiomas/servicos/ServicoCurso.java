package escolaIdiomas.servicos;

import java.util.List;

import escolaIdiomas.entidades.Curso;
import escolaIdiomas.repositorios.Repositorio.RepositorioCurso;
import escolaIdiomas.utilitarios.LimparBuffer;
import escolaIdiomas.utilitarios.Teclado;
import escolaIdiomas.utilitarios.Video;

public class ServicoCurso {

    public final RepositorioCurso repositorio;

    public ServicoCurso(RepositorioCurso repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarCurso() {
        Video.cabecalho("\n          Cadastro de Curso ");
        System.out.print("Idioma: ");
        String idioma = Teclado.readString();

        System.out.print("Modalidade: ");
        System.out.println("Online(1) ou Presencial(2)?");
        String modalidade = Teclado.readString();
        if (modalidade.equals("1")) {
            modalidade = "Online";
        } else if (modalidade.equals("2")) {
            modalidade = "Presencial";
        } else {
            Video.mensagemAlerta("Modalidade inválida. Cadastro cancelado.\n");
            return;
        }

        System.out.print("Valor base: ");
        double valor = Teclado.readDouble();
        LimparBuffer.limpar(Teclado.getScanner());

        int idCurso = repositorio.gerarIdCurso();
        Curso c = new Curso(idCurso, idioma, modalidade, valor);
        double mensalidade = c.calcularMensalidade();
        Video.mensagemInfo("Mensalidade calculada: R$ " + mensalidade);
        repositorio.salvar(c);
        Video.mensagemOk("Curso cadastrado com sucesso!\n");
    }

    public void listarCursos() {
        Video.cabecalho("          Lista de Cursos ");
        List<Curso> lista = repositorio.listarTodos();
        if (lista.isEmpty()) {
            Video.mensagemAlerta("Nenhum curso cadastrado.\n");
            return;
        }
        for (Curso c : lista) {
            System.out.println(c);
            System.out.print("-----------------------------\n");
        }
        System.out.println();
    }

    public Curso buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public boolean removerCurso() {
        listarCursos();
        System.out.print("Informe o ID do curso para remover: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        boolean ok = repositorio.remover(id);
        System.out.println(ok ? "Curso removido." : "Curso não encontrado.");
        return ok;
    }

    public void atualizarValorCurso() {
        listarCursos();

        System.out.print("Informe o ID do curso a alterar: ");
        int id = Teclado.readInt();
        LimparBuffer.limpar(Teclado.getScanner());

        Curso curso = repositorio.buscarPorId(id);

        if (curso == null) {
            Video.mensagemAlerta("Curso não encontrado!");
            return;
        }

        System.out.println("Valor atual: R$ " + curso.getValorBase());
        System.out.print("Novo valor base: ");
        double novoValor = Teclado.readDouble();
        LimparBuffer.limpar(Teclado.getScanner());
        curso.setValorBase(novoValor);

        System.out.println("Modalidade atual: " + curso.getModalidade());
        System.out.print("Nova modalidade: ");
        String novaModalidade = Teclado.readString();
        curso.setModalidade(novaModalidade);

        repositorio.atualizar(curso);

        Video.mensagemOk("Valor atualizado com sucesso!");
        Video.mensagemInfo("Nova mensalidade: R$ " + curso.calcularMensalidade());
    }
}

