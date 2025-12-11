package escolaIdiomas.repositorios.Repositorio;
import escolaIdiomas.entidades.Professor;
import escolaIdiomas.utilitarios.GeradorIdentificadorUnicoModificado;

public class RepositorioProfessor extends RepositorioImplementacao<Professor> {

    

    public RepositorioProfessor() {
        super();

    // Se já existirem professores salvos no arquivo de persistência,
    // encontra o maior ID entre eles para garantir que novos IDs
    // continuem a partir do último valor utilizado.
    
        if (!lista.isEmpty()) {
            int maior = 0;
            for (Professor p : lista) {
                if (p.getIdProfessor() > maior) {
                    maior = p.getIdProfessor();
                }
            }
            GeradorIdentificadorUnicoModificado.setValorInicialInt(maior);
        }
    }

    public int gerarIdProfessor() {
        return GeradorIdentificadorUnicoModificado.gerarIDUnicoInt();
    }

    @Override
    public int getId(Professor p) {
        return p.getIdProfessor();
    }
}