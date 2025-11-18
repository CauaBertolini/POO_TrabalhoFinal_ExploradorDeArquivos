package modelo;

import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;

import java.util.ArrayList;
import java.util.List;

public class Listas {

    private List<Genero> listaGeneros;
    private List<Idioma> listaIdiomas;

    public Listas() {
        listaGeneros = new ArrayList<>();
        listaIdiomas = new ArrayList<>();
        gerarGeneros();
        gerarIdiomas();
    }

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
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

    public void gerarIdiomas() {
        listaIdiomas.add(new Idioma("Português (BR)"));
        listaIdiomas.add(new Idioma("Português (PT)"));
        listaIdiomas.add(new Idioma("Inglês (EUA)"));
        listaIdiomas.add(new Idioma("Espanhol (ES)"));
        listaIdiomas.add(new Idioma("Francês (FR)"));
        listaIdiomas.add(new Idioma("Alemão (DE)"));
        listaIdiomas.add(new Idioma("Italiano (IT)"));
        listaIdiomas.add(new Idioma("Japonês (JP)"));
        listaIdiomas.add(new Idioma("Chinês (Mandarim)"));
        listaIdiomas.add(new Idioma("Coreano (KR)"));
        listaIdiomas.add(new Idioma("Russo (RU)"));
        listaIdiomas.add(new Idioma("Árabe (SA)"));
        listaIdiomas.add(new Idioma("Hindi (IN)"));
        listaIdiomas.add(new Idioma("Sueco (SE)"));
        listaIdiomas.add(new Idioma("Norueguês (NO)"));
        listaIdiomas.add(new Idioma("Grego (GR)"));
        listaIdiomas.add(new Idioma("Hebraico (IL)"));
        listaIdiomas.add(new Idioma("Turco (TR)"));
        listaIdiomas.add(new Idioma("Holandês (NL)"));
    }



}
