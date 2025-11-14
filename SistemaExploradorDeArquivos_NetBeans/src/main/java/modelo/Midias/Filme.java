package modelo.Midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.Genero;

public class Filme extends modelo.Midias.Midia {

    private Idioma idioma;

    public Filme(String caminho, String nome, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, Idioma idioma) {
        super(caminho, nome, eTipoArquivo, duracao, tamanho);
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
            throw new IllegalArgumentException("ETipoGenero inválido");
        }
    }

    public void setIdioma(Idioma idioma) throws CampoVazioOuNuloExcecao {
        if (idioma == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.idioma = idioma;
    }

}
