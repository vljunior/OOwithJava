package repositorio;

import java.io.*;
import java.util.List;

public class RepositorioBinario<T> implements Repositorio<T> {
    private String arquivo;

    public RepositorioBinario(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void salvar(List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<T>) ois.readObject();
        }
    }
}
