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

        Genero acao = new Genero("Ação", ETipoGenero.CINEMA);
        Genero aventura = new Genero("Aventura", ETipoGenero.CINEMA);
        Genero comedia = new Genero("Comedia", ETipoGenero.CINEMA);
        Genero romance = new Genero("Romance", ETipoGenero.CINEMA);
        Genero terror = new Genero("Terror", ETipoGenero.CINEMA);
        Genero drama = new Genero("Drama", ETipoGenero.CINEMA);
        Genero ficcaoCientifica = new Genero("Ficção Científica", ETipoGenero.CINEMA);
        Genero suspense = new Genero("Suspense", ETipoGenero.CINEMA);
        Genero documentario = new Genero("Documentário", ETipoGenero.CINEMA);
        Genero animacao = new Genero("Animação", ETipoGenero.CINEMA);
        Genero guerra = new Genero("Guerra", ETipoGenero.CINEMA);
        Genero policial = new Genero("Policial", ETipoGenero.CINEMA);
        Genero biografia = new Genero("Biografia", ETipoGenero.CINEMA);
        Genero western = new Genero("Faroeste", ETipoGenero.CINEMA);
        Genero historico = new Genero("Histórico", ETipoGenero.CINEMA);

        Genero jazz = new Genero("Jazz", ETipoGenero.MUSICAL);
        Genero rock = new Genero("Rock", ETipoGenero.MUSICAL);
        Genero pop = new Genero("Pop", ETipoGenero.MUSICAL);
        Genero eletronica = new Genero("Eletrônica", ETipoGenero.MUSICAL);
        Genero sertanejo = new Genero("Sertanejo", ETipoGenero.MUSICAL);
        Genero blues = new Genero("Blues", ETipoGenero.MUSICAL);
        Genero reggae = new Genero("Reggae", ETipoGenero.MUSICAL);
        Genero soul = new Genero("Soul", ETipoGenero.MUSICAL);
        Genero funk = new Genero("Funk", ETipoGenero.MUSICAL);
        Genero mpb = new Genero("MPB", ETipoGenero.MUSICAL);
        Genero samba = new Genero("Samba", ETipoGenero.MUSICAL);
        Genero classica = new Genero("Música Clássica", ETipoGenero.MUSICAL);
        Genero rap = new Genero("Rap", ETipoGenero.MUSICAL);
        Genero trap = new Genero("Trap", ETipoGenero.MUSICAL);
        Genero bossaNova = new Genero("Bossa Nova", ETipoGenero.MUSICAL);

        Genero fantasia = new Genero("Fantasia", ETipoGenero.LITERARIO);
        Genero ficcao = new Genero("Ficção", ETipoGenero.LITERARIO);
        Genero dramaLit = new Genero("Drama", ETipoGenero.LITERARIO);
        Genero poesia = new Genero("Poesia", ETipoGenero.LITERARIO);
        Genero misterio = new Genero("Mistério", ETipoGenero.LITERARIO);
        Genero terrorLit = new Genero("Terror", ETipoGenero.LITERARIO);
        Genero aventuraLit = new Genero("Aventura", ETipoGenero.LITERARIO);
        Genero romanceLit = new Genero("Romance", ETipoGenero.LITERARIO);
        Genero distopia = new Genero("Distopia", ETipoGenero.LITERARIO);
        Genero mitologia = new Genero("Mitologia", ETipoGenero.LITERARIO);
        Genero cronica = new Genero("Crônica", ETipoGenero.LITERARIO);

    }



}
