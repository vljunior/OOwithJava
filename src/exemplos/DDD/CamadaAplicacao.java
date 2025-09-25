package exemplos.DDD;


//Camada de Aplicação: Orquestra chamadas sem conter regras de negócio.

/* Aplicação decide a regra: ERRADO
class App {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("123", "João", 20);
        if (cliente.getIdade() >= 18) {
            System.out.println("Pode comprar!");
        }
    }
}*/

// Regra dentro da entidade, App só orquestra : CORRETO
public class CamadaAplicacao {
    public static void main(String[] args) {
   /*   Cliente cliente = new Cliente("123", "João", 20);

        if (cliente.podeComprar()) {
            System.out.println("Pode comprar!");
        }
    */
    }
}
