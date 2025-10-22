package exemplos.designpatterns.criacionais;

public class ClasseComBuilder {
    // Atributos
    private String atributo1;
    private int atributo2;
    
    // Construtor privado
    private ClasseComBuilder(Builder builder) {
        this.atributo1 = builder.atributo1;
        this.atributo2 = builder.atributo2;        
    }

            // Classe interna estática Builder
            public static class Builder {
                private String atributo1;
                private int atributo2;
                

                public Builder setAtributo1(String atributo1) {
                    this.atributo1 = atributo1;
                    return this;
                }

                public Builder setAtributo2(int atributo2) {
                    this.atributo2 = atributo2;
                    return this;
                }        

                public ClasseComBuilder build() {
                    return new ClasseComBuilder(this);
                }
            }

     /*Na aplicação
        ClasseComBuilder.Builder builder = new Pessoa.Builder();
        
        builder.setAtributo1("Lorenzon");
        builder.setAtributo2(34);
        
        ClasseComBuilder instancia = builder.build();

        //ou com a técnica de Fluent Interface + Method Chaining

     * Classe objeto = new Classe.Builder() //retorna o objeto
                .setAtributo1("String") //devido ao retorno this, retorna o objeto
                .setAtributo2()
                //assim por diante                
                .build();    
     * 
     */        
}