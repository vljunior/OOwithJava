package exemplos.Swing.Calculadora;

public class Adicao implements OperacaoMatematica {
    @Override
    public double calcular(double a, double b) {
        return a + b;
    }
}