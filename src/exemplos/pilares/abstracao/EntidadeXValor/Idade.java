package exemplos.pilares.abstracao.EntidadeXValor;

import java.util.Objects;

/*Seguindo a ideia de raciocínio de Nome */

public class Idade {
    private final int valor;

    public Idade(int valor) {
        if (valor < 0 || valor > 120) {
            throw new IllegalArgumentException("Idade inválida. Deve estar entre 0 e 120 anos.");
        }
        this.valor = valor;
    }

    public int getIdade() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Idade)) return false;
        Idade idade = (Idade) o;
        return valor == idade.valor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor + " anos";
    }
}