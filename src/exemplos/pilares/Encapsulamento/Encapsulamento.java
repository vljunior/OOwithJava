package exemplos.pilares.Encapsulamento;

import javax.management.InvalidAttributeValueException;

public class Encapsulamento {

    private int atributo; //Visibilidade
    //Se muitos atributos, pensar em encapsular em nova classe e propósito
    //Atributos em validação não servem como tipos primitivos: Nome, cpf, idade

    public Encapsulamento(){ //reescrevendo construtor padrão
        //algo?
    }

    public Encapsulamento (int atributo){
        this(); //chamando construtor padrão: cascata.
        setAtributo(atributo); //sempre usar o set pra alterar o atributo, em qualquer lugar
    }

    //Se mais construtores, avaliar padrão Builder.

    public int getAtributo() {
        return atributo;
    }

    //Cuidado ao ser público
    public void setAtributo(int atributo) //throws InvalidAttributeValueException
        {
        //validação? Exceção? 
        this.atributo = atributo;
        //Validar por exceção
        //trows new InvalidAttributeValueException("Erro");
    }

    //Comportamentos, pra não ser classe anêmica

    public void procedimentoQualquer(){
        //Este método vai fazer algo, sem parâmetros
    }
    
    public void procedimentoQualquer(int comParametro){ //sobrecarga
        //Este método vai fazer algo, com parâmetro

    }
  
    //@override
    public String toString(){
        //super.toString(); //Executa o pai?
        return ("Valor interno: " + atributo);
    }

    private int metodoQualquerInterno(int temParametro){ //privado da classe

        //Caso particular para variáveis locais onde o tipo é definido pelo valor, tipos defaults: usar var
        var inteiro = 0;

        int algo = inteiro;
        algo = algo + temParametro;
        return algo;
    }

    //Construir comportamento que sejam chamadas Tell e Não Ask!
    

}
