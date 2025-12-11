package escolaIdiomas.repositorios.Repositorio;
import escolaIdiomas.entidades.Aluno;
import escolaIdiomas.utilitarios.GeradorIdentificadorUnico;

public class RepositorioAluno extends RepositorioImplementacao<Aluno> {

    public RepositorioAluno() {
    super();

    // Se já existirem alunos salvos no arquivo de persistência,
    // encontra o maior ID entre eles para garantir que novos IDs
    // continuem a partir do último valor utilizado.
    if (!lista.isEmpty()) {
        int maior = 0;
        for (Aluno a : lista) {
            if (a.getMatricula() > maior) {
                maior = a.getMatricula();
            }
        }
        GeradorIdentificadorUnico.setValorInicialInt(maior);
    }
}
    public int gerarMatricula() {
        return GeradorIdentificadorUnico.gerarIDUnicoInt();
    }

    @Override
    public int getId(Aluno aluno) {
        return aluno.getMatricula();
    }
}

