package exemplos.Swing.Calculadora;

public class Calculadora {

    public double executarOperacao(OperacaoMatematica operacao, double a, double b) {
        return operacao.calcular(a, b);
    }
}
