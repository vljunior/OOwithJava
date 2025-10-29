package exemploaluno;


import java.util.List;


public interface AlunoRepository {
    void salvar(Aluno aluno);
    Aluno buscarPorCpf(CPF cpf);
    List<Aluno> listarTodos();
    void deletar(CPF cpf);
}