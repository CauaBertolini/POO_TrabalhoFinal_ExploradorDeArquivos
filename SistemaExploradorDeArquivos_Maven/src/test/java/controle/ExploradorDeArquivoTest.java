package controle;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.ArquivoNaoExisteExcecao;
import modelo.Genero;
import modelo.Idioma;
import modelo.Salvamento;
import modelo.midias.Filme;
import modelo.midias.Livro;
import modelo.midias.Midia;
import modelo.midias.Musica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.JOptionPaneUtil;
import visao.PaginaPrincipal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExploradorDeArquivosTest {

    // --------------------------------------------------------------
    //  MOCKS
    // --------------------------------------------------------------
    @Test
    public void testCarregarCSV_SemFalhar_ComPaginaPrincipalNula() throws Exception {

        // Mock estático do GerenciadorCSV
        try (MockedStatic<GerenciadorCSV> mock = Mockito.mockStatic(GerenciadorCSV.class)) {

            // Impede caminhos reais inexistentes → nenhum arquivo será carregado
            mock.when(GerenciadorCSV::carregarCaminhos)
                    .thenReturn(List.of());

            // Aqui está o ponto principal:
            // PaginaPrincipal = null → o Explorador NÃO tenta atualizar a UI
            ExploradorDeArquivos explorador = new ExploradorDeArquivos(null);

            // Este método agora NÃO lança IOException
            explorador.carregarMidiasDoCSV();
        }
    }

    private static class HomePageMock extends PaginaPrincipal {

        private int atualizacaoTabelaCount = 0;
        private int limparPainelDireitoCount = 0;

        // Impede execução do construtor real da PaginaPrincipal
        private HomePageMock() {
            super(false); // você criará este construtor alternativo
        }

        @Override
        public void atualizarTabela() {
            atualizacaoTabelaCount++;
        }

        @Override
        public void limparPainelDireito() {
            limparPainelDireitoCount++;
        }

        public int getAtualizacaoTabelaCount() {
            return atualizacaoTabelaCount;
        }

        public int getLimparPainelDireitoCount() {
            return limparPainelDireitoCount;
        }

        public void resetCounts() {
            atualizacaoTabelaCount = 0;
            limparPainelDireitoCount = 0;
        }
    }

    private static class ExploradorDeArquivosComFalha extends ExploradorDeArquivos {

        private boolean proximaConfirmacao = true;
        private boolean forcarFalhaIo = false;

        public ExploradorDeArquivosComFalha(PaginaPrincipal homePage) {
            super(homePage);
        }

        public void setProximaConfirmacao(boolean confirmacao) {
            this.proximaConfirmacao = confirmacao;
        }

        public void setForcarFalhaIo(boolean forcar) {
            this.forcarFalhaIo = forcar;
        }

        @Override
        public boolean confirmarSubstituicaoArquivo() {
            return proximaConfirmacao;
        }

        protected void removerArquivoDoDisco(Path caminho) throws IOException {
            if (forcarFalhaIo) {
                throw new IOException("Simulação de erro de I/O forçada.");
            }
            Files.deleteIfExists(caminho);
        }

        protected void salvarMidiaNoDisco(Midia midia) throws IOException {
            if ("/caminho/inacessivel/erro.tpoo".equals(midia.getCaminho())) {
                throw new IOException("Simulação de erro de salvamento forçada.");
            }
            SerializadorTpoo.salvarMidia(midia);
        }
    }

    // --------------------------------------------------------------
    // VARIÁVEIS DE TESTE
    // --------------------------------------------------------------

    private ExploradorDeArquivosComFalha explorador;
    private HomePageMock homePageMock;

    private Genero generoFilme;
    private Genero generoLivro;
    private Genero generoMusica;
    private Idioma idioma;

    @TempDir
    Path tempDir;

    // --------------------------------------------------------------
    // SETUP
    // --------------------------------------------------------------

    @BeforeEach
    void setUp() {
        homePageMock = new HomePageMock();
        explorador = new ExploradorDeArquivosComFalha(homePageMock);

        generoFilme = new Genero("Ação", ETipoGenero.CINEMA);
        generoLivro = new Genero("Fantasia", ETipoGenero.LITERARIO);
        generoMusica = new Genero("Pop", ETipoGenero.MUSICAL);

        idioma = new Idioma("Português (BR)");

        explorador.setProximaConfirmacao(true);
        explorador.setForcarFalhaIo(false);
        homePageMock.resetCounts();
    }

    // --------------------------------------------------------------
    // HELPERS
    // --------------------------------------------------------------

    private Midia simularInclusaoCompleta(
            String nome,
            Class<? extends Midia> tipo,
            Genero genero,
            ETipoArquivo tipoArquivo) throws Exception {

        // ⚠ ERRO ORIGINAL dentro do seu código:
        // String caminhoCompleto = tempDir.resolve("desktopPath" + nome + ".tpoo").toString();

        String caminhoCompleto = tempDir.resolve(nome + ".tpoo").toString();

        Midia midia;

        if (tipo.equals(Filme.class)) {
            midia = new Filme(caminhoCompleto, nome, 100f, 60.0, tipoArquivo, genero, idioma);
        } else if (tipo.equals(Livro.class)) {
            midia = new Livro(caminhoCompleto, nome, 5f, 300.0, tipoArquivo, genero, "Autor");
        } else {
            midia = new Musica(caminhoCompleto, nome, 3f, 3.5, tipoArquivo, genero, "Artista");
        }

        SerializadorTpoo.salvarMidia(midia);
        explorador.getSalvamento().incluirMidia(midia);

        return midia;
    }

    private File criarArquivoVazio(String nome) throws IOException {
        File arquivo = tempDir.resolve(nome + ".tpoo").toFile();
        Files.createFile(arquivo.toPath());
        return arquivo;
    }

    // =====================================================================
    // 1. TESTES DE CRIAÇÃO
    // =====================================================================

    @Nested
    @DisplayName("Criação de Mídias")
    class CriacaoMidiaTest {

        Path desktop = Paths.get(System.getProperty("user.home"), "Desktop");
        String CAMINHO = desktop.toString();

        private final String NOME = "MidiaTeste";

        @Test
        void criarFilme_Sucesso_RetornaTrue() throws Exception {
            assertTrue(explorador.criarNovaMidia(
                    CAMINHO, NOME, 100f, 60.0, ETipoArquivo.MP4, generoFilme, idioma));
        }

        @Test
        void criarMidia_ArquivoExiste_NaoConfirma_RetornaFalse() throws Exception {
            criarArquivoVazio(NOME);
            explorador.setProximaConfirmacao(false);

            assertFalse(explorador.criarNovaMidia(
                    CAMINHO, NOME, 100f, 60.0, ETipoArquivo.MP4, generoFilme, idioma));
        }

        @Test
        void criarMidia_ArquivoExiste_Confirma_RetornaTrue() throws Exception {
            criarArquivoVazio(NOME);
            explorador.setProximaConfirmacao(true);

            assertTrue(explorador.criarNovaMidia(
                    CAMINHO, NOME, 100f, 60.0, ETipoArquivo.MP4, generoFilme, idioma));
        }

        @Test
        void criarMidia_CampoVazio_TrataExcecao() {
            assertFalse(explorador.criarNovaMidia(
                    CAMINHO, "", 100f, 60.0, ETipoArquivo.MP4, generoFilme, idioma));
        }

        @Test
        void criarMidia_TamanhoInvalido_TrataExcecao() {
            assertFalse(explorador.criarNovaMidia(
                    CAMINHO, NOME, 0f, 60.0, ETipoArquivo.MP4, generoFilme, idioma));
        }

        @Test
        void criarLivro_Sucesso_RetornaTrue() throws Exception {
            assertTrue(explorador.criarNovaMidia(
                    CAMINHO, NOME, 5f, 300.0, ETipoArquivo.PDF, generoLivro, "Autor", true));
        }

        @Test
        void criarMusica_Sucesso_RetornaTrue() throws Exception {
            assertTrue(explorador.criarNovaMidia(
                    CAMINHO, NOME, 3f, 3.5, ETipoArquivo.MP3, generoMusica, "Artista", false));
        }
    }


    // =====================================================================
    // 2. TESTES DE ALTERAÇÃO
    // =====================================================================

    @Nested
    @DisplayName("Alteração de Mídias")
    class AlteracaoMidiaTest {

        private Midia midiaFilme;
        private Midia midiaLivro;

        @BeforeEach
        void setupAlteracao() throws Exception {
            midiaFilme = simularInclusaoCompleta("Filme", Filme.class, generoFilme, ETipoArquivo.MP4);
            midiaLivro = simularInclusaoCompleta("Livro", Livro.class, generoLivro, ETipoArquivo.PDF);
            simularInclusaoCompleta("Musica", Musica.class, generoMusica, ETipoArquivo.MP3);
        }

        @Test
        void alterarFilme_Sucesso_RetornaTrue() throws Exception {
            Genero novoGenero = new Genero("Suspense", ETipoGenero.CINEMA);
            double novaDuracao = 150.0;

            assertTrue(explorador.alterarMidia(
                    midiaFilme.getCaminho(), 100f, novaDuracao, novoGenero, ETipoArquivo.MKV, idioma));
        }

        @Test
        void alterarMidia_ArquivoNaoExiste_LancaExcecao() {
            assertThrows(ArquivoNaoExisteExcecao.class, () ->
                    explorador.alterarMidia(
                            tempDir.resolve("naoexiste.tpoo").toString(),
                            1f, 1.0, generoFilme, ETipoArquivo.MP4, idioma));
        }

        @Test
        void alterarFilme_ArquivoNaoEFilme_RetornaFalse() throws Exception {
            assertFalse(explorador.alterarMidia(
                    midiaLivro.getCaminho(), 100f, 150.0, generoFilme, ETipoArquivo.MP4, idioma));
        }

        @Test
        void alterarFilme_ErroAoSalvar_LancaRuntimeException() {
            midiaFilme.setCaminho(tempDir.resolve("erro.tpoo").toString());

            assertThrows(RuntimeException.class, () ->
                    explorador.alterarMidia(
                            midiaFilme.getCaminho(), 100f, 150.0, generoFilme, ETipoArquivo.MKV, idioma));
        }

        @Test
        void alterarLivro_Sucesso_RetornaTrue() throws Exception {
            String novoAutor = "Novo Autor Teste";

            assertTrue(explorador.alterarMidia(
                    midiaLivro.getCaminho(), 6f, 500.0,
                    generoLivro, ETipoArquivo.EPUB, novoAutor, true));
        }

        @Test
        void alterarMusica_Sucesso_RetornaTrue() throws Exception {
            String novoArtista = "Novo Artista Teste";

            String caminhoDiretorio = tempDir.toString();
            String nomeMusica = "MusicaTeste";

            explorador.criarNovaMidia(
                    caminhoDiretorio, nomeMusica, 3f, 3.5, ETipoArquivo.MP3,
                    generoMusica, "ArtistaOriginal", false);

            Path arquivoMusica = tempDir.resolve(nomeMusica + ".tpoo");

            boolean resultado = explorador.alterarMidia(
                    arquivoMusica.toString(), 4f, 4.0,
                    generoMusica, ETipoArquivo.MP3, novoArtista, false);

            assertTrue(resultado);
        }

        @Test
        void alterarMidia_TiposIncompativeis_RetornaFalse() throws Exception {
            String caminhoDiretorio = tempDir.toString();
            String nomeArquivo = "TipoIncompativel";

            explorador.criarNovaMidia(
                    caminhoDiretorio, nomeArquivo, 100f, 60.0,
                    ETipoArquivo.MP4, generoFilme, idioma);

            Path arquivoIncompativel = tempDir.resolve(nomeArquivo + ".tpoo");

            assertFalse(explorador.alterarMidia(
                    arquivoIncompativel.toString(), 1f, 1.0,
                    generoLivro, ETipoArquivo.PDF, "Autor", true));
        }
    }

    // =====================================================================
    // 3. TESTES DE EXCLUSÃO
    // =====================================================================

    @Nested
    @DisplayName("Exclusão de Mídias")
    class ExclusaoMidiaTest {

        private Midia midiaParaExcluir;

        @BeforeEach
        void setupExclusao() throws Exception {
            midiaParaExcluir = simularInclusaoCompleta(
                    "Excluir", Filme.class, generoFilme, ETipoArquivo.MP4);
        }

        @Test
        void excluirMidia_Sucesso_RetornaTrue() {
            assertTrue(explorador.excluirMidia(midiaParaExcluir));
        }

        @Test
        void excluirMidia_ArquivoNaoExiste_RetornaTrue() throws Exception {
            Files.delete(Path.of(midiaParaExcluir.getCaminho()));
            assertTrue(explorador.excluirMidia(midiaParaExcluir));
        }
    }

    // =====================================================================
    // 4. TESTES DE RENOMEAÇÃO
    // =====================================================================

    @Nested
    @DisplayName("Renomeação de Mídias")
    class RenomeacaoMidiaTest {

        private Midia midiaParaRenomear;
        private final String NOVO_NOME = "MidiaRenomeada";

        @BeforeEach
        void setupRenomeacao() throws Exception {
            midiaParaRenomear = simularInclusaoCompleta(
                    "NomeAntigo", Filme.class, generoFilme, ETipoArquivo.MP4);
        }

        @Test
        void renomearMidia_Sucesso_RetornaTrue() throws ArquivoNaoExisteExcecao {
            assertTrue(explorador.renomearMidia(midiaParaRenomear, NOVO_NOME));
        }

        @Test
        void renomearMidia_ArquivoNaoExiste_LancaExcecao() {
            assertThrows(ArquivoNaoExisteExcecao.class, () -> {
                Files.deleteIfExists(Path.of(midiaParaRenomear.getCaminho()));
                explorador.renomearMidia(midiaParaRenomear, NOVO_NOME);
            });
        }

        @Test
        void renomearMidia_NomeVazio_LancaExcecao() {
            assertThrows(Exception.class,
                    () -> explorador.renomearMidia(midiaParaRenomear, ""));
        }
    }

    // =====================================================================
    // 5. TESTES DE MOVIMENTAÇÃO
    // =====================================================================

    @Nested
    @DisplayName("Movimentação de Mídias")
    class MovimentacaoMidiaTest {

        private Midia midiaParaMover;
        private Path novoDir;

        @BeforeEach
        void setupMovimentacao() throws Exception {
            midiaParaMover = simularInclusaoCompleta(
                    "ParaMover", Filme.class, generoFilme, ETipoArquivo.MP4);

            novoDir = tempDir.resolve("NovoDestino");
            Files.createDirectory(novoDir);
        }

        @Test
        void moverMidia_Sucesso_RetornaTrue() throws Exception {
            assertTrue(explorador.moverMidia(midiaParaMover, novoDir.toString()));
        }

        @Test
        void moverMidia_DestinoExiste_NaoConfirma_RetornaFalse() throws Exception {
            Files.createFile(novoDir.resolve(midiaParaMover.getNome() + ".tpoo"));
            explorador.setProximaConfirmacao(false);

            assertFalse(explorador.moverMidia(midiaParaMover, novoDir.toString()));
        }

        @Test
        void moverMidia_OrigemNaoExiste_LancaExcecao() throws Exception {
            Files.deleteIfExists(Path.of(midiaParaMover.getCaminho()));

            assertThrows(ArquivoNaoExisteExcecao.class,
                    () -> explorador.moverMidia(midiaParaMover, novoDir.toString()));
        }
    }

    // =====================================================================
    // 6. TESTES DE FILTRAGEM
    // =====================================================================

    @Nested
    @DisplayName("Filtragem de Mídias")
    class FiltragemMidiaTest {

        @BeforeEach
        void setupFiltros() throws Exception {
            simularInclusaoCompleta("Acao1", Filme.class, generoFilme, ETipoArquivo.MP4);
            simularInclusaoCompleta("Drama1", Filme.class, new Genero("Drama", ETipoGenero.CINEMA), ETipoArquivo.MKV);
            simularInclusaoCompleta("MusicaPop1", Musica.class, generoMusica, ETipoArquivo.MP3);
            simularInclusaoCompleta("LivroFantasia1", Livro.class, generoLivro, ETipoArquivo.PDF);
        }

        @Test
        void filtrarMidias_PorGenero_RetornaCorreto() {
            List<Midia> filtradas =
                    explorador.filtrarMidias("AÇÃO", "TODOS", "TODOS");

            assertEquals(1, filtradas.size());
        }

        @Test
        void filtrarMidias_PorTipo_RetornaCorreto() {
            List<Midia> filtradas =
                    explorador.filtrarMidias("POP", "MP3", "Musica");

            assertEquals(1, filtradas.size());
        }


        @Test
        void filtrarMidias_Combinado_RetornaCorreto() {
            List<Midia> filtradas =
                    explorador.filtrarMidias("DRAMA", "MKV", "FILME");

            assertEquals(1, filtradas.size());
        }

        @Test
        void filtrarMidias_Todos_RetornaTodas() {
            List<Midia> filtradas =
                    explorador.filtrarMidias("TODOS", "TODOS", "TODOS");

            assertEquals(4, filtradas.size());
        }
    }

    // =====================================================================
// 7. TESTES DE ORDENAÇÃO
// =====================================================================

    @Nested
    @DisplayName("Ordenação de Mídias")
    class OrdenacaoMidiaTest {

        @BeforeEach
        void setupOrdenacao() throws Exception {
            // Ordem propositalmente fora de ordem: C, A, B
            simularInclusaoCompleta("C_Nome", Filme.class, generoFilme, ETipoArquivo.MP4);
            simularInclusaoCompleta("A_Nome", Livro.class, generoLivro, ETipoArquivo.PDF);
            simularInclusaoCompleta("B_Nome", Musica.class, generoMusica, ETipoArquivo.MP3);

            // Garantir que os tamanhos/durações sejam diferentes para testes não ambíguos
            explorador.getSalvamento().getMidias().get(0).setDuracao(300); // C
            explorador.getSalvamento().getMidias().get(1).setDuracao(100); // A
            explorador.getSalvamento().getMidias().get(2).setDuracao(200); // B
        }

        // ------------------------------------------------------------
        // 7.1 Ordenar por Nome
        // ------------------------------------------------------------
        @Test
        @DisplayName("Ordenar por Nome A-Z deve ordenar alfabeticamente")
        void ordenarMidiasPorNome_DeveOrdenarAlfabeticamente() {

            explorador.ordenarMidias(
                    explorador.getSalvamento().getMidias(),
                    "Nome A-Z"
            );

            assertEquals("A_Nome", explorador.getSalvamento().getMidias().get(0).getNome());
            assertEquals("B_Nome", explorador.getSalvamento().getMidias().get(1).getNome());
            assertEquals("C_Nome", explorador.getSalvamento().getMidias().get(2).getNome());
        }

        // ------------------------------------------------------------
        // 7.2 Ordenar por Duração/Tamanho
        // ------------------------------------------------------------
        @Test
        @DisplayName("Ordenar por Duração Crescente deve ordenar corretamente")
        void ordenarMidiaPorTamanho_DeveOrdenarPorTamanhoCrescente() {

            explorador.ordenarMidias(
                    explorador.getSalvamento().getMidias(),
                    "Duração Crescente"
            );

            assertEquals("A_Nome", explorador.getSalvamento().getMidias().get(0).getNome()); // 100
            assertEquals("B_Nome", explorador.getSalvamento().getMidias().get(1).getNome()); // 200
            assertEquals("C_Nome", explorador.getSalvamento().getMidias().get(2).getNome()); // 300
        }
    }

}
