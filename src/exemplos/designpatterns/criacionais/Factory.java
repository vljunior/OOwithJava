package exemplos.designpatterns.criacionais;

//Imaginando  que cada classe estaria em um, seu prórpio, arquivo
//Criando o contrato

/*public*/interface Generica {
    String getAlgoComum();
}

//Assinando o contrato

/*public*/ class Especializacao1 implements Generica {
    @Override
    public String getAlgoComum() {
        return "Sou uma especialização.";
    }
}

/*public*/ class Especializacao2 implements Generica {
    @Override
    public String getAlgoComum() {
        return "Sou outra especialização.";
    }
}

/*public*/ class EspecializacaoFactory {
    public static Generica criarEspecializacao (String tipo) {
        
        if (tipo.equalsIgnoreCase("1")) {
            return new Especializacao1();
        } 
        
        if (tipo.equalsIgnoreCase("2")) {
            return new Especializacao2();
        } 

        return null; //ou dispara uma exceção
        //throw new IllegalArgumentException("Tipo de especialização inválido: " + tipo);        
    }
}

public class Factory {
    public static void main(String[] args) {
       // O cliente precisa conhecer as classes concretas:
	   // A referência é genérica, mas a construção é especializada
	   // Aplicação de Polimorfismo
       Generica e1 = EspecializacaoFactory.criarEspecializacao("1");
       Generica e2 = EspecializacaoFactory.criarEspecializacao("2");
       System.out.println(e1.getAlgoComum());
       System.out.println(e2.getAlgoComum());
    }
}
