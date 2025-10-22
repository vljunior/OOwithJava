package exemplos.SOLID;

/*Uma base de problema
 * 
 * class Calculadora {
    public double calcular(String operacao, double a, double b) {
        if (operacao.equals("soma")) {
            return a + b;
        } else if (operacao.equals("subtracao")) {
            return a - b;
        } else if (operacao.equals("multiplicacao")) {
            return a * b;
        } else if (operacao.equals("divisao")) {
            return a / b;
        }
        return 0;
    }
}
*/

//A solução

interface Operacao {
    double executar(double a, double b);
}

class Soma implements Operacao {
    public double executar(double a, double b) {
        return a + b;
    }
}

class Subtracao implements Operacao {
    public double executar(double a, double b) {
        return a - b;
    }
}

class Calculadora {
    public double calcular(Operacao operacao, double a, double b) {
        return operacao.executar(a, b);
    }
}

class Multiplicacao implements Operacao {
    public double executar(double a, double b) {
        return a * b;
    }
}

class Divisao implements Operacao {
    public double executar(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não permitida");
        }
        return a / b;
    }
}

public class OCP {
    public static void main(String[] args) {
        
        Calculadora calculadora = new Calculadora();

        // Operações básicas
        Operacao soma = new Soma();
        Operacao subtracao = new Subtracao();

        double resultado1 = calculadora.calcular(soma, 10, 5);
        double resultado2 = calculadora.calcular(subtracao, 10, 5);

        System.out.println("Resultado da Soma: " + resultado1);
        System.out.println("Resultado da Subtração: " + resultado2);

        // Estendendo sem modificar a Calculadora
        Operacao multiplicacao = new Multiplicacao();
        double resultado3 = calculadora.calcular(multiplicacao, 10, 5);
        System.out.println("Resultado da Multiplicação: " + resultado3);

        Operacao divisao = new Divisao();
        double resultado4 = calculadora.calcular(divisao, 10, 5);
        System.out.println("Resultado da Divisão: " + resultado4);
    }
}