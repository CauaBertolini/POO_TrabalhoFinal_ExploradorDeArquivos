package modelo.Midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.Genero;


public class Livro extends modelo.Midias.Midia {

    private String autor;
    private Genero genero;

    public Livro(String caminho, String nome, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, String autor) {
        super(nome, caminho, eTipoArquivo, duracao, tamanho);
        setAutor(autor);
        setGenero(genero);
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.LITERARIO) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero inválido");
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(autor)) {
            this.autor = autor;
        }
    }


}