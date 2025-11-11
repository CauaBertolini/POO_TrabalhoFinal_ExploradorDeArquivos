package modelo;

import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;

import java.util.ArrayList;
import java.util.List;

public class ListaGenero {

    private List<Genero> listaGeneros;

    public ListaGenero () {
        listaGeneros = new ArrayList<>();
        gerarGeneros();
    }

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void adicionarGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        listaGeneros.add(genero);
    }

    public void removerGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        listaGeneros.remove(genero);
    }

    public void gerarGeneros() {

        listaGeneros.add(new Genero("Ação", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Aventura", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Comedia", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Romance", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Terror", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Drama", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Ficção Científica", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Suspense", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Documentário", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Animação", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Guerra", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Policial", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Biografia", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Faroeste", ETipoGenero.CINEMA));
        listaGeneros.add(new Genero("Histórico", ETipoGenero.CINEMA));

        listaGeneros.add(new Genero("Jazz", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Rock", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Pop", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Eletrônica", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Sertanejo", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Blues", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Reggae", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Soul", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Funk", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("MPB", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Samba", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Música Clássica", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Rap", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Trap", ETipoGenero.MUSICAL));
        listaGeneros.add(new Genero("Bossa Nova", ETipoGenero.MUSICAL));

        listaGeneros.add(new Genero("Fantasia", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Ficção", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Drama", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Poesia", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Mistério", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Terror", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Aventura", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Romance", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Distopia", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Mitologia", ETipoGenero.LITERARIO));
        listaGeneros.add(new Genero("Crônica", ETipoGenero.LITERARIO));


    }



}
