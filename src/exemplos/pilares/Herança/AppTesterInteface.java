package exemplos.pilares.Herança;

/*public*/ interface QueroFazerParte {

    void facoParte(); //método abstrato, sem corpo   

}

/*public*/ abstract class TentandoFazerParte implements QueroFazerParte {   

    //Método concreto
    public void metodoConcreto() {
        System.out.println("Eu sou um método concreto de quem tenta fazer parte!");
    }
}

/*public*/class FacoPartePelaInterface implements QueroFazerParte {

    @Override
    public void facoParte() {
        System.out.println("Eu faço parte pela implementacao da interface!");
    }
}   


/*public*/class FacoPartePelaAbstract extends  TentandoFazerParte {

    @Override
    public void facoParte() {
        System.out.println("Eu faço parte pela implementacao da classe abstrata!");
    }
}   


public class AppTesterInteface {

    public static void main(String[] args) {
        
        //QueroFazerParte obj = new QueroFazerParte(); //Não compila, interface não pode ser instanciada
        //TentandoFazerParte obj2 = new TentandoFazerParte(); //Não compila, classe abstrata não pode ser instanciada

        /*Referencia da interface, instancia pela classe concreta
          O lado esquerdo (QueroFazerParte objeto1) é o tipo de referência. 
          Ele diz: “eu só sei que este objeto tem a forma de QueroFazerParte”.
          O lado direito (new FacoPartePelaInterface()) é a especialização concreta, 
          que realmente cria o objeto.

          Estamos programando para a interface, e não para a implementação.

          “Sempre usamos a interface como referência porque assim o código depende do contrato, não da implementação.
          O new sempre vem de uma especialização concreta, porque só ela está pronta para ser usada.”

        */
        QueroFazerParte objeto1 = new FacoPartePelaInterface();
        objeto1.facoParte();

        QueroFazerParte objeto2 = new FacoPartePelaAbstract();  
        objeto2.facoParte();

        /*Se agora estivermos programando pra implementação, 
          Há necessidades, downcast
          objeto2.metodoConcreto(); //Não compila, referencia da interface não conhece o método concreto
          Quanto mais genérica for a referência, menos detalhes ela conhece.
          A interface conhece apenas o contrato mínimo, mas não os “extras”.         
         */

        ((FacoPartePelaAbstract)objeto2).metodoConcreto(); //Down

        if (objeto2 instanceof FacoPartePelaAbstract) {
            ((FacoPartePelaAbstract)objeto2).metodoConcreto(); //Down
        }

        FacoPartePelaAbstract objeto3 = new FacoPartePelaAbstract();
        objeto3.facoParte();
        objeto3.metodoConcreto(); //Compila, referencia da classe concreta conhece o método concreto, além da Interface       
        
    }    
}