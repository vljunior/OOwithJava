package exemplos.PersistenciaOO.NItrite;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.ObjectRepository;


class Entidade {
    @Id
    private int codigo;
    private String nome;

    // Construtor vazio exigido pelo Nitrite
    public Entidade() {

    }

    public Entidade(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

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

        // 3. Inserir objetos
        Entidade e1 = new Entidade(1, "Primeiro");
        Entidade e2 = new Entidade(2, "Segundo");
        repositorio.insert(e1, e2);

        System.out.println("Após inserção");
        for (Entidade entidade : repositorio.find()) {
            System.out.println(entidade);
        }       

        // 4. Atualizar objeto (mudando nome do código 2)
        Entidade atualizar = repositorio.getById(2);
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

/* pom.xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>com.exemplo</groupId>
    <artifactId>exemplo-nitrite</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- Nitrite Database -->
        <dependency>
            <groupId>org.dizitart</groupId>
            <artifactId>nitrite</artifactId>
            <version>3.4.4</version>
        </dependency>

        <!-- SLF4J simples (para mostrar logs no console) -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.9</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

*/
