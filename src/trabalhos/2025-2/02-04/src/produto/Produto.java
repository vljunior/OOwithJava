package produto;
import java.util.ArrayList;
import execoes.listaVazia;
import execoes.semProdutosRegistrados;
import execoes.valorInvalido;

public class Produto implements Iproduto {

    private String nomeProduto;
    private ArrayList <String> listaProduto = new ArrayList<>();
    private double valor;

     public Produto(String nome, double valor){  
        this.nomeProduto = nome;
        this.valor = valor;
        setValor(valor);
    }

    @Override
    public String getNomeProduto(){
        return nomeProduto;
    }

        @Override
    public ArrayList <String> listarProduto(){

        if (listaProduto.size() == 0){
            throw new semProdutosRegistrados("Não há produtos em estoque deste item selecionado.");
        }

        for (String p : listaProduto){
            System.out.println("Lista de produtos: " + p + " Preço: " +  getValor());
        }

        return listaProduto;
    }

        @Override
    public void addProduto(String nomeProduto, int quantidade){
        if (valor <= 0){
            throw new valorInvalido("Valor inválido! O valor deve ser maior que 0.");
        }
        for(int i = 0; i < quantidade; i++){
            listaProduto.add(nomeProduto);
        }
    }

        @Override
    public void setNomeProduto(String nomeProduto){
        this.nomeProduto = nomeProduto;
    }

        @Override
    public int getQuantidadeProduto(){
        if (listaProduto.isEmpty()){
            throw new listaVazia("Produto indisponível");
        }else{
        return listaProduto.size();
        }
    }

    @Override
    public void setValor(double valor){
        if (valor <= 0){
            throw new valorInvalido("Valor inválido! O valor deve ser maior que 0.");
        }else{
            this.valor = valor;
        }
    }

    
    

    public double getValor(){
        return valor;
    }
}
