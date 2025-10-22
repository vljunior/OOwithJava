package exemplos.pilares.Herança;

/*public*/ interface ContratoParaFazerParte {

    void assinarContrato(); //método abstrato, sem corpo   

}

/*public*/ abstract class LendoSemAssinarContrato implements ContratoParaFazerParte {   

    //Método concreto
    public void algumaImplementacaoQualquer() {
        System.out.println("Eu sou um método concreto de quem leu, mas não assinou o contrato");
    }
}

/*public*/class AssinoOContrato implements ContratoParaFazerParte {

    @Override
    public void assinarContrato() {
        System.out.println("Eu assinei o controato, com a implementacao da interface!");
    }
}   


/*public*/class AssinoTambemOContrato extends  LendoSemAssinarContrato {

    @Override
    public void assinarContrato() {
        System.out.println("Eu assino o contrato, com a implementacao da classe que leu, mas não assinou o contrato, repassando a assinatura.");
    }
}   


public class AppTesterInteface {

    public static void main(String[] args) {
        
        //ContratoParaFazerParte obj = new ContratoParaFazerParte(); //Não compila, interface não pode ser instanciada
        //LendoSemAssinarContrato obj2 = new LendoSemAssinarContrato(); //Não compila, classe abstrata não pode ser instanciada

        /*Referencia da interface, instancia pela classe concreta
          O lado esquerdo (ContratoParaFazerParte objeto1) é o tipo de referência. 
          Ele diz: “eu só sei que este objeto tem a forma de ContratoParaFazerParte”.
          O lado direito (new AssinoOContrato()) é a especialização concreta, 
          que realmente cria o objeto.

          Estamos programando para a interface, e não para a especialização.

          “Sempre usamos a interface como referência porque assim o código depende do contrato, não da implementação especializada.
          O new sempre vem de uma especialização concreta, porque só ela está pronta para ser usada.”

        */
        ContratoParaFazerParte objeto1 = new AssinoOContrato();
        objeto1.assinarContrato();

        ContratoParaFazerParte objeto2 = new AssinoTambemOContrato();  
        objeto2.assinarContrato();

        /*Se agora estivermos programando pra especializações, 
          Há necessidades de downcasting
          objeto2.algumaImplementacaoQualquer(); //Não compila, referencia da interface não conhece o método concreto
          Quanto mais genérica for a referência, menos detalhes ela conhece.
          A interface conhece apenas o contrato mínimo, mas não os “extras”.         
         */

        ((AssinoTambemOContrato)objeto2).algumaImplementacaoQualquer(); //Down

        if (objeto2 instanceof AssinoTambemOContrato) {
            ((AssinoTambemOContrato)objeto2).algumaImplementacaoQualquer(); //Down
        }

        AssinoTambemOContrato objeto3 = new AssinoTambemOContrato();
        objeto3.assinarContrato();
        objeto3.algumaImplementacaoQualquer(); //Compila, referencia da classe concreta conhece o método concreto, além da Interface       
        
    }    
}