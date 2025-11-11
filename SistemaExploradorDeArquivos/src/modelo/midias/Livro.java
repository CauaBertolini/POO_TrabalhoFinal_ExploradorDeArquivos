package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.Genero;


public class Livro extends Midia {

    private String autor;
    private Genero genero;

    public Livro(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, String autor) {
        super(titulo, local, eTipoArquivo, duracao, tamanho, genero);
        setAutor(autor);
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.LITERARIO) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero invalido");
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
