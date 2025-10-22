package exemplos.pilares.polimorfismo;

//Autor: Prof. Lorenzon em 2025-2

class Calculadora {

    // Soma de dois inteiros
    public int somar(int a, int b) {
        return a + b;
    }

    // Soma de três inteiros
    public int somar(int a, int b, int c) {
        return a + b + c;
        //return somar (a, b) + c; Não é obrigatório fazer uma chamada em cascata, dependa da situação lógica
    }

    // Soma de dois números decimais
    public double somar(double a, double b) {
        return a + b;
    }

    // Soma de dois textos (concatenação)
    public String somar(String a, String b) {
        return a + b;
    }
}

public class Sobrecarga {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println(calc.somar(2, 3));          // Saída: 5
        System.out.println(calc.somar(1, 2, 3));       // Saída: 6
        System.out.println(calc.somar(2.5, 3.5));      // Saída: 6.0
        System.out.println(calc.somar("Olá, ", "Java")); // Saída: Olá, Java
    }
}
