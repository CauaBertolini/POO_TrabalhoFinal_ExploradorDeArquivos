package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;
import modelo.Genero;

public class Musica extends modelo.midias.Midia {

    private String artista;

    public Musica(String caminho, String nome, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, String artista) {
        super(caminho, nome, eTipoArquivo, duracao, tamanho);
        setArtista(artista);
        setGenero(genero);
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.MUSICAL) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero inválido");
        }
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) throws CampoVazioOuNuloExcecao {
        if (!ExcecaoUtil.campoEstaVazioOuNulo(artista)) {
            this.artista = artista;
        }
    }



}
