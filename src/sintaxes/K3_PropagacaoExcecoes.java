package sintaxes;

public class K3_PropagacaoExcecoes {
    public static void main(String[] args) {
        try {
            System.out.println("Início do main");
            metodo1();
            System.out.println("Fim do main (normal)");
        } catch (Exception e) {
            System.out.println("Exceção capturada no main: " + e.getMessage());
        }
        System.out.println("Fim do programa");
    }

    public static void metodo1() {
        try {
            System.out.println("Início do metodo1");
            metodo2();
            System.out.println("Fim do metodo1 (normal)");
        } catch (NullPointerException e) {
            System.out.println("Exceção capturada no metodo1: " + e.getMessage());
        }
    }

    public static void metodo2() {
        try {
            System.out.println("Início do metodo2");
            metodo3();
            System.out.println("Fim do metodo2 (normal)");
        } catch (ArithmeticException e) {
            System.out.println("Exceção capturada no metodo2: " + e.getMessage());
        }
    }

    public static void metodo3() {
        System.out.println("Início do metodo3");
        // Aqui forçamos uma exceção
        String texto = null;
        System.out.println(texto.length()); // lança NullPointerException
        System.out.println("Fim do metodo3 (normal)");
    }
}

