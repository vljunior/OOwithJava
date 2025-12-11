package temadogrupo.Menus;

import java.util.ArrayList;

public class MenuArtista {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Cadastrar Artista");
        opcoes.add("Listar Artistas");
        opcoes.add("Voltar");

        Menu menu = new Menu("Menu de Artistas", opcoes);
        return menu.exibir();
    }
}