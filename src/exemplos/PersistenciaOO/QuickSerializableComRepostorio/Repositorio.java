import java.io.IOException;
import java.util.List;

//<E> representa a entidade

public interface Repositorio<E> {

    //Os métodos sugerem um List<E> no repositorio impementado

    void salvar(List<E> lista) throws IOException;
    List<E> recuperar() throws IOException, ClassNotFoundException;
}