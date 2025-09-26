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
