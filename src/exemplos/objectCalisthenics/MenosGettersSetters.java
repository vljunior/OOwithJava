package exemplos.objectCalisthenics;

/* 9. “Sem getters/setters: em vez de expor dados, exponha comportamento.”
 *
 * O problema dos getters e setters
 * 
 * Eles exibem o estado interno do objeto, "violando" encapsulamento.
 * Transformam o objeto em uma “struct com métodos”, o famoso objeto anêmico (só guarda dados, mas não tem comportamento).
 * Fazem com que outra classe tenha que tomar decisões com base nos atributos de um objeto, em vez do próprio objeto decidir.
 * 
 * Muito fácil de perceber quqando colocamos necessidades de validação ou comportamento
 * fora da classe e não dentro dela. Nos preocupando com get e set e não
 * com o que se deve fazer e validar.
 * 
 * Eliminar get e set e dar corpo ao comportamento.
 *  
 */

/*public*/ class PessoaAnemica {
    private int idade;

    public PessoaAnemica (int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Pensando bem, qual a diferença desta classe e a mesma com
    // public int idade? Apenas não ter os métodos....
}

/* Aqui trazendo a necessidade fora da classe, que deveria estar no comportamento
 * 
 *  Pessoa p = new Pessoa(17);
 *  
 *  //Usando No elses...
 *  if (!(p.getIdade() >= 18)) { * 
 *     System.out.println("Não pode dirigir.");
 *     return;
 *  }
 * 
 *  System.out.println("Pode dirigir."); *  
 *  
 *  O código externo decide a regra do domínio.
 *  Pessoa virou só um armazenador de dados.   * 
 * 
 */

// Abstrair não é apenas coletar atributos e fazer Get e Set
// Encapsular é trazer o comportamento pra dentro da classe,
// É perceber que o que se faz fora em termos de regras de 
// negócio é errado, pois deveria ser comportamento da classe.

/*public*/ class PessoaMenosGettersSettersMaisComportamento {
    private int idade;

    public PessoaMenosGettersSettersMaisComportamento (int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade inválida.");
        }
        this.idade = idade;
    }

    public boolean ehMaiorDeIdade() {
        return idade >= 18;
    }

    public boolean podeDirigir() {
        return ehMaiorDeIdade();
    }
}

//Uso:

/* Pessoa p = new Pessoa(17);

   if (p.podeDirigir()) {
       System.out.println("Pode dirigir.");
   } else {
       System.out.println("Não pode dirigir.");
   }
*/



public class MenosGettersSetters {

}
