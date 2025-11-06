package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.Genero;

public class Filme extends Midia {

    private Idioma idioma;

    public Filme(String local, String titulo, float tamanho, double duracao, ETipoArquivo tipoArquivo, Genero genero, Idioma idioma) {
        super(local, titulo, tamanho, duracao, tipoArquivo);
        setIdioma(idioma);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) throws CampoVazioOuNuloExcecao {
        if (idioma == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.idioma = idioma;
    }

}
