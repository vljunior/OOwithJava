package exemplos.objectCalisthenics;

/* 3. Envolva todos os tipos primitivos e strings
 * Em vez de usar int, double ou String diretamente, crie objetos que representem conceitos.
 * Exemplo: em vez de int idade, use uma classe Idade.
 * Isso adiciona semântica, validações e clareza. 
 * 
 * Fazer isso pra email, cpf, sanitizar e envolver em classes
 * 
 */

//O problema

class AlunoRuim { //Aluno
    
    private String nome;
    private int idade;

    public AlunoRuim(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public boolean podeSeMatricular() {
        return idade >= 18;
    }

    public String getNome() {
        return nome;
    }
}

/*
 * idade é só um int, sem significado além do valor numérico.
 * Validações ficam espalhadas (idade >= 18 poderia estar em vários lugares do sistema).
 * nome é só String, sem regra própria (ex: não permitir vazio, tamanho mínimo, etc.).
 * 
 */

 //Encapsulando tipos especiais

 class Nome {
    private final String valor;

    public Nome(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}


class Idade {
    private final int valor;

    public Idade(int valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
        this.valor = valor;
    }

    public boolean ehMaiorDeIdade() {
        return valor >= 18;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}

//O uso

class AlunoBom {
    private final Nome nome;
    private final Idade idade;

    public AlunoBom (Nome nome, Idade idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public boolean podeSeMatricular() {
        return idade.ehMaiorDeIdade();
    }

    public Nome getNome() {
        return nome;
    }
}

public class PrimitivosEspalhados {
    public static void main(String[] args) {
        AlunoBom aluno = new AlunoBom(new Nome("Maria"), new Idade(20));
        
        if (aluno.podeSeMatricular()) {
            System.out.println(aluno.getNome() + " pode se matricular.");
        }
    }
}

/* Semântica: agora não é "um int qualquer", mas uma Idade.
 * Validações centralizadas: qualquer instância de Idade já nasce válida.
 * Domínio mais expressivo: ler idade.ehMaiorDeIdade() é muito mais claro do que idade >= 18.
 * Imutabilidade: os objetos não podem ser alterados de fora (boa prática em Value Objects). 
 */ 


