/*
 * Object Calisthenics é um conjunto de 9 princípios de programação orientada 
 * a objetos criado por Jeff Bay, com o objetivo de incentivar a escrita de
 * código mais limpo, modular e sustentável. 
 * A ideia é aplicar restrições artificiais para forçar boas práticas.
 *
 * 
 * 1. Só um nível de indentação por método
 * Evite estruturas de código muito aninhadas (ifs dentro de loops dentro de switches etc.).
 * Quebra em métodos menores facilita a leitura e manutenção.
 * 
 * 2. Não use a palavra-chave else
 * O else costuma indicar complexidade ou lógica condicional excessiva.
 * Prefira early return, polimorfismo ou tratamento de exceções para clareza.
 *
*/

package exemplos.objectCalisthenics;

//Classe com método pior e melhorado.

public class UmNivelIdentacaoSemElses { //Pedido
    private boolean pago;
    private boolean estoqueDisponivel;

    public UmNivelIdentacaoSemElses(boolean pago, boolean estoqueDisponivel) {
        this.pago = pago;
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public void processarPior() {
        if (pago) {
            if (estoqueDisponivel) {
                System.out.println("Pedido processado com sucesso!");
            } else {
                System.out.println("Não há estoque disponível.");
            }
        } else {
            System.out.println("Pagamento não realizado.");
        }
    }

    public void processarMelhor() {

        //Nega a condição, cria a cláusula de guarda
        //Fail Fast, retorna falha rápida
        if (!pago) {
            System.out.println("Pagamento não realizado.");
            //Early return
            return;             
        }

        //segue fazendo...        
        if (!estoqueDisponivel) {
            System.out.println("Não há estoque disponível.");
            return;
        }

        //Ao final, o caminho perfeito ao final, sem else
        System.out.println("Não há estoque disponível.");

        //Atendidos os itens 1 e 2.
        
    }
}

/* Conceitos:

 * 1. Fail Fast (Falhar Rápido) 
 * O princípio do fail fast defende que um código deve detectar e sinalizar problemas o mais cedo possível, interrompendo a execução em vez de tentar continuar em estado inválido.
 * A ideia é evitar que o erro se propague e cause efeitos colaterais piores.
 * Em vez de mascarar ou adiar o problema, o sistema "falha" imediatamente, forçando o desenvolvedor a tratar a causa.
 * 
 * 2. Cláusula de Guarda (Guard Clause)
 * Uma cláusula de guarda é uma condição no início do método que verifica casos inválidos ou especiais e retorna imediatamente, evitando o aninhamento de if/else.
 * Funciona como uma "sentinela": protege o método de estados inválidos.
 * Melhora a legibilidade, pois elimina níveis de indentação.
 * 
 * 3. Early Return (Retorno Antecipado) 
 * O early return é a aplicação prática do fail fast e das cláusulas de guarda: consiste em retornar de um método o mais cedo possível, assim que uma condição crítica é detectada.
 * Evita prolongar a execução quando já sabemos que não faz sentido continuar.
 * Garante clareza: os casos inválidos ou triviais são resolvidos logo de início.
 * 
 * Resumo:
 * 
 * 1. Fail Fast: Princípio: falhar logo que algo está errado.
 * 2. Cláusula de Guarda: Técnica: verificações no início do método para proteger o restante do código.
 * 3. Early Return: Prática: retornar cedo do método quando a condição crítica é encontrada.
 * 
 */