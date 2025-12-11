package modelos;
import java.util.ArrayList;
import utilitarios.Video;

public class Cardapio{

    
    final ArrayList<Prato> pratos = new ArrayList<>();
    public Cardapio() {
        caregarPratos();
    }

    private void caregarPratos(){
        pratos.add(new Prato("Macarao ao molho branco", 75, "Prato Principal"));
        pratos.add(new Prato("Lasanha a bolonhesa", 80, "Prato Principal"));
        pratos.add(new Prato("Batata frita", 15, "Entrada"));
        pratos.add(new Prato("Salada", 20, "Entrada"));
        pratos.add(new Prato("Pudim de leite", 10, "Sobremesa"));
        pratos.add(new Prato("Mousse de maracuja", 12, "Sobremesa"));
    }

    public void exibirPratos() {

        Video.cabecalho("Cardápio");
        for (int i = 0; i < pratos.size(); i++) {
            System.out.println((i + 1) + " - " + pratos.get(i));
        }
        Video.separador();
    }

    public int getQuantidadePratos() {
        return pratos.size();
    }

    public Prato getPrato(int indice) {
        if (indice >= 0 && indice < pratos.size()) {
            return pratos.get(indice);
        }
        return null;
    }
}
