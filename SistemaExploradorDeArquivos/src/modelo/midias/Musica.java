package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.generos.GeneroMusical;

public class Musica extends Midia {

    private String artista;
    private GeneroMusical generoMusical;

    public Musica(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String artista, GeneroMusical generoMusical) {
        super(local, titulo, tamanho, duracao, eTipoArquivo);
        setArtista(artista);
        setGeneroMusical(generoMusical);
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (!Utilitario.campoEstaVazioOuNulo(artista)) {
            this.artista = artista;
        }
    }

    public GeneroMusical getGeneroMusical(){
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        if (generoMusical == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.generoMusical = generoMusical;
    }

}
