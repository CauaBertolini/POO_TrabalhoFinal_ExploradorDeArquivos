package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;
import modelo.Genero;

/**
 * A classe Musica estende a classe abstrata Midia, herdando seus atributos
 * e métodos básicos, e adiciona característica específica de um livro, como
 * autor. Esta classe é responsável por armazenar e validar informações referentes a um
 * livro.
 */
public class Musica extends modelo.midias.Midia {

    private String artista;

    /**
     * Cria um objeto Musica inicializando seus atributos específicos e herdados
     *
     * @param caminho       - Caminho onde o arquivo do livro está armazenado
     * @param nome          - Nome da música
     * @param tamanho       - Tamanho do arquivo
     * @param duracao       - Duração da música em minutos
     * @param eTipoArquivo  - Tipo do arquivo (MP3)
     * @param genero        - Gênero da musica do tipo musical
     * @param artista       - Nome do artista
     */
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
