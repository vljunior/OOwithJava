package exemplos.pilares.abstracao.EntidadeXValor;

import java.util.Objects;

public class Nome {
    //O nome de uma pessoa não muda.
    private final String valor;

    public Nome(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.valor = valor.trim();
    }

    public String getNome() {
        return valor;
    }

    /*Num objeto valor, o equals não se refere mais a apontart para o mesmo objeto e sim devemos
     * sobreescrever para que seja comparado o estado interno igual entre objetos
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nome)) return false;
        Nome nome = (Nome) o;
        return valor.equalsIgnoreCase(nome.valor);
    }

    /*O hashcode deve ser compatível com o que o equals faz!*/
    @Override
    public int hashCode() {
        return Objects.hash(valor.toLowerCase());
    }

    @Override
    public String toString() {
        return valor;
    }
}
