package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.Genero;

/**
 * A classe Filme estende a classe abstrata Midia, herdando seus atributos
 * e métodos básicos, e acrescenta informações específicas do formato
 * musical, como artista e validação de gênero adequado
 */
public class Filme extends Midia {

    private Idioma idioma;

    /**
     * Cria um objeto Filme inicializando todos os seus atributos, tanto os herdados
     * da classe quanto os específicos da classe Filme
     *
     * @param caminho       Caminho onde o arquivo do livro está armazenado
     * @param nome          Nome do filme
     * @param tamanho       Tamanho do arquivo
     * @param duracao       Duração do filme
     * @param eTipoArquivo  Tipo do arquivo do filme (MP4, MKV)
     * @param genero        Gênero do filme do tipo Cinema
     * @param idioma        Idioma principal do filme
     *
     * @throws CampoVazioOuNuloExcecao Se algum dos campos obrigatórios herdados de Midia for nulo ou vazio
     * @throws IllegalArgumentException Se o gênero informado não for adequado para filme
     */
    public Filme(String caminho, String nome, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, Idioma idioma) {
        super(caminho, nome, eTipoArquivo, duracao, tamanho);
        setIdioma(idioma);
        setGenero(genero);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.CINEMA) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero inválido");
        }
    }

    public void setIdioma(Idioma idioma) throws CampoVazioOuNuloExcecao {
        if (idioma == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.idioma = idioma;
    }

}
