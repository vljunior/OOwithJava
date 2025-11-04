package exemplos.pilares.polimorfismo.subtipoliskov;

import utilitarios.*;

/*
 *
 * Interfaceavel é o contrato: define o que deve existir. 
 * ClasseBase é o modelo genérico: implementa o contrato e define um comportamento padrão.
 * Concreta é o subtipo: substitui o comportamento, mantendo a compatibilidade.
 * SubTipo representa o cliente do código: ele trabalha com abstrações (interface), e não com implementações concretas.
 * Como qualquer subtipo pode ser passado ao método sem quebrar o programa, o LSP está sendo cumprido.
 *  
 */

/*public*/ interface Interfaceavel {
    void metodoInterfaceado();
}


/*public*/ abstract class ClasseBase implements Interfaceavel {

    @Override
    public void metodoInterfaceado() {
        Video.mensagem("Acionado método e executado pela classe ClasseBase.");
    }

}


/*public*/  class Concreta extends ClasseBase {

    @Override
    public void metodoInterfaceado() {
        Video.mensagem("Acionado método e executado pela classe Concreta.");
    }

}

public class Subtipo {

    public static void testarMensagem(Interfaceavel objeto) {
        objeto.metodoInterfaceado();
    }

    public static void main(String[] args) {

        Interfaceavel   referenciaParaImplementacao;
        ClasseBase      referenciaParaBase;
        Concreta        referenciaParaConcreta;

        // Instanciacao
        referenciaParaConcreta = new Concreta();

        // Polimorfismo de subtipo
        referenciaParaImplementacao = referenciaParaConcreta;
        referenciaParaBase = referenciaParaConcreta;

        // Teste da substituicao : LISKOV

        /*
          O Liskov Substitution Principle (Barbara Liskov, 1987) afirma:
          
          “Se S é um subtipo de T, então objetos do tipo T podem ser substituídos por objetos do tipo S 
          sem alterar as propriedades desejáveis do programa.” 
          
          Em termos simples:

          Subtipos devem poder substituir seus tipos base sem quebrar o comportamento esperado. 
        */

        testarMensagem(referenciaParaImplementacao);
        testarMensagem(referenciaParaBase);
        testarMensagem(referenciaParaConcreta);
    }

}
