package modelo;

public class Participante extends Pessoa { // Participante herda da classe pessoa
    private static final long serialVersionUID = 1L;
    
    private int idade;

    public Participante() {
        super();
    }
    public Participante(String nome, int idade) {
        super(nome);
        this.idade = idade;
    }
    public int getIdade() { 
        return idade; 
    }
    public void setIdade(int idade) { 
        this.idade = idade; 
    }
    @Override
    public String getTipo() {
        return "Participante";
    }
    @Override
    public String toString() {
        return nome + " - Idade: " + idade;
    }
}
