package escolaIdiomas.interfaces;

public interface Gerenciavel<T, U> {
    void adicionar(T item, U destino);
    void remover(T item, U destino);
}

