package temadogrupo.Menus;

import java.util.ArrayList;

public class MenuPrincipal {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Participantes");
        opcoes.add("Artistas");
        opcoes.add("Eventos");
        opcoes.add("Ingressos");
        opcoes.add("Sair");

        Menu menu = new Menu("MENU PRINCIPAL", opcoes);
        return menu.exibir();
    }
}

