package escolaIdiomas.repositorios.Repositorio;
import escolaIdiomas.entidades.Curso;

public class RepositorioCurso extends RepositorioImplementacao<Curso> {

    private int contador = 1;

    public RepositorioCurso() {
        super();

    // Ao reconstruir o repositório (após carregar os dados do arquivo),
    // percorre a lista de cursos já existentes para identificar o maior ID.
    // Isso garante que o sistema continue a numeração sequencial correta
    // mesmo após fechar e reabrir o programa.

        int maior = 0;
        for (Curso c : lista) {
            if (c.getIdCurso() > maior) {
                maior = c.getIdCurso();
            }
        }
        contador = maior + 1;
    }

    public int gerarIdCurso() {
        return contador++;
    }
    @Override
    public int getId(Curso curso) {
        return curso.getIdCurso();
    }
}
