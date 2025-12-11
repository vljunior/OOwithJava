package modelos;

public class Prato {

    private String nome;
    private double preco;
    private String categoria;

    public Prato(String nome, double preco, String categoria) {
        setNome(nome);
        setPreco(preco);
        setCategoria(categoria);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

   
    @Override   
    public String toString() {
        return "Nome prato: " + getNome() + " - Preço: $" + getPreco() + " - Categoria: " + getCategoria();
    }
}
