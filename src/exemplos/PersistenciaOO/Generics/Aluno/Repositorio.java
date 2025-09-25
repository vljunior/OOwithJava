package exemplos.PersistenciaOO.Generics.Aluno;

import java.util.*;

/*
 * 2. Repositorio<T> (Interface)
 * Função: contrato que define o que qualquer repositório deve fazer.
 * - salvar(List<T> lista)
 * - listar()
 * Por que existe?
 * Garante ISP (Interface Segregation Principle): define só o essencial.
 * Permite DIP (Dependency Inversion Principle): o serviço depende da abstração, não da implementação.
 * É como um contrato de aluguel → várias pessoas podem alugar o mesmo imóvel, mas todas seguem as mesmas regras.
 */

/*public*/ interface Repositorio<T> {
    void salvar(List<T> lista) throws Exception;
    List<T> listar() throws Exception;
}
