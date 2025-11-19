package controle;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;
import modelo.midias.Filme;
import modelo.midias.Livro;
import modelo.midias.Midia;
import modelo.midias.Musica;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SerializadorTpooTest {

    @TempDir
    File tempDir;

    Genero generoAcao;
    Genero generoLivro;
    Idioma idiomaPortugues;

    @BeforeEach
    void setup() {
        generoAcao = new Genero("AÇÃO", ETipoGenero.CINEMA);
        generoLivro = new Genero("FANTASIA", ETipoGenero.LITERARIO);
        idiomaPortugues = new Idioma("Português");
    }

    // -------------------------------------------------------
    // 1. salvarMidia()
    // -------------------------------------------------------

    @Test
    void salvarMidia_Sucesso_ArquivoCriado() throws Exception {  // Caso 1
        File arquivo = new File(tempDir, "filme.tpoo");

        Midia filme = new Filme(
                arquivo.getAbsolutePath(),
                "Filme Teste",
                1.5f,
                120,
                ETipoArquivo.MP4,
                generoAcao,
                idiomaPortugues
        );

        SerializadorTpoo.salvarMidia(filme);

        assertTrue(arquivo.exists(), "O arquivo .tpoo deve ser criado com sucesso.");
        assertTrue(arquivo.length() > 0, "O arquivo deve conter dados.");
    }

    @Test
    void salvarMidia_CaminhoInvalido_LancaIOException() {  // Caso 2
        Midia filme = new Filme(
                "C:/caminho_inexistente/arquivo.tpoo",
                "Filme Teste",
                1.5f,
                120,
                ETipoArquivo.MP4,
                generoAcao,
                idiomaPortugues
        );

        assertThrows(IOException.class, () -> SerializadorTpoo.salvarMidia(filme));
    }

    // -------------------------------------------------------
    // 2. carregarMidia()
    // -------------------------------------------------------

    @Test
    void carregarMidia_ArquivoValido_RetornaMidiaCompleta() throws Exception {  // Caso 3 (melhorado)
        File arquivo = new File(tempDir, "filme_valido.tpoo");

        Idioma idioma = new Idioma("Mandarim");
        Genero genero = new Genero("ACAO", ETipoGenero.CINEMA);

        // Força os SerializadorTpoo a usar nossa lista de testes
        SerializadorTpoo.listas = new Listas();
        SerializadorTpoo.listas.getListaGeneros().clear();
        SerializadorTpoo.listas.getListaIdiomas().clear();

        SerializadorTpoo.listas.getListaGeneros().add(genero);
        SerializadorTpoo.listas.getListaIdiomas().add(idioma);

        Midia original = new Filme(arquivo.getAbsolutePath(), "Filme Carregado", 2.0f, 140, ETipoArquivo.MP4, genero, idioma
        );

        SerializadorTpoo.salvarMidia(original);

        Midia carregada = SerializadorTpoo.carregarMidia(arquivo);

        assertNotNull(carregada, "A mídia carregada não deve ser nula.");
        assertTrue(carregada instanceof Filme, "O objeto carregado deve ser um Filme.");

        Filme filme = (Filme) carregada;

        assertEquals("Filme Carregado", filme.getNome());
        assertEquals(140, filme.getDuracao());
        assertEquals(2.0f, filme.getTamanho());
        assertEquals(ETipoArquivo.MP4, filme.getTipoArquivo());

        assertEquals("ACAO", filme.getGenero().getNome());
        assertEquals(ETipoGenero.CINEMA, filme.getGenero().getETipoGenero());

        assertEquals("Mandarim", filme.getIdioma().getNome());
        assertEquals(arquivo.getAbsolutePath(), filme.getCaminho());
    }

    @Test
    void carregarMidia_CabecalhoInvalido_LancaIOException() throws Exception {  // Caso 4
        File arquivo = new File(tempDir, "corrompido.tpoo");

        try (FileOutputStream fos = new FileOutputStream(arquivo)) {
            fos.write("XXXX".getBytes()); // cabeçalho inválido
        }

        assertThrows(IOException.class, () -> SerializadorTpoo.carregarMidia(arquivo));
    }

    // -------------------------------------------------------
    // 3. encontrarGenero()
    // -------------------------------------------------------

    @Test
    void encontrarGenero_Existente_RetornaGenero() throws Exception { // Caso 5
        Genero resultado = SerializadorTpoo.encontrarGenero("Ação", ETipoGenero.CINEMA);
        assertNotNull(resultado, "Deve encontrar o gênero existente.");
        assertEquals("Ação", resultado.getNome());
    }

    @Test
    void encontrarGenero_Inexistente_RetornaNull() throws Exception {  // Caso 6
        Genero encontrado = SerializadorTpoo.encontrarGenero("GENERO_INEXISTENTE", ETipoGenero.CINEMA);

        assertNull(encontrado, "Deve retornar null quando o gênero não existe.");
    }

    // -------------------------------------------------------
    // 4. encontrarIdioma()
    // -------------------------------------------------------

    @Test
    void encontrarIdioma_Existente_RetornaIdioma() throws Exception { // Caso 7
        Idioma encontrado = SerializadorTpoo.encontrarIdioma("Português (BR)");
        assertNotNull(encontrado, "Deve encontrar o idioma existente.");
        assertEquals("Português (BR)", encontrado.getNome());
    }

    @Test
    void encontrarIdioma_Inexistente_RetornaNull() throws Exception {  // Caso 8
        Idioma encontrado = SerializadorTpoo.encontrarIdioma("Idioma_Inexistente");

        assertNull(encontrado, "Deve retornar null quando o idioma não existe.");
    }
}
