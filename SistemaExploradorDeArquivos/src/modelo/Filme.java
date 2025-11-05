package modelo;

public class Filme extends Midia {
    private Idioma idioma;

    public Filme(String local, String titulo, float tamanho,  double duracao) {
        super(local, titulo, tamanho, duracao);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }
}
