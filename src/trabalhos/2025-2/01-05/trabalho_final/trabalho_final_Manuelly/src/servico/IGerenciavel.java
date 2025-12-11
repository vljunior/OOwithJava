package servico;

import java.util.ArrayList;
// <T> é o tipo genérico
public interface IGerenciavel<T> {
    
    void cadastrar(T objeto);
    T buscar(String nome) throws EntidadeNaoEncontradaException;
    ArrayList<T> listarTodos();
    void atualizar(T objeto) throws EntidadeNaoEncontradaException;
    void excluir(String nome) throws EntidadeNaoEncontradaException;
    void salvar();
    void carregar();
}