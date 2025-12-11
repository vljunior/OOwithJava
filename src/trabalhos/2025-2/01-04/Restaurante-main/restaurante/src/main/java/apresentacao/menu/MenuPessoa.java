package apresentacao.menu;
import java.util.ArrayList;

public class MenuPessoa {

    public static int exibir() {
        ArrayList<String> opcoes = new ArrayList<>();
        Menu menu = new Menu("CRUD Pessoa!", opcoes);
        opcoes.add("Cadastrar");
        opcoes.add("Listar");
        opcoes.add("Atualizar");
        opcoes.add("Apagar");
        opcoes.add("Voltar");
        return menu.exibir();
    }
}
