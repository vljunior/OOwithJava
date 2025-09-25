package exemplos.objectCalisthenics;

import exemplos.PersistenciaOO.Generics.Aluno.Aluno;

/* 7. “Não use mais de duas variáveis de instância por classe” 
 *     O objetivo não é uma “lei rígida”, mas uma restrição didática: forçar a pensar em classes menores, mais focadas e coesas.
 *     Se uma classe precisar de muitos atributos, isso pode ser um indício de que ela está assumindo mais de uma responsabilidade (violando o SRP – Single Responsibility Principle).
 *     A solução geralmente é extrair objetos e compor a classe.
 * 
 *     Criar composições e Injetar. 
 * 
 *     Não é possível levar a regra do 2, mas levar ao máximopossível sim!
 * 
 */

/*public*/ class AlunoProblema {
    private String nome;
    private int idade;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    private String matricula;

    public AlunoProblema (String nome, int idade, String cpf, String email, String endereco, String telefone, String matricula) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.matricula = matricula;
    }
}

/* A classe faz tudo.
 * Dificulta validações (ex: CPF válido, email formatado).
 * Viola SRP: está misturando identidade civil, dados de contato e informações acadêmicas. 
 */


 //Solução

class Nome {
    private final String valor;
    public Nome(String valor) {
        if (valor == null || valor.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.valor = valor;
    }
    @Override
    public String toString() {
        return valor;
    }
}

class Contato {
    private final String email;
    private final String telefone;

    public Contato(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
    }

    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
}

class Documento {
    private final String cpf;

    public Documento(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    public String getCpf() { return cpf; }
}

//Agora o Aluno fica simples, com apenas duas variáveis de instância:

class AlunoOK {
    private final Nome nome;
    private final Contato contato;

    public AlunoOK(Nome nome, Contato contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public Nome getNome() { return nome; }
    public Contato getContato() { return contato; }
}


public class NaoMais2Atributos {
    public static void main(String[] args) {
        AlunoOK aluno = new AlunoOK(
            new Nome("Maria"),
            new Contato("maria@email.com", "99999-9999")
        );

        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Email: " + aluno.getContato().getEmail());
    }
}

/* Não é uma contradição ao dp Builder!!! */
