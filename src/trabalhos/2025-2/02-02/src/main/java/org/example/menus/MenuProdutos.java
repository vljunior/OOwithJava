package org.example.menus;

import java.util.ArrayList;

public class MenuProdutos {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Listar Produtos");
        opcoes.add("Adicionar Novo Produto (Pão/Bolo)");
        opcoes.add("Atualizar Estoque/Preço");
        opcoes.add("Remover Produto");
        opcoes.add("Voltar");
        Menu menu = new Menu("Gestão de Produtos", opcoes);
        return menu.exibir();
    }
}