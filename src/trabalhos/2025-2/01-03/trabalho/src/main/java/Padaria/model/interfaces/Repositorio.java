package Padaria.model.interfaces;

import java.util.List;

public interface Repositorio<T> {
    void salvar(T entidade);

    T buscarPorId(int id);

    List<T> listarTodos();

    void atualizar(T entidade);
    
    void deletar(int id);

    int quantidade();
}