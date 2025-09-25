package exemplos.PersistenciaOO.Generics.Aluno;

import java.io.*;
import java.util.*;

/*
 * 4. SerializacaoRepositorio<T extends Serializable> (Implementação Concreta) 
 * Função: salvar e recuperar objetos em arquivo binário via serialização Java.
 * Por que existe?
 * Outra implementação de Repositorio<T>.
 * Permite comparar texto × binário.
 * Mostra OCP (Open-Closed Principle): você estende o sistema criando outra classe, sem modificar código existente.
 * É como guardar objetos numa caixa fechada, que só o programa sabe abrir (não legível no bloco de notas).
 */

public class SerializacaoRepositorio<T> implements Repositorio<T> {
    private String arquivo;

    public SerializacaoRepositorio(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void salvar(List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<T>) ois.readObject();
        }
    }
}
