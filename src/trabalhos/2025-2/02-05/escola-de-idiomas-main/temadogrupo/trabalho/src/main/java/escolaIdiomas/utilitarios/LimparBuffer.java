package escolaIdiomas.utilitarios;

import java.util.Scanner;

public class LimparBuffer {

    private LimparBuffer() {}

    public static void limpar(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
