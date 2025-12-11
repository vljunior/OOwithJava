package menus;

import java.util.ArrayList;

public class MenuPrincipal {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Clientes");
        opcoes.add("Pedidos");
        opcoes.add("Sair");
        Menu menu = new Menu("Menu Principal", opcoes);
        return menu.exibir();
    }
}
