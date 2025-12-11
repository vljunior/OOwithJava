package escolaIdiomas.menu;

import java.util.ArrayList;

public class MenuPessoa {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Listar Alunos ou Professores.");
        opcoes.add("Procurar Aluno ou Professor por ID.");
        opcoes.add("Cadastrar Novo Aluno ou Professor.");
        opcoes.add("Atualizar Dados de Alunos ou de Professores.");
        opcoes.add("Remover Aluno ou Professor do sistema.");
        
        opcoes.add("Voltar.");
        Menu menu = new Menu("Gerenciamento de Alunos e Professor", opcoes);
        return menu.exibir();
    }
}
