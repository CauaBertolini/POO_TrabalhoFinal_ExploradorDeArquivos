package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public class Musica extends Midia {

    private String artista;

    public Musica(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String artista) {
        super(local, titulo, tamanho, duracao, eTipoArquivo);
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
