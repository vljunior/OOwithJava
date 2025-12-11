package persistencia;

import java.io.*;
import java.util.ArrayList;

public class ArquivoTexto {

    public static void salvar(String nomeArquivo, ArrayList<String> linhas) {
        try (PrintWriter pw = new PrintWriter(nomeArquivo)) {//PrintWhiter é como o println(), mas o println() é para imprimir e o PrintWhiter é como um arquivo
            for (String linha : linhas) {
                pw.println(linha);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo: " + nomeArquivo);
        }
    }
    public static ArrayList<String> carregar(String nomeArquivo) {
        ArrayList<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo: " + nomeArquivo);
        }
        return linhas;
    }
}
