package exemplos.PersistenciaOO.SerializacaoOO;

/* 0. Passo BASE:
 * Se faz necessário definir um contrato para que qualquer entidade que queria ser 
 * serializada em txt precise implmentar salvar em txt e recuperar de txt.]
 * A ideia se fez necessária devido a evitar o uso de toString para este propósito ficando travado
 * o método pra isso , sendo que poderia ser modificado se por outra necessidade de uso
 * e formato de entrega
 */

public interface SerializableTxt {
    String toSerializableTxt();
    SerializableTxt fromSerializableTxt(String linha);
}
