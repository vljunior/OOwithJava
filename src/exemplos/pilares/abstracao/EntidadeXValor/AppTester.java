package exemplos.pilares.abstracao.EntidadeXValor;

public class AppTester {
    public static void main(String[] args) {
        CPF cpf = new CPF("529.982.247-25"); // válido
        Nome nome = new Nome("João Silva");
        Idade idade = new Idade(20);

        Aluno aluno = new Aluno(cpf, nome, idade);

        System.out.println(aluno.obterNome());
        System.out.println("É maior de idade? " + aluno.isMaiorDeIdade());
        System.out.println("Pode se matricular? " + aluno.podeSeMatricular());
        System.out.println("Confere CPF? " + aluno.temCpf("52998224725"));
    }
}

/**
 * ---------------------------------------------------------------------------
 * ENTIDADE E OBJETOS DE VALOR: BOAS PRÁTICAS E QUALIDADE DE CÓDIGO
 * ---------------------------------------------------------------------------
 * 
 * Este exemplo ilustra o uso de uma ENTIDADE (Aluno) composta por OBJETOS DE VALOR
 * (CPF, Nome e Idade) em vez de usar tipos primitivos diretamente (String, int).
 * 
 * POR QUE NÃO USAR TIPOS PRIMITIVOS DIRETAMENTE?
 * ----------------------------------------------
 * - Se usássemos "String cpf" ou "int idade" diretamente, o código ficaria frágil:
 *   qualquer parte do sistema poderia atribuir valores inválidos sem validação.
 * - A responsabilidade de validação ficaria espalhada em vários lugares (alta viscosidade),
 *   tornando difícil manter consistência ao longo do sistema.
 * 
 * VANTAGENS DE USAR OBJETOS DE VALOR:
 * -----------------------------------
 * 1. **Validação centralizada**:
 *    - CPF valida se o número é consistente e sanitiza entrada.
 *    - Nome garante que não é vazio e normaliza.
 *    - Idade garante que está em faixa válida.
 *    Assim, qualquer uso de CPF/Nome/Idade já nasce válido.
 *
 * 2. **Baixo acoplamento e alta coesão**:
 *    - Cada classe encapsula sua própria regra de negócio.
 *    - A Entidade Aluno não precisa conhecer as regras internas do CPF, Nome ou Idade.
 *    - Alterar a validação de CPF não exige mudar o código da Entidade.
 *
 * 3. **Legibilidade e expressividade**:
 *    - O código fica mais semântico: em vez de "String cpf", temos "CPF cpf".
 *    - O domínio é melhor representado: fica claro que CPF é um objeto especial,
 *      com regras próprias, e não "só uma string".
 *
 * 4. **Evita fragilidade do código**:
 *    - Fragilidade acontece quando uma pequena mudança quebra várias partes.
 *    - Ao encapsular comportamento em Objetos de Valor, reduzimos esse risco.
 *
 * 5. **Evita viscosidade do código**:
 *    - Viscosidade é quando a solução correta é mais difícil de aplicar que a errada.
 *    - Com Objetos de Valor, a validação é automática e o caminho correto (usar Nome, CPF, Idade)
 *      se torna natural e simples para o programador.
 *
 * 6. **Enriquecimento do domínio**:
 *    - Em vez de expor dados crus via getters, a Entidade oferece métodos de comportamento
 *      (ex: ehMaiorDeIdade, podeSeMatricular), aumentando a aderência ao DDD.
 *
 * RESUMO:
 * -------
 * - Objetos de Valor trazem robustez, clareza e segurança.
 * - Evitam fragilidade e viscosidade do código.
 * - Garantem baixo acoplamento, alta coesão e regras de negócio centralizadas.
 * - Promovem boas práticas de programação orientada a objetos.
 * ---------------------------------------------------------------------------
 */
