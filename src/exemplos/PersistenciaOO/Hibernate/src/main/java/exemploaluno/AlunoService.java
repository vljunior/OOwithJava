package exemploaluno;

import java.util.List;

public class AlunoService {

    private final AlunoRepository repo;

    public AlunoService(AlunoRepository repo) {
        this.repo = repo;
    }

    public void cadastrarAluno(Aluno aluno) {
        repo.salvar(aluno);
    }

    public Aluno buscarAluno(CPF cpf) {
        return repo.buscarPorCpf(cpf);
    }

    public List<Aluno> listarAlunos() {
        return repo.listarTodos();
    }

    public void removerAluno(CPF cpf) {
        repo.deletar(cpf);
    }
}
