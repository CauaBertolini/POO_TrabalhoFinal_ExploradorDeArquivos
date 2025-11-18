package modelo.midias;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;
import modelo.Genero;


public class Livro extends modelo.midias.Midia {

    private String autor;
    private Genero genero;

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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do Livro: ").append(getNome()).append("\n");
        sb.append("Tamanho do Livro: ").append(getTamanho()).append("\n");
        sb.append("Duração do Livro: ").append(getDuracao()).append("\n");
        sb.append("Caminho do Livro: ").append(getCaminho()).append("\n");
        sb.append("Gênero do Livro: ").append(getGenero()).append("\n");
        sb.append("Tipo do Arquivo Livro: ").append(getTipoArquivo()).append("\n");
        return sb.toString();
    }


}