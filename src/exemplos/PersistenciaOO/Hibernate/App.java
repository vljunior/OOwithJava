package exemplos.PersistenciaOO.Hibernate;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AlunoRepositorio repo = new AlunoRepositorio();
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n=== ALUNOS ===");
                System.out.println("1) Inserir");
                System.out.println("2) Buscar por matrícula");
                System.out.println("3) Atualizar");
                System.out.println("4) Deletar");
                System.out.println("5) Listar todos");
                System.out.println("0) Sair");
                System.out.print("Opção: ");

                String op = sc.nextLine().trim();
                if ("0".equals(op)) break;

                switch (op) {
                    case "1" -> {
                        System.out.print("Matrícula: ");
                        String m = sc.nextLine();
                        System.out.print("Nome: ");
                        String n = sc.nextLine();
                        System.out.print("Idade: ");
                        int i = Integer.parseInt(sc.nextLine());
                        repo.inserir(new Aluno(m, n, i));
                        System.out.println("Inserido!");
                    }
                    case "2" -> {
                        System.out.print("Matrícula: ");
                        String m = sc.nextLine();
                        Aluno a = repo.buscarPorMatricula(m);
                        System.out.println(a == null ? "Não encontrado." : a);
                    }
                    case "3" -> {
                        System.out.print("Matrícula: ");
                        String m = sc.nextLine();
                        Aluno a = repo.buscarPorMatricula(m);
                        if (a == null) {
                            System.out.println("Não encontrado.");
                        } else {
                            System.out.print("Novo nome (" + a.getNome() + "): ");
                            String n = sc.nextLine();
                            if (!n.isBlank()) a.setNome(n);
                            System.out.print("Nova idade (" + a.getIdade() + "): ");
                            String sIdade = sc.nextLine();
                            if (!sIdade.isBlank()) a.setIdade(Integer.parseInt(sIdade));
                            repo.atualizar(a);
                            System.out.println("Atualizado!");
                        }
                    }
                    case "4" -> {
                        System.out.print("Matrícula: ");
                        String m = sc.nextLine();
                        repo.deletar(m);
                        System.out.println("Deletado (se existia).");
                    }
                    case "5" -> {
                        List<Aluno> alunos = repo.listarTodos();
                        alunos.forEach(System.out::println);
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        } finally {
            sc.close();
            AlunoRepositorio.fechar(); // fecha EMF
        }
        System.out.println("Fim.");
    }
}
