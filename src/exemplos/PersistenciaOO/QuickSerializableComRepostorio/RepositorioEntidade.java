import java.io.*;
import java.util.*;

//<E> obrigatoriamente deve implementar Serializable

public class RepositorioEntidade<E extends Serializable> 
             implements Repositorio<E> {

    private final String arquivo;

    public RepositorioEntidade(Class<E> tipo) {
        this.arquivo = tipo.getSimpleName() + ".db";
    }

    @Override
    public void salvar(List<E> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        }
    }

    @Override    
    public List<E> recuperar() throws IOException, ClassNotFoundException {
        
        File f = new File(arquivo);
        
        if (!f.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<E>) ois.readObject();
        }
    }
}