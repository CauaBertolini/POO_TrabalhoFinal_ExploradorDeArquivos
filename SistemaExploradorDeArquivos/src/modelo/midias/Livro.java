package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.generos.GeneroLiterario;

public class Livro extends Midia {

    private String autor;

    public Livro(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String autor, GeneroLiterario generoLiterario) {
        super(local, titulo, tamanho, duracao, eTipoArquivo);
        setAutor(autor);
    }

    public void setAutor(String autor) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(autor)) {
            this.autor = autor;
        }
    }

    public String getAutor() {
        return autor;
    }

}
