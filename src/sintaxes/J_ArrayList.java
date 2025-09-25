package sintaxes;
import java.util.ArrayList;

//desenhando uma classe qualquer - melhor em arquivo separado
class MeuObjeto{        

}

public class J_ArrayList {

    public static void varreLista (ArrayList<MeuObjeto> lista){
        //exemplo de For Each: pois ArrayList é Iterator
        for (MeuObjeto objeto : lista) {//para cada MeuObjeto de lista de objetos : já que é iterável
            System.out.printf ("\nNome: %s", objeto.toString());
        }
    }

    public static void main(String[] args) throws Exception {
       
        //tipo indica qual o ArrayList<tipo> de objeto será armazenado na lista
        ArrayList lista = new ArrayList<MeuObjeto>(); 
        MeuObjeto o1, o2;

        o1 = new MeuObjeto();
        lista.add(o1);
        o2 = new MeuObjeto();
        lista.add(o2);
        
        lista.add(new MeuObjeto());

        System.out.println (lista.indexOf(o1)); //Buscando um objeto e retornando seu "indice"       

        varreLista(lista);        
        lista.remove(o2);        
        System.out.println();
        varreLista(lista);

    }
}
