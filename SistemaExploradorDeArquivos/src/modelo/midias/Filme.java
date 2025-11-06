package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.generos.GeneroCinema;

public class Filme extends Midia {

    private Idioma idioma;
    private GeneroCinema generoCinema;

    public Filme(String local, String titulo, float tamanho,  double duracao, ETipoArquivo tipoArquivo, Idioma idioma) {
        super(local, titulo, tamanho, duracao, tipoArquivo);
        setIdioma(idioma);
        setGeneroCinema(generoCinema);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        if (idioma == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.idioma = idioma;
    }

    public GeneroCinema getGeneroCinema() {
        return generoCinema;
    }

    public  void setGeneroCinema(GeneroCinema generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.generoCinema = generoCinema;
    }
}
