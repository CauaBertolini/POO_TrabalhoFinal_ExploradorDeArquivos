package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;
import modelo.Genero;

/**
 * A classe Livro estende a classe abstrata Midia, herdando seus atributos
 * e métodos básicos, e adiciona característica específica de um livro, como
 * autor. Esta classe é responsável por armazenar e validar informações referentes a um
 * livro.
 */
public class Livro extends modelo.midias.Midia {

    private String autor;
    private Genero genero;

    /**
     * Constrói um objeto do tipo Livro inicializando seus atributos específicos,
     * além dos atributos herdados da classe
     *
     * @param caminho       - Caminho onde o arquivo do livro está armazenado
     * @param nome          - Título do livro
     * @param tamanho       - Tamanho do arquivo do livro
     * @param duracao       - Número de páginas do livro
     * @param eTipoArquivo  - Tipo de arquivo do livro (PDF, EPUB)
     * @param genero        - Gênero literário do livro
     * @param autor         - Nome do autor da obra
     */
    public Livro(String caminho, String nome, float tamanho, double duracao, ETipoArquivo eTipoArquivo, Genero genero, String autor) {
        super(caminho, nome, eTipoArquivo, duracao, tamanho);
        setAutor(autor);
        setGenero(genero);
    }

    @Override
    public void setGenero(Genero genero) {
        if (genero.getETipoGenero() == ETipoGenero.LITERARIO) {
            super.setGenero(genero);
        } else {
            throw new IllegalArgumentException("ETipoGenero inválido");
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws CampoVazioOuNuloExcecao {
        if (!ExcecaoUtil.campoEstaVazioOuNulo(autor)) {
            this.autor = autor;
        }
    }


}