package servico;

import repositorio.Repositorio;
import java.util.List;

public class ServicoPedido<T> {
    private Repositorio<T> repositorio;

    public ServicoPedido(Repositorio<T> repositorio) {
        this.repositorio = repositorio;
    }

    public void salvar(List<T> lista) throws Exception {
        repositorio.salvar(lista);
    }

    public List<T> carregar() throws Exception {
        return repositorio.listar();
    }
}
