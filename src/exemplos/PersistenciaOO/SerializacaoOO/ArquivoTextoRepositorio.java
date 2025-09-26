package exemplos.PersistenciaOO.SerializacaoOO;

import java.io.*;
import java.util.*;
import java.util.function.Function;

/*
 * 3. ArquivoTextoRepositorio<T> (Implementação Concreta) 
 * Função: salvar e recuperar objetos em arquivo texto (CSV simples).
 * Por que existe?
 * Implementa o contrato Repositorio<T>.
 * Aplica Strategy Pattern → é uma estratégia de persistência.
 * Mostra aos alunos que toString e split permitem transformar objeto ↔ string.
 * É como guardar os objetos numa caixa transparente, onde o conteúdo é visível (texto legível).
 */

public class ArquivoTextoRepositorio<T> implements Repositorio<T> {
    private final String arquivo;
    private final Function<String, T> conversor;
    public ArquivoTextoRepositorio(String arquivo, Function<String, T> conversor) {
        this.arquivo = arquivo; this.conversor = conversor;
    }

    @Override public void salvar(List<T> lista) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
            for (T obj : lista) pw.println(obj.toString());
            }
    }
    
    @Override public List<T> listar() throws IOException {
        List<T> out = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(arquivo))) {
            while (sc.hasNextLine()) out.add(conversor.apply(sc.nextLine()));
        }
        return out;
    }
}

