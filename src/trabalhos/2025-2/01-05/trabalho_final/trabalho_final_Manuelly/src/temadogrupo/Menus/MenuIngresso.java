package temadogrupo.Menus;

import java.util.ArrayList;

public class MenuIngresso {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Vender Ingresso");
        opcoes.add("Listar Ingressos");
        opcoes.add("Voltar");

        Menu menu = new Menu("Menu de Ingressos", opcoes);
        return menu.exibir();
    }
}
