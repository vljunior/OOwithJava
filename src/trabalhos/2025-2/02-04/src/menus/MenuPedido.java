package menus;

import java.util.ArrayList;

public class MenuPedido {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Listar pedidos");
        opcoes.add("Localizar pedidos");
        opcoes.add("Cadastrar um novo pedido");
        opcoes.add("Remover um pedido");
        opcoes.add("Atualizar pedidos");
        opcoes.add("Voltar");
        Menu menu = new Menu("CRUD pedido!", opcoes);
        return menu.exibir();
    }
}
