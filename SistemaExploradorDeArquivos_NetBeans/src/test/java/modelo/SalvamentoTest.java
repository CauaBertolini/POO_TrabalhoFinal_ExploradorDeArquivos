package modelo;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import modelo.midias.Filme;
import modelo.midias.Midia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalvamentoTest {

    private Salvamento salvamento;
    private Midia filme1;
    private Midia filme2;

    @BeforeEach
    void setup() throws Exception {
        salvamento = new Salvamento();
        Genero genero = new Genero("Ação", ETipoGenero.CINEMA);
        Idioma idioma = new Idioma("Italiano");
        filme1 = new Filme("caminho1.mp4", "Filme 1", 2.0f, 120, ETipoArquivo.MP4, genero, idioma);
        filme2 = new Filme("caminho2.mp4", "Filme 2", 1.5f, 90, ETipoArquivo.MP4, genero, idioma);
    }

    // ====================== incluirMidia ==========================
    @Test
    void incluirMidia_MidiaValida_RetornaTrue() { // Caso 1
        boolean resultado = salvamento.incluirMidia(filme1);
        assertTrue(resultado);
        assertEquals(1, salvamento.getMidias().size());
    }

    @Test
    void incluirMidia_MidiaNula_RetornaFalse() { // Caso 2
        boolean resultado = salvamento.incluirMidia(null);
        assertFalse(resultado);
    }

    // ====================== removerMidia ==========================
    @Test
    void removerMidia_MidiaExistente_RetornaTrue() { // Caso 3
        salvamento.incluirMidia(filme1);
        boolean resultado = salvamento.removerMidia(filme1);
        assertTrue(resultado);
        assertEquals(0, salvamento.getMidias().size());
    }

    /*@Test
    void removerMidia_MidiaInexistente_RetornaFalse() { // Caso 4
        // Garante que a lista está vazia
        salvamento.getMidias().clear();

        // Tenta remover uma mídia que não existe na lista
        boolean resultado = salvamento.removerMidia(filme2);

        // Deve retornar false, porque a mídia não está na lista
        assertFalse(resultado, "Remover mídia inexistente deve retornar false.");
    }*/


    // ====================== atualizarMidia ==========================
    @Test
    void atualizarMidia_CaminhoExistente_RetornaTrue() throws CampoVazioOuNuloExcecao {
        // Adiciona filme original
        salvamento.incluirMidia(filme1);

        // Cria dados válidos para atualizar
        Genero generoValido = new Genero("Ação", ETipoGenero.CINEMA);
        Idioma idiomaValido = new Idioma("Português (BR)");

        Midia filmeAtualizado = new Filme(
                "caminho1.mp4",          // caminho igual ao filme1
                "Filme Atualizado",      // novo nome
                2.5f,                    // tamanho
                130,                     // duração
                ETipoArquivo.MP4,        // tipo de arquivo válido
                generoValido,            // gênero válido
                idiomaValido             // idioma válido
        );

        boolean resultado = salvamento.atualizarMidia(filmeAtualizado);

        assertTrue(resultado);
        assertEquals("Filme Atualizado", salvamento.getMidias().get(0).getNome());
    }


    @Test
    void atualizarMidia_CaminhoInexistente_RetornaFalse() { // Caso 6
        boolean resultado = salvamento.atualizarMidia(filme2);
        assertFalse(resultado);
    }

    // ====================== atualizarCaminho ==========================
    @Test
    void atualizarCaminho_CaminhoExistente_RetornaTrue() { // Caso 7
        salvamento.incluirMidia(filme1);
        boolean resultado = salvamento.atualizarCaminho("caminho1.mp4", "novo_caminho.mp4");
        assertTrue(resultado);
        assertEquals("novo_caminho.mp4", salvamento.getMidias().get(0).getCaminho());
    }

    @Test
    void atualizarCaminho_ListaVazia_RetornaTrue() { // Caso 8
        boolean resultado = salvamento.atualizarCaminho("qualquer_caminho.mp4", "novo_caminho.mp4");
        assertTrue(resultado);
        assertTrue(salvamento.getMidias().isEmpty());
    }

    // ====================== getMidias ==========================
    @Test
    void getMidias_ListaComItens_RetornaListaNaoNula() { // Caso 9
        salvamento.incluirMidia(filme1);
        salvamento.incluirMidia(filme2);
        List<Midia> lista = salvamento.getMidias();
        assertNotNull(lista);
        assertEquals(2, lista.size());
    }

    @Test
    void getMidias_ListaVazia_RetornaListaVaziaNaoNula() { // Caso 10
        List<Midia> lista = salvamento.getMidias();
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }
}
