package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.Genero;

public class Musica extends Midia {

    private String artista;

    public Musica(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, String artista) {
        super(titulo, local, eTipoArquivo, duracao, tamanho, genero);
        setArtista(artista);
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
