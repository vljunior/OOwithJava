package escolaIdiomas.menu;

import java.util.ArrayList;

public class MenuPrincipal {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Gerenciar Alunos e Professores");
        opcoes.add("Gerenciar Turmas");
        opcoes.add("Gerenciar Cursos");
        opcoes.add("Listar Todos os Dados");
        opcoes.add("Sair");
        Menu menu = new Menu("Sistema da Escola de Idiomas", opcoes);
        return menu.exibir();
    }
}
