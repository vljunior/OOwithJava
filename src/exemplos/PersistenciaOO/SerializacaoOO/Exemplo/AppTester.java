package exemplos.PersistenciaOO.SerializacaoOO.Exemplo;

/* Conexão com SOLID + Patterns
 * SRP: cada classe faz uma coisa só (Aluno = dados, Repositorio = contrato, etc.).
 * OCP: novas persistências (JSON, Banco de Dados) podem ser criadas sem modificar as outras classes.
 * LSP: qualquer repositório pode substituir outro (ArquivoTextoRepositorio <-> SerializacaoRepositorio).
 * ISP: a interface Repositorio<T> tem apenas os métodos essenciais.
 * DIP: Servico<T> depende da interface, não de uma classe concreta.
 * Strategy Pattern: diferentes formas de persistência são estratégias intercambiáveis. 
 */


import java.util.*;
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

        // 1. Criar lista de alunos
        List<Aluno> alunos = new ArrayList<Aluno>();
        alunos.add(new Aluno("Maria", 20));
        alunos.add(new Aluno("João", 22));

        // 2. Criar serviço de TXT
        Servico<Aluno> servicoTxt = new Servico<Aluno>(
            new ArquivoTextoRepositorio<Aluno>("alunos.txt", new Aluno("", 0))
        );

        // 3. Salvar alunos
        servicoTxt.salvar(alunos);

        // 4. Recuperar alunos
        System.out.println("Alunos recuperados do TXT:");
        for (Aluno a : servicoTxt.carregar()) {
            System.out.println(a);
        }

        // 5. Criar serviço de BIN
        Servico<Aluno> servicoBin = new Servico<>(
            new BinarioRepositorio<Aluno>("alunos.dat")
        );

        // 6. Salvar alunos
        servicoBin.salvar(alunos);

        // 7. Recuperar alunos
        System.out.println("\nAlunos recuperados do BIN:");
        for (Aluno a : servicoBin.carregar()) {
            System.out.println(a);
        }
    }
}