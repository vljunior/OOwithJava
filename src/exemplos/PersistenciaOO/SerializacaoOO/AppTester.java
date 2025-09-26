package exemplos.PersistenciaOO.SerializacaoOO;

/* Conexão com SOLID + Patterns
 * SRP: cada classe faz uma coisa só (Aluno = dados, Repositorio = contrato, etc.).
 * OCP: novas persistências (JSON, Banco de Dados) podem ser criadas sem modificar as outras classes.
 * LSP: qualquer repositório pode substituir outro (ArquivoTextoRepositorio <-> SerializacaoRepositorio).
 * ISP: a interface Repositorio<T> tem apenas os métodos essenciais.
 * DIP: Servico<T> depende da interface, não de uma classe concreta.
 * Strategy Pattern: diferentes formas de persistência são estratégias intercambiáveis. 
 */

import java.io.*;
import java.util.*;
import java.util.function.Function;

/* 6. AppTester (Classe de Aplicação)
 * Função: ponto de entrada (main).
 * Por que existe?
 * Cria os objetos (Aluno).
 * Define qual estratégia de persistência usar (ArquivoTextoRepositorio ou SerializacaoRepositorio).
 * Demonstra o uso da aplicação.
 * É como o cliente que vai ao cartório. Ele só diz “quero salvar” ou “quero recuperar” — não precisa saber como as gavetas (repositorios) funcionam. * 
 */

public class AppTester {
    public static void main(String[] args) throws Exception {
      
        List<Aluno> alunos = Arrays.asList(
            new Aluno("Maria", 20),
            new Aluno("João", 22)
        );

        // Escolher estratégia: Texto
        Servico<Aluno> servicoTxt = new Servico<>(
            new ArquivoTextoRepositorio<>("alunos.txt", Aluno::fromString)
        );

        servicoTxt.salvar(alunos);
        System.out.println("Alunos recuperados do TXT:");
        servicoTxt.carregar().forEach(System.out::println);

        // Escolher estratégia: Binário
        Servico<Aluno> servicoBin = new Servico<>(
            new SerializacaoRepositorio<>("alunos.dat")
        );

        servicoBin.salvar(alunos);
        System.out.println("\nAlunos recuperados do BIN:");
        servicoBin.carregar().forEach(System.out::println);
    }
}
