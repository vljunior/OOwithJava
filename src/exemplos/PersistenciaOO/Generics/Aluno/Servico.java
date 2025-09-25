package exemplos.PersistenciaOO.Generics.Aluno;

import java.util.*;

/*
 * 5. Servico<T> (Camada de Negócio / Context)
 * Função: orquestrar as operações de negócio usando um Repositorio<T>.
 * Por que existe?
 * Encapsula regras de negócio (ex.: validar antes de salvar, logar operações).
 * Aplica DIP: recebe no construtor a implementação concreta (injeção de dependência).
 * Separa responsabilidade: Repositorio só persiste, Servico decide quando e como persistir.
 * É como o funcionário do cartório → ele não é a gaveta (repositório), mas quem decide quando colocar ou retirar papéis dela.
 */

public class Servico<T> {
    private Repositorio<T> repositorio; //Injeção, associação

    public Servico(Repositorio<T> repositorio) {
        this.repositorio = repositorio;
    }

    public void salvar(List<T> lista) throws Exception {
        repositorio.salvar(lista);
    }

    public List<T> carregar() throws Exception {
        return repositorio.listar();
    }
}