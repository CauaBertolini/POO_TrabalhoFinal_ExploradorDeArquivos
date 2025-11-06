package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;

public class Filme extends Midia {

    private Idioma idioma;

    public Filme(String local, String titulo, float tamanho,  double duracao, ETipoArquivo tipoArquivo, Idioma idioma) {
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
