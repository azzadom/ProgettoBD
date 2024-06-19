package model;

public class Macchinario {

    private String nome;
    private Integer quantità;

    public Macchinario(String nome, Integer quantità) {
        this.nome = nome;
        this.quantità = quantità;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantità() {
        return quantità;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Quantità: " + quantità;
    }

}
