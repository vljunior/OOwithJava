package exemplos.pilares.Encapsulamento;
import javax.management.InvalidAttributeValueException;

import utilitarios.*;

/*
 * Onde entra o princípio Tell, Don’t Ask 
 * O princípio diz: em vez de perguntar ao objeto sobre seu estado para tomar uma decisão fora dele, 
 * é melhor dizer ao objeto o que deve fazer, deixando que ele encapsule a lógica. 
 * Isso evita objetos anêmicos e distribui melhor as responsabilidades.
 * 
 * No caso, o PrincipioContador poderia ser responsável por saber se ainda deve continuar. 
 * Por exemplo, em vez de expor getValor(), você poderia expor um método como:
 * 
 * 
 * public boolean naoUltrapassouLimite() {
 *     return valor < limite;
 * }
 * 
 * E o laço ficaria:
 * 
 * for (; contador.naoUltrapassouLimite(); contador.incrementar()) {
 *     Video.mensagem("Esta e uma mensagem");
 * }
 * 
 */

class PrincipioContador {

    //Fazer um Builder....

    private int valor;
    private int passo;
    //private int limite; Para se tornar Tell, don't Ask.

    public PrincipioContador() {
        setValor(0);
        setPasso(1);
    }

    public PrincipioContador(int valor) {
        setValor(valor);
        setPasso(1);
    }

    public PrincipioContador(int valor, int passo) {
        setValor(valor);
        setPasso(passo);
    }

    private void setValor(int valor) {
        //validação
        this.valor = valor;
        //Dispararia uma instrução de gatilho de exceção
        //throws new InvalidAttributeValueException("Valor inválido");
    }

    private void setPasso(int valor) {
        //validação
        this.passo = valor;
    }    

    //Avaliar, pois é Ask
    public int getValor() {
        return valor;
    }

    //Avaliar, pois é Ask
    public int getPasso() {
        return passo;
    }
    
    public void incrementar(){
        setValor(getValor() + getPasso());
    }

    public void incrementar(int valor){
        setValor(getValor() + valor);
    }

    public void decrementar(){
        setValor(getValor() - getPasso());

    }

    public void decrementar(int valor){
        setValor(getValor() - valor);
    }

}

public class TellDontAsk {    

    public static void main(String[] args) throws Exception {        

        Video.limparTela();  
        Video.cabecalho("Certificação de abstração e encapsulamento!");    
        
        PrincipioContador contador = new PrincipioContador();

        /*Se o contador faz parte do domínio (por exemplo, um contador de tentativas, de pontos, 
        de limite de acesso etc.), faz sentido que ele saiba os limites e exponha esse comportamento. 
        Se o contador é puramente técnico (só um loop genérico, sem semântica no domínio), 
        então pode ser exagero encapsular a condição — o for básico com getValor() já cumpre o papel. */

        for (; contador.getValor() < 5 ; contador.incrementar()){

            Video.mensagem("Esta e uma mensagem");

        }
    
        System.exit(0); //isso em qualquer lugar fecha a aplicação
    }
}
    
/*
 * Ferindo ou não o princípio?
 * Ferindo (um pouco): quando você expõe o estado cru (getValor()) e deixa a decisão fora da classe, 
 * está mais no estilo Ask, Don’t Tell.
 * Mais alinhado: encapsular a condição dentro do objeto, transformando a consulta em 
 * comportamento de domínio (naoUltrapassouLimite(), aindaPodeIncrementar(), etc.).
 * 
 * Dá para mover a condição para dentro do PrincipioContador, e isso aproxima do princípio Tell, 
 * Don’t Ask. Não é errado deixar a comparação fora (especialmente em casos técnicos), 
 * mas perde-se parte da intenção de DDD e encapsulamento.
 * A chave é: se o contador tem semântica no domínio, encapsule. 
 * Se é só um detalhe técnico, mantenha simples.
 * 
 */
