package exemplos.pilares.Herança;

import utilitarios.Video;

class Cliente {
    private final String cpf;
    private String nome;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente c = (Cliente) o;
        return cpf.equals(c.cpf); // igualdade por identidade
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }

    @Override
    public String toString() {
        return "Cliente{cpf='" + cpf + "', nome='" + nome + "'}";
    }
}

final class Endereco {
    private final String rua;
    private final String cidade;

    public Endereco(String rua, String cidade) {
        this.rua = rua;
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco e = (Endereco) o;
        return rua.equals(e.rua) && cidade.equals(e.cidade);
    }

    @Override
    public int hashCode() {
        return rua.hashCode() + cidade.hashCode();
    }

    @Override
    public String toString() {
        return rua + ", " + cidade;
    }
}

public class EqualsHashCodeToString {

    public static void main(String[] args) {

        Cliente c1, c2;
        Endereco e1, e2;

        Boolean iguais;

        c1 = new Cliente("71221445687", "Cliente 1");
        c2 = new Cliente("71221445688", "Cliente 2");

        e1 = new Endereco("Rua Tal", "Cidade Qual");
        e2 = new Endereco("Rua Tal", "Cidade Qual");

        //objeto entidade, referencias distintas, id diferentes
        iguais = c1.equals(c2);
        Video.mensagem("c1 = " + c1);
        Video.mensagem("c1 = " + c2);
        Video.mensagem("c1 == c2? " + iguais);

        //objeto valor, mesma referencia, valores iguais    
        iguais = e1.equals(e2);
        Video.mensagem("e1 = " + e1);
        Video.mensagem("e2 = " + e2);

        Video.mensagem("e1 == e2? " + iguais);
    }

}
