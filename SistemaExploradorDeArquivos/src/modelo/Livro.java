package modelo;

import java.util.ArrayList;

public class Livro extends Midia {

    private ArrayList<Pessoa> autores;

    public Livro(String local, String titulo, float tamanho,  double duracao) {
        super(local, titulo, tamanho, duracao);
    }


    public void adicionarAutor() {

    }

    public void removerAutor() {

    }

    public ArrayList<Pessoa> listarAutores() {
        return autores;
    }
}
