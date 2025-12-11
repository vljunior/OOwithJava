package modelo;

public class Artista extends Pessoa { // Artista herda da classe pessoa
    private static final long serialVersionUID = 1L;
    private String generoMusical;

    public Artista() {
        super();
    }
    public Artista(String nome, String generoMusical) {
        super(nome);
        this.generoMusical = generoMusical;
    }
    public String getGeneroMusical() { 
        return generoMusical; 
    }
    public void setGeneroMusical(String genero) { 
        this.generoMusical = genero; 
    }
    @Override
    public String getTipo() {
        return "Artista";
    }
    @Override
    public String toString() {
        return nome + " (" + generoMusical + ")";
    }
}
