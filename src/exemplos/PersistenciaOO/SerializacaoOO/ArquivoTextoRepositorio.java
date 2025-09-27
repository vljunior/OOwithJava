package exemplos.PersistenciaOO.SerializacaoOO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 3. ArquivoTextoRepositorio<T> (Implementação Concreta) 
 * Função: salvar e recuperar objetos em arquivo texto (; simples).
 * Por que existe?
 * Implementa o contrato Repositorio<T>.
 * Aplica Strategy Pattern → é uma estratégia de persistência.
 * Mostra aos alunos que toString e split permitem transformar objeto para string.
 * É como guardar os objetos numa caixa transparente, onde o conteúdo é visível (texto legível).
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoTextoRepositorio implements Repositorio<Aluno> {
    private final String arquivo;

    public ArquivoTextoRepositorio(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void salvar(List<Aluno> lista) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
            for (Aluno obj : lista) {
                pw.println(obj.toString()); // usa toString
            }
        }
    }

    @Override
    public List<Aluno> listar() throws IOException {
        List<Aluno> out = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(arquivo))) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                out.add(Aluno.fromString(linha)); // reconstrói
            }
        }
        return out;
    }
}