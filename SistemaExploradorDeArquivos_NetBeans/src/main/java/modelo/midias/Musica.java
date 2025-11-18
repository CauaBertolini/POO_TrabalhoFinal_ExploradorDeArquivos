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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome da Musica: ").append(getNome()).append("\n");
        sb.append("Tamanho da Musica: ").append(getTamanho()).append("\n");
        sb.append("Duração da Musica: ").append(getDuracao()).append("\n");
        sb.append("Caminho da Musica: ").append(getCaminho()).append("\n");
        sb.append("Gênero da Musica: ").append(getGenero()).append("\n");
        sb.append("Tipo do Arquivo Musica: ").append(getTipoArquivo()).append("\n");
        return sb.toString();
    }



}
