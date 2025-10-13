package exemplos.PersistenciaOO.SerializacaoOO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Classe de entidade simples, implementa Serializable
class Entidade implements Serializable {

    private static final long serialVersionUID = 1L; // boa prática

    private int codigo; //chave?    
    private String nome;

    public Entidade(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() { 
        return codigo; 
    }
    
    public String getNome() { 
        return nome; 
    }

    @Override
    public String toString() {
        return "Entidade {codigo= " + codigo + ", nome= " + nome + "}";
    }
}

public class QuickExemplo {      
    public static void main(String[] args) {
        String ARQUIVO = "entidades.dat"; //"Tipado, binário"

        // Lista inicial
        List<Entidade> lista = new ArrayList<>();
        lista.add(new Entidade(1, "José Toniazzo"));
        lista.add(new Entidade(2, "Marcos Moretto"));
        lista.add(new Entidade(3, "Valdemar Lorenzon Junior"));

        // Serialização (Gravar os objetos no arquivo)
        try {
            FileOutputStream fos = new FileOutputStream(ARQUIVO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            System.out.println("Objetos salvos em " + ARQUIVO);
            oos.close();
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }        

        // Desserialização (ler objetos do arquivo)
        try {
            FileInputStream fis = new FileInputStream(ARQUIVO);
            ObjectInputStream ois = new ObjectInputStream(fis);            
            List<Entidade> recuperados = (List<Entidade>) ois.readObject();
            System.out.println("\nObjetos recuperados do arquivo:");
            ois.close();
            for (Entidade e : recuperados) {
                System.out.println(e);
            }
        }
        catch (ClassNotFoundException e) {
        } 
        catch (IOException e) { 
        }            
    }
}