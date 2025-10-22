package exemplos.pilares.Herança;

/*public*/ interface PessoaInterface {

        //public static final String nome; //Não tem sentido pois seria static e compartilhado pra todos os objetos
        /*public/private static ....(){        
        }; para um método ter corpo ele precisará ser public/private  e static*/

        //lembrando que aqui será public e abstract
        void setNome(String nome);     
        String getNome();    
}

/*public*/abstract class PessoaSuperClass implements PessoaInterface{

    private String nome;

    @Override
    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String getNome(){
        return nome;      
    }

    @Override
    public String toString(){
        return getNome() + " \\" + this.getClass();
    }


}


/*public*/class PessoaFisica extends PessoaSuperClass{

    public void algoPessoaFisica(){
        System.out.println("Executado algo de " + this.getClass());
    }

}

/*public*/class PessoaJuridica extends PessoaSuperClass{

    public void algoPessoaJuridica(){
        System.out.println("Executado algo de " + this.getClass());
    }

}


public class PessoaTester {


    public static void main(String[] args) {

        PessoaInterface p1, p2;

        p1 = new PessoaFisica();
        p1.setNome("p1");
        p2 = new PessoaJuridica();
        p2.setNome("p2");

        System.out.println(p1); //toString de SuperClass
        System.out.println(p2); //toString de SuperClass

        ((PessoaFisica) p1).algoPessoaFisica();
        ((PessoaJuridica) p2).algoPessoaJuridica();

    }


}
