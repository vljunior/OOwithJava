package gerencia_hospede;

public class Hospede {
    private String nome;
    private String cpf;
    private String telefone;

    public Hospede(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }   

    public String getTelefone() {
        return telefone;
    }

    public String exibirInfo() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone;
    }

    @Override
    public String toString() {
        return nome + ";" + cpf + ";" + telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
