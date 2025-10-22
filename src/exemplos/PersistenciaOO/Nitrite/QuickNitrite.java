package exemplos.PersistenciaOO.Nitrite;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.ObjectRepository;


class Entidade {
    @Id
    //private NitriteId id;   //Chave de banco OU
    private int codigo;     //Chave de negócio
    private String nome;

    // Construtor vazio exigido pelo Nitrite
    public Entidade() {
    }
    public Entidade(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    /*
    public NitriteId getId() {
        return id;
    }
    public void setId(NitriteId id) {
        this.id = id;
    }
    */
    public int getCodigo() { 
        return codigo; 
    }    
    public void setCodigo(int codigo) { 
        this.codigo = codigo; 
    }
    public String getNome() { 
        return nome; 
    }    
    public void setNome(String nome) { 
        this.nome = nome; 
    }
    @Override
    public String toString() {
        return "Entidade {codigo=" + codigo + ", nome='" + nome + "'}";
    }
}

public class QuickNitrite {

     public static void main(String[] args) {

        // 1. Abrir ou criar banco Nitrite
        Nitrite db = Nitrite.builder()
                .filePath("bancoExemplo.db")
                .openOrCreate("user", "password");

        // 2. Criar repositório para a classe Entidade
        ObjectRepository<Entidade> repositorio = db.getRepository(Entidade.class);

        // 3. Inserir objetos.
        // Se usar Id, esquece o Id, é controle de Nitrite
        Entidade e1 = new Entidade(1, "Primeiro");
        Entidade e2 = new Entidade(2, "Segundo");
        repositorio.insert(e1, e2);

        System.out.println("Após inserção");
        for (Entidade entidade : repositorio.find()) {
            System.out.println(entidade);
        }       

        // 4. Atualizar objeto (mudando nome do código 2)
        Entidade atualizar = repositorio.getById(2); //Chave de negócio
        if (atualizar != null) {
            atualizar.setNome("Segundo Alterado");
            repositorio.update(atualizar);
        }

        System.out.println("\nApós atualização");
        for (Entidade entidade : repositorio.find()) {
            System.out.println(entidade);
        }

        // 5. Remover objeto (com código 1)
        Entidade remover = repositorio.getById(1);
        if (remover != null) {
            repositorio.remove(remover);
        }

        System.out.println("\nApós remoção");
        for (Entidade entidade : repositorio.find()) {
            System.out.println(entidade);
        }

        // 6. Fechar banco
        db.close();
    }
}