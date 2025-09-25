package exemplos.objectCalisthenics;

/*
 * 4. Use apenas uma instrução de ponto por linha
 *    Respeite o princípio da Lei de Deméter.
 *    Em vez de cliente.getEndereco().getCidade(), 
 *    encapsule a lógica em métodos apropriados (cliente.getCidade()).
 *  
 *    A Lei de Deméter (Law of Demeter – LoD) é um princípio de design orientado 
 *    a objetos, também chamada de Princípio do Mínimo Conhecimento.
 * 
 *    A Lei de Deméter promove: 
 *    - Baixo acoplamento
 *    - Alto encapsulamento
 *    - Código mais robusto a mudanças
 * 
 *    Um objeto deve conhecer apenas seus "amigos" diretos, e não os amigos dos amigos.
 *    Ou seja: não "fuçar" dentro de outros objetos para acessar internamente o que eles sabem.
 * 
 */

//Vamos começar quebrando a lei

class Cliente {
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }
}

class Endereco {
    private Cidade cidade;

    public Cidade getCidade() {
        return cidade;
    }
}

class Cidade {
    private String nome;

    public String getNome() {
        return nome;
    }
}

/* Uso

Cliente cliente = new Cliente();
String nomeCidade = cliente.getEndereco().getCidade().getNome();

O código depende de uma cadeia de chamadas (getEndereco().getCidade().getNome()).
Se qualquer classe mudar, várias partes do sistema quebram.
O objeto Cliente expõe demais sua estrutura interna.

*/

//Resolvendo o problema : Encapsulamos o acesso dentro da própria classe:

class ClienteNovo {
    private Endereco endereco;

    //Retira o que não importa, cria o que importa

    public String getNomeCidade() {
        return endereco.getCidade().getNome();
    }
}

/* 
   Uso:

   Cliente cliente = new Cliente();
   String nomeCidade = cliente.getNomeCidade();

   O código cliente não sabe da estrutura interna.
   Se Endereco ou Cidade mudarem, o impacto é mínimo.
   Melhora o encapsulamento e reduz o acoplamento.

   Regrinha prática (quem posso chamar?)

   Um método de um objeto A só deve chamar métodos de: Ele mesmo.
   Seus atributos diretos.
   Objetos criados dentro dele.
   Parâmetros recebidos no método.

   Resumindo - Lei de Deméter promove:

   Baixo acoplamento
   Alto encapsulamento
   Código mais robusto a mudanças

 */

public class LOD {

}
