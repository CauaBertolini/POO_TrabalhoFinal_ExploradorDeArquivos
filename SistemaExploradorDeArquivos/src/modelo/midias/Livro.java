package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.generos.GeneroLiterario;

public class Livro extends Midia {

    private String autor;
    private GeneroLiterario generoLiterario;

    public Livro(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String autor, GeneroLiterario generoLiterario) {
        super(local, titulo, tamanho, duracao, eTipoArquivo);
        setAutor(autor);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (!Utilitario.campoEstaVazioOuNulo(autor)) {
            this.autor = autor;
        }
    }

    public GeneroLiterario getGeneroLiterario() {
        return generoLiterario;
    }

    public void setGeneroLiterario(GeneroLiterario generoLiterario) {
        if (generoLiterario == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.generoLiterario = generoLiterario;
    }

}
