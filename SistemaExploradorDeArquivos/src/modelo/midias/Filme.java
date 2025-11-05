package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.generos.GeneroCinema;
import modelo.generos.GeneroMusical;

import java.util.ArrayList;
import java.util.List;

public class Filme extends Midia {

    private Idioma idioma;
    private List<GeneroCinema> generosCinema;

    public Filme(String local, String titulo, float tamanho,  double duracao, ETipoArquivo tipoArquivo, Idioma idioma) {
        super(local, titulo, tamanho, duracao, tipoArquivo);
        this.generosCinema = new ArrayList<>();
        setIdioma(idioma);
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

    public void adicionarGenero(GeneroCinema generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        generosCinema.add(generoCinema);
    }

    public void removerGenero(GeneroCinema generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        generosCinema.remove(generoCinema);
    }

    public String listarGenerosCinemas() {
        String generos = "";
        for (GeneroCinema genero : generosCinema) {
            generos += genero.getNome() + ", ";
        }
        return generos;
    }
}
