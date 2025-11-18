package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Idioma;
import modelo.Genero;

public class Filme extends modelo.midias.Midia {

    private Idioma idioma;

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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do Filme: ").append(getNome()).append("\n");
        sb.append("Idioma: ").append(getIdioma()).append("\n");
        sb.append("Tamanho do Filme: ").append(getTamanho()).append("\n");
        sb.append("Duração do Filme: ").append(getDuracao()).append("\n");
        sb.append("Caminho do Filme: ").append(getCaminho()).append("\n");
        sb.append("Gênero do Filme: ").append(getGenero()).append("\n");
        sb.append("Tipo do Arquivo Filme: ").append(getTipoArquivo()).append("\n");
        return sb.toString();
    }


}
