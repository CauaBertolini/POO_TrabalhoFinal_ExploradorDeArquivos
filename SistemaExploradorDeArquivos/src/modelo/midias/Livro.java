package modelo.midias;

import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

import java.util.ArrayList;

public class Livro extends Midia {

    private String autor;

    public Livro(String local, String titulo, float tamanho,  double duracao) {
        super(local, titulo, tamanho, duracao);
    }

    public void setAutor(String autor) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(autor)) {
            this.autor = autor;
        }
    }

    public String getAutor() {
        return autor;
    }
}
