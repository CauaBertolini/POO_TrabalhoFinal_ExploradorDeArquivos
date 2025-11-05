package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.generos.GeneroMusical;

import java.util.ArrayList;
import java.util.List;

public class Musica extends Midia {

    private String artista;
    private List<GeneroMusical> generosMusical;

    public Musica(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String artista, GeneroMusical generoMusical) {
        super(local, titulo, tamanho, duracao, eTipoArquivo);
        this.generosMusical = new ArrayList<>();
        setArtista(artista);
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (!Utilitario.campoEstaVazioOuNulo(artista)) {
            this.artista = artista;
        }
    }

    public void adicionarGenero(GeneroMusical generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        generosMusical.add(generoCinema);
    }

    public void removerGenero(GeneroMusical generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        generosMusical.remove(generoCinema);
    }

    public String listarGenerosMusical() {
        String generos = "";
        for (GeneroMusical genero : generosMusical) {
            generos += genero.getNome() + ", ";
        }
        return generos;
    }

}
