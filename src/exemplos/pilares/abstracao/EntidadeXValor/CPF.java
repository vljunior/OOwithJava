package exemplos.pilares.abstracao.EntidadeXValor;

import java.util.Objects;

public class CPF {
    private final String numero;

    public CPF(String numero) {      

        //Guard Clause
        if (numero == null) {
            //Early Return
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }

        // Sanitização: remove caracteres não numéricos
        String apenasDigitos = numero.replaceAll("\\D", "");

        if (!apenasDigitos.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");            
        }

        //ver métodos privados
        if (!isValido(apenasDigitos)) {
            throw new IllegalArgumentException("CPF inválido: " + numero);
        }

        //Happy path
        this.numero = apenasDigitos;       
    }

    public String getCPF() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CPF)) return false;
        CPF cpf = (CPF) o;
        return numero.equals(cpf.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return numero;
    }

    //Algoritmo oficial de validação do CPF
    private boolean isValido(String cpf) {
        if (cpf == null || cpf.length() != 11) return false;

        // Rejeita CPFs com todos os dígitos iguais (ex: 11111111111)
        if (cpf.chars().distinct().count() == 1) return false;

        try {
            int digito1 = calcularDigito(cpf.substring(0, 9), 10);
            int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, 11);

            return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int calcularDigito(String base, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (char c : base.toCharArray()) {
            soma += Character.getNumericValue(c) * peso--;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}