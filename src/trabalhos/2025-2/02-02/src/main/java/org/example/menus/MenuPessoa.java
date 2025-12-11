package org.example.menus;

import java.util.ArrayList;

public class MenuPessoa {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Listar");
        opcoes.add("Remover");
        opcoes.add("Cadastrar");
        opcoes.add("Atualizar");
        opcoes.add("Voltar");
        Menu menu = new Menu("CRUD Pessoa!", opcoes);
        return menu.exibir();
    }
}