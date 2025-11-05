package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.generos.GeneroLiterario;
import modelo.generos.GeneroMusical;

import java.util.ArrayList;
import java.util.List;

public class Livro extends Midia {

    private String autor;
    private List<GeneroLiterario> generosLiterario;

    public Livro(String local, String titulo, float tamanho, double duracao, ETipoArquivo eTipoArquivo, String autor, GeneroLiterario generoLiterario) {
        super(local, titulo, tamanho, duracao, eTipoArquivo);
        this.generosLiterario = new ArrayList<>();
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

    public void adicionarGenero(GeneroLiterario generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        generosLiterario.add(generoCinema);
    }

    public void removerGenero(GeneroLiterario generoCinema) {
        if (generoCinema == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        generosLiterario.remove(generoCinema);
    }

    public String listarGenerosLiterarios() {
        String generos = "";
        for (GeneroLiterario genero : generosLiterario) {
            generos += genero.getNome() + ", ";
        }
        return generos;
    }

}
