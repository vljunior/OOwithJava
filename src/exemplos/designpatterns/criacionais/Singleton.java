package exemplos.designpatterns.criacionais;
public class Singleton {
    
    private static Singleton unicaInstancia;    

    //Forçando o construtor privado, para garantir o padrão Singleton e nenhuma nova instância
    private Singleton() {         
    }

    public static Singleton getInstanciaSingleton(){
        if (unicaInstancia == null) {
            unicaInstancia = new Singleton();
        }
        return unicaInstancia;
    }   

}

/*Na aplicação:
 * static Singleton objeto; //static se, por exemplo, estiver na classe App   
*  objeto = Singleton.getInstanciaSingleton();
 * 
 */