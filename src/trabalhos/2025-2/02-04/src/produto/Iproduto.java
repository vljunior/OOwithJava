package produto;
import java.util.ArrayList;

public interface Iproduto {

    String getNomeProduto();
    ArrayList <String> listarProduto();
    void addProduto(String nome, int quantidade);
    void setNomeProduto(String nomeProduto);
    int getQuantidadeProduto();
    void setValor(double valor);

}
