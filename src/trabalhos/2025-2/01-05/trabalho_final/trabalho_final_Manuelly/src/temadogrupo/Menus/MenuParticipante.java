package temadogrupo.Menus;

import java.util.ArrayList;

public class MenuParticipante {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Cadastrar Participante");
        opcoes.add("Listar Participantes");
        opcoes.add("Atualizar Participante");
        opcoes.add("Excluir Participante");
        opcoes.add("Voltar");

        Menu menu = new Menu("Menu de Participantes", opcoes);
        return menu.exibir();
    }
}

