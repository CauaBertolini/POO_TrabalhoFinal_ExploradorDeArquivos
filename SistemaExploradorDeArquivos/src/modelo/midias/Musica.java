package modelo.midias;

public class Musica extends Midia {

    private String artista;

    public Musica(String local, String titulo, float tamanho,  double duracao) {
        super(local, titulo, tamanho, duracao);
    }

    public String getArtista() {
        return artista;
    }

}
