package escolaIdiomas.entidades;

import java.io.Serializable;

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    private int matricula;     
    private String nome;
    private String cpf;
    private String email;

    public Aluno(int matricula, String nome, String cpf, String email){
        this.matricula = matricula;
        setNome(nome);
        setCpf(cpf);     // limpa, valida e armazena corretamente
        setEmail(email);
    }

    public int getMatricula(){
        return matricula;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public void setCpf(String cpf) {
        String cpfLimpo = cpf.replace(".", "").replace("-", ""); // deixa só números
        validarCpf(cpfLimpo);
        this.cpf = apresentaCpf(cpfLimpo);
    }

    @Override
    public String toString(){
        
        return "Nome do aluno: " + nome +
        "\nMatricula: " + matricula +
        "\nEmail: " + email +
        "\nCpf: " + getCpf();
    }

    public static void validarCpf(String cpf){

        if (!cpf.matches("\\d+")){ //verifica se CPF tem somente numeros
            throw new IllegalArgumentException ("O cpf deve conter somente numeros!");
        }

        if (cpf.length() != 11){ //verifica se CPF tem 11 digitos
            throw new IllegalArgumentException ("Cpf deve conter 11 digitos!");
        }
    }

    private String apresentaCpf(String cpf){
        return cpf.substring(0, 3)+ "." +
        cpf.substring(3, 6) + "." +
        cpf.substring(6, 9) + "-" +
        cpf.substring(9, 11);
    }
}
