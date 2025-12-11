package utilitarios;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe utilitária para leitura segura de dados do teclado.
 * Usa a classe Scanner, com tratamento de exceções e mensagens orientativas.
 * 
 * Autor: Professor Lorenzon, revisão 2025-2
 * Uso: salvar dentro da pasta 'utilitarios' no projeto.
 */

public class Teclado {

    // Scanner único reutilizável
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lê um valor inteiro digitado pelo usuário.
     * Repete enquanto a entrada for inválida.
     */
    public static int readInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                scanner.nextLine(); // limpa buffer
            }
        }
    }

    public static int readInt(String mensagem) {
        System.out.print(mensagem + " ");
        return readInt();
    }

    /**
     * Lê uma string (linha completa) digitada pelo usuário.
     * Rejeita entradas vazias ou limpa o buffer ao iniciar.
     */
    public static String readString() {
        while (true) {
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("");
        }
    }

    public static String readString(String mensagem) {
        System.out.print(mensagem + " ");
        return readString();
    }

    /**
     * Lê um valor decimal (double) digitado pelo usuário.
     * Repete enquanto a entrada for inválida.
     */
    public static double readDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número decimal.");
                scanner.nextLine(); // limpa buffer
            }
        }
    }

    public static double readDouble(String mensagem) {
        System.out.print(mensagem + " ");
        return readDouble();
    }

    /**
     * Lê uma data no formato yyyy-MM-dd (ex: 2025-07-24).
     * Repete até o usuário digitar um formato válido.
     */
    public static LocalDate readDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String entrada = readString();
            try {
                return LocalDate.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato AAAA-MM-DD.");
            }
        }
    }

    public static LocalDate readDate(String mensagem) {
        System.out.print(mensagem + " ");
        return readDate();
    }
}


