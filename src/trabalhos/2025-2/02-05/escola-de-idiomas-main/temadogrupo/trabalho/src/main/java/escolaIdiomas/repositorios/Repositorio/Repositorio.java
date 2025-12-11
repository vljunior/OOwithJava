package escolaIdiomas.repositorios.Repositorio;
import java.util.List;

public interface Repositorio<T> {

    void salvar(T objeto);
    List<T> listarTodos();
    T buscarPorId(int id);   
    void atualizar(T objeto);
    boolean remover(int id);
}
