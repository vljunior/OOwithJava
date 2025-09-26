package exemplos.pilares.abstracao.EntidadeXValor;

import java.util.Objects;
import java.util.UUID;

import utilitarios.GeradorIdentificadorUnico;

public class Aluno {
    private final int id;       // Identidade única da Entidade
    private final CPF cpf;       // Objeto Valor
    private final Nome nome;     // Objeto Valor
    private final Idade idade;   // Objeto Valor

    public Aluno(CPF cpf, Nome nome, Idade idade) {
        //Classe identidade deve ter um id único
        this.id = GeradorIdentificadorUnico.gerarIDUnicoInt(); // gera identificador único
        //OU, poderia ser o CPF, mas não se pensa assim, gera-se um ID pra chave, normalmente int e não String
        this.cpf = Objects.requireNonNull(cpf);
        this.nome = Objects.requireNonNull(nome);
        this.idade = Objects.requireNonNull(idade);
    }

    /* Aqui fere DDD, Object Calisthenics e Clean Code: 
    public UUID getId() {
        return id;
    }

    public CPF getCpf() {
        return cpf;
    }

    public Nome getNome() {
        return nome;
    }

    public Idade getIdade() {
        return idade;
    }

    */

    //Aqui corrige, trazendo um encapsulamento "decente"

    /** Retorna o identificador único do aluno */
    public int obterIdentificador() {
        return id;
    }

    /** Retorna uma descrição textual do aluno */
    public String obterNome() {
        //faz-se aqui pra que lá no uso não se tenha aluno.nome.getNome... ou seja . . .
        return nome.getNome();
    }

    /** Verifica se o aluno é maior de idade */
    public boolean isMaiorDeIdade() {
        return idade.getIdade() >= 18;
    }

    /** Verifica se o CPF do aluno corresponde ao informado */
    public boolean temCpf(String numeroCpf) {
        return this.cpf.getCPF().equals(numeroCpf.replaceAll("\\D", ""));
    }

    /** Regra de negócio: exemplo simples de poder se matricular */
    public boolean podeSeMatricular() {
        return isMaiorDeIdade(); // aqui poderia haver mais regras no futuro
    }

    //Se precisar de retorno do cpf, idade e etc... se fará, mas não teria sentido criar pra seguir a regra "boba" de encapsular

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", cpf=" + cpf +
                ", nome=" + nome +
                ", idade=" + idade +
                '}';
    }
}
