import java.io.IOException;
import java.util.List;

public interface Repositorio<E> {

    void salvar(List<E> lista) throws IOException;

    List<E> recuperar() throws IOException, ClassNotFoundException;
}