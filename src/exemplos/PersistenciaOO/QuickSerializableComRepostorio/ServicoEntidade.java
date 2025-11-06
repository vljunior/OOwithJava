import java.io.IOException;
import java.util.*;

public class ServicoEntidade<E extends Serializable> {

    private final Repositorio<E> repositorio;
    private List<E> lista;

    // Injeção de dependência via construtor
    public ServicoEntidade(Repositorio<E> repositorio) {
        this.repositorio = repositorio;
        this.lista = new ArrayList<>();
    }

    public void adicionar(E entidade) throws IOException {
        lista.add(entidade);
        repositorio.salvar(lista);
    }

    public void remover(E entidade) throws IOException {
        lista.remove(entidade);
        repositorio.salvar(lista);
    }

    public List<E> listar() throws IOException, ClassNotFoundException {
        lista = repositorio.recuperar();
        return Collections.unmodifiableList(lista);
    }

    public void limpar() throws IOException {
        lista.clear();
        repositorio.salvar(lista);
    }
}