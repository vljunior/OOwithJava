package repositorio;

import java.util.List;

public interface Repositorio<T> {
    void salvar(List<T> lista) throws Exception;
    List<T> listar() throws Exception;
}
