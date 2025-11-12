package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.Genero;

public class Musica extends Midia {

    private String artista;

    public Musica(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, String artista) {
        super(titulo, local, eTipoArquivo, duracao, tamanho);
        setArtista(artista);
        setGenero(genero);
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.MUSICAL) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero invalido");
        }
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(artista)) {
            this.artista = artista;
        }
    }



}
