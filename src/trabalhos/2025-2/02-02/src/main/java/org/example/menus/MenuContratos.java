package org.example.menus;

import java.util.ArrayList;

public class MenuContratos {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Registrar Nova Venda");
        opcoes.add("Listar Vendas");
        opcoes.add("Gerenciar Produtos (Estoque)");
        opcoes.add("Voltar");
        Menu menu = new Menu("Menu Vendas e Contratos", opcoes);
        return menu.exibir();
    }
}