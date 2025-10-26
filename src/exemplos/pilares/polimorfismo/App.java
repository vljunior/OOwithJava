
class Exibir {

    public static void mostrar (int valor) {
        System.out.println(valor);
    }

    public static void mostrar (double valor){
        System.out.println(valor);
    }

    public static void mostrar (Object valor) {
        System.out.println(valor); //toString();
    }
}


public class App {
    public static void main(String[] args) throws Exception {
       
        Object objeto = new Object();
        Exibir.mostrar(objeto);

    }
}

/*
class Exibir {

    // Método genérico
    public static <T> void mostrar(T valor) {
        System.out.println(valor);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
       
        Object objeto = new Object();
        Exibir.mostrar(objeto);

        // Exemplos de chamadas genéricas
        Exibir.mostrar(42);          // int
        Exibir.mostrar(3.14);        // double
        Exibir.mostrar("Lorenzon");  // String
    }
}
 */
