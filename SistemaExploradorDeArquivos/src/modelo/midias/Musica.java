package modelo.midias;

import modelo.Pessoa;

import java.util.ArrayList;

public class Musica extends Midia {

    private ArrayList<Pessoa> artistas;

    public Musica(String local, String titulo, float tamanho,  double duracao) {
        super(local, titulo, tamanho, duracao);
    }

    public void adicionarArtista() {

    }

    public void removerArtista() {

    }

    public ArrayList<Pessoa> listarArtistas() {
        return artistas;
    }

}
