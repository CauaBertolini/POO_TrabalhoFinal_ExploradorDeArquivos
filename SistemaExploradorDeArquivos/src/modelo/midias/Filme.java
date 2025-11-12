package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.Genero;

public class Filme extends Midia {

    private Idioma idioma;

    public Filme(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, Idioma idioma) {
        super(titulo, local, eTipoArquivo, duracao, tamanho);
        setIdioma(idioma);
        setGenero(genero);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.CINEMA) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero invalido");
        }
    }

    public void setIdioma(Idioma idioma) throws CampoVazioOuNuloExcecao {
        if (idioma == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.idioma = idioma;
    }

}
