package escolaIdiomas.repositorios.Repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositorioImplementacao<T> implements Repositorio<T> {

    public List<T> lista = new ArrayList<>();

    private final File arquivoPersistencia;

    public RepositorioImplementacao() {
        File pasta = new File("data");
        if (!pasta.exists()) pasta.mkdirs();

        String nomeArquivo = this.getClass().getSimpleName() + ".dat";
        arquivoPersistencia = new File(pasta, nomeArquivo);

        if (arquivoPersistencia.exists() && arquivoPersistencia.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(arquivoPersistencia)))) {

                Object obj = ois.readObject();
                if (obj instanceof List) {
                    @SuppressWarnings("unchecked")
                    List<T> carregado = (List<T>) obj;
                    this.lista = carregado;
                }

            } catch (Exception e) {
                this.lista = new ArrayList<>();
            }
        }
    }

    private synchronized void persistir() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(arquivoPersistencia)))) {

            oos.writeObject(lista);
            oos.flush();

        } catch (IOException e) {}
    }

    @Override
    public void salvar(T objeto) {
        lista.add(objeto);
        persistir();
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(lista);
    }

    @Override
    public boolean remover(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (getId(lista.get(i)) == id) {
                lista.remove(i);
                persistir();
                return true;
            }
        }
        return false;
    }

    @Override
    public T buscarPorId(int id) {
        for (T obj : lista) {
            if (getId(obj) == id) return obj;
        }
        return null;
    }

    @Override
    public void atualizar(T objeto) {
        int id = getId(objeto);
        for (int i = 0; i < lista.size(); i++) {
            if (getId(lista.get(i)) == id) {
                lista.set(i, objeto);
                persistir();
                return;
            }
        }
    }
public int gerarProximoId() {
    int maior = 0;

    for (T obj : lista) {
        int id = getId(obj);
        if (id > maior) maior = id;
    }

    return maior + 1;
}

    public abstract int getId(T objeto);
}
