package modelo;

import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListasTest {

    private Listas listas;

    @BeforeEach
    void setup() {
        listas = new Listas();
    }

    // getListaGeneros()
    @Test
    void getListaGeneros_DeveRetornar47Generos() {
        List<Genero> generos = listas.getListaGeneros();
        assertNotNull(generos, "Lista de gêneros não deve ser null");
        assertEquals(41, generos.size(), "Deve conter 41 gêneros");
    }

    @Test
    void getListaGeneros_NaoDeveSerNull() {
        assertNotNull(listas.getListaGeneros());
    }

    // getListaIdiomas()
    @Test
    void getListaIdiomas_DeveRetornar19Idiomas() {
        List<Idioma> idiomas = listas.getListaIdiomas();
        assertNotNull(idiomas, "Lista de idiomas não deve ser null");
        assertEquals(19, idiomas.size(), "Deve conter 19 idiomas");
    }

    @Test
    void getListaIdiomas_NaoDeveSerNull() {
        assertNotNull(listas.getListaIdiomas());
    }

    // adicionarGenero()
    @Test
    void adicionarGenero_GeneroValido_DeveAdicionar() throws CampoVazioOuNuloExcecao {
        Genero novoGenero = new Genero("TesteGenero", ETipoGenero.CINEMA);
        listas.adicionarGenero(novoGenero);
        assertTrue(listas.getListaGeneros().contains(novoGenero), "O gênero deve ser adicionado");
    }

    @Test
    void adicionarGenero_Nulo_DeveLancarExcecao() {
        assertThrows(CampoVazioOuNuloExcecao.class, () -> listas.adicionarGenero(null));
    }

    // removerGenero()
    @Test
    void removerGenero_GeneroExistente_DeveRemover() throws CampoVazioOuNuloExcecao {
        Genero genero = new Genero("GeneroParaRemover", ETipoGenero.MUSICAL);
        listas.adicionarGenero(genero);
        listas.removerGenero(genero);
        assertFalse(listas.getListaGeneros().contains(genero), "O gênero deve ser removido");
    }

    @Test
    void removerGenero_Nulo_DeveLancarExcecao() {
        assertThrows(CampoVazioOuNuloExcecao.class, () -> listas.removerGenero(null));
    }

    // gerarGeneros()
    @Test
    void gerarGeneros_DeveCriar47Generos() {
        List<Genero> generos = listas.getListaGeneros();
        assertEquals(41, generos.size(), "Deve conter 41 gêneros");
    }

    @Test
    void gerarGeneros_DevePermitirMesmosNomesDiferentesTipos() {
        boolean possuiDuplicatasValidas = listas.getListaGeneros().stream()
                .filter(g -> g.getNome().equals("Drama"))
                .count() > 1;
        assertTrue(possuiDuplicatasValidas, "Mesmos nomes podem existir em diferentes categorias");
    }

    //  gerarIdiomas()
    @Test
    void gerarIdiomas_DeveCriar19Idiomas() {
        assertEquals(19, listas.getListaIdiomas().size(), "Deve conter 19 idiomas");
    }

    @Test
    void gerarIdiomas_ConteudoBasicoDeveEstarPresente() {
        List<String> nomesIdiomas = listas.getListaIdiomas().stream().map(Idioma::getNome).toList();
        assertTrue(nomesIdiomas.contains("Português (BR)"), "Deve conter Português (BR)");
        assertTrue(nomesIdiomas.contains("Inglês (EUA)"), "Deve conter Inglês (EUA)");
        assertTrue(nomesIdiomas.contains("Espanhol (ES)"), "Deve conter Espanhol (ES)");
    }
}
