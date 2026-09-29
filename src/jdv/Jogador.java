package jdv;

public class Jogador {

    private String nome;
    private char simbolo;

    public Jogador(String nome, char simbolo) {
        this.nome = nome;
        this.simbolo = simbolo;
    }

    public String getNome() {
        return nome;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
