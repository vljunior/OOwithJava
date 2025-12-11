package escolaIdiomas.repositorios.Repositorio;
import escolaIdiomas.entidades.Turma;

public class RepositorioTurma extends RepositorioImplementacao<Turma> {

    private int contador = 1;

    public RepositorioTurma() {
        super();

    // Ao reconstruir o repositório (após carregar os dados do arquivo),
    // percorre a lista de cursos já existentes para identificar o maior ID.
    // Isso garante que o sistema continue a numeração sequencial correta
    // mesmo após fechar e reabrir o programa.
    
        int maior = 0;
        for (Turma c : lista) {
            if (c.getIdTurma() > maior) {
                maior = c.getIdTurma();
            }
        }
        contador = maior + 1;
    }

    public int gerarIdTurma() {
        return contador++;
    }
    @Override
    public int getId(Turma turma) {
        return turma.getIdTurma();
    }
}
