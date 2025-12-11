package temadogrupo.Menus;

import java.util.ArrayList;

public class MenuEvento {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Cadastrar Evento");
        opcoes.add("Listar Eventos");
        opcoes.add("Adicionar Apresentação");
        opcoes.add("Listar Apresentações");
        opcoes.add("Voltar");

        Menu menu = new Menu("Menu de Eventos", opcoes);
        return menu.exibir();
    }
}
