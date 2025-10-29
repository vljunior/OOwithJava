package exemploaluno;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CPF {
    private String numero;

    protected CPF() {} // exigido pelo JPA

    public CPF(String numero) {
        if (!numero.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido!");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CPF cpf)) return false;
        return Objects.equals(numero, cpf.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
