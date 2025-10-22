package exemploaluno;

public class App {
    public static void main(String[] args) {
        AlunoService service = new AlunoService(new AlunoRepositoryHibernate());

        //Aqui poderia duplicar
        //Aluno aluno = new Aluno(new CPF("12546879458"), "2025003", "Ana Silva", 20);
        //service.cadastrarAluno(aluno);

        System.out.println("Alunos cadastrados:");
        //Function com Lambda

        for (Aluno aluno : service.listarAlunos()) {
            System.out.println(aluno);
        }
    }
}
