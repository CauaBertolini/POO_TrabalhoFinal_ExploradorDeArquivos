package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;


public class Livro extends Midia {

    private String autor;

    public Livro(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String autor) {
        super(local, titulo, eTipoArquivo, duracao, tamanho);
        setAutor(autor);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(autor)) {
            this.autor = autor;
        }
    }


}
