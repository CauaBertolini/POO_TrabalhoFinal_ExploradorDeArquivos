package controle;

import enumerador.*;
import excecao.*;
import modelo.*;
import modelo.midias.Filme;
import modelo.midias.Livro;
import modelo.midias.Midia;
import modelo.midias.Musica;
import util.ExcecaoUtil;
import util.GerenciadorCSV;
import util.JOptionPaneUtil;
import visao.HomePage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * A classe ExploradorDeArquivos é a responsável pela criação das Mídias e dos arquivos do tipo
 * .tpoo. Além de modificar, excluir, mover e filtrar essas mídias que estão contidas no lista Salvamento.
 */
public class ExploradorDeArquivos {
    private Salvamento salvamento;
    private Listas listas = new Listas();
    private HomePage homePage;
    SerializadorTpoo serializadorTpoo = new SerializadorTpoo();

    /**
     * Constrói um novo ExploradorDeArquivos associando-o à interface principal
     * da aplicação e inicializando o gerenciador de salvamento.
     *
     * @param homePage a instância da tela principal que será vinculada ao explorador
     *                 para atualização de interface e ações relacionadas.
     */
    public ExploradorDeArquivos(HomePage homePage) {
        salvamento = new Salvamento();
        this.homePage = homePage;
    }

    public void carregarMidiasCSV() {
        try {
            List<String> caminhosMidiasCSV = GerenciadorCSV.carregarCaminhos();

            for (String linha : caminhosMidiasCSV) {
                carregarArquivo(linha);
            }

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Cria uma nova mídia do tipo Filme
     * <p>
     * Ele monta o caminho completo do arquivo, instancia a mídia, trata possíveis
     * exceções de validação, confirma substituição caso o arquivo já exista,
     * inclui a mídia no sistema e realiza a serialização para disco.
     *
     * @param caminho-    Caminho do usuário onde o arquivo vai ser salvo.
     * @param nome-       Nome da Mídia a ser criada.
     * @param tamanho     - Tamanho do arquivo em bytes.
     * @param duracao     - Duração do filme em minutos.
     * @param tipoArquivo O tipo de arquivo de acordo com o tipo de Mídia criada. No caso de filme, MKV ou MP4.
     * @param genero      - Gênero da Mídia para tipo cinema.
     * @param idioma      - Idioma principal do filme criado.
     * @return O método retorna {@code true} caso o objeto tenha sido criado com sucesso e retorna {@code false} se a ação for
     * cancelada ou ocorrerem erros
     * @throws CampoVazioOuNuloExcecao       - Lançada quando o campo recebido estiver vazio ou nulo.
     * @throws CampoMenorOuIgualAZeroExcecao - Lançada quando o campo recebido for igual ou menor que zero.
     */
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo, Genero genero, Idioma idioma) throws CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao {

        String caminhoCompleto = Paths.get(caminho, nome + ".tpoo").toString();
        modelo.midias.Midia novaMidia = null;


        try {
            novaMidia = new Filme(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, idioma);
        } catch (CampoVazioOuNuloExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro(excecao.getMessage());
        } catch (CampoMenorOuIgualAZeroExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro(excecao.getMessage());
        }

        File arquivoNovo = new File(caminhoCompleto);

        try {
            if (arquivoNovo.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                return false;
            }

            salvamento.incluirMidia(novaMidia);

            try {
                SerializadorTpoo.salvarMidia(novaMidia);
            } catch (IOException excecao) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar midia.\n" + excecao.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

            GerenciadorCSV.atualizarCSV(salvamento.getMidias());

            homePage.limparPainelDireito();
            homePage.atualizarTabela();//adicionei isso para atualizar a tabela de midias na home page
            return true;
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro(excecao.getMessage());
            return false;
        }
    }

    /**
     * Cria uma nova mídia do tipo Música ou Livro.
     * <p>
     * O método monta o caminho completo do arquivo, instancia a mídia correspondente,
     * trata exceções de validação, confirma substituição caso o arquivo já exista,
     * inclui a mídia no sistema e realiza sua serialização em disco.
     *
     * @param caminho        - Caminho do usuário onde o arquivo vai ser salvo.
     * @param nome           - Nome da Mídia a ser criada.
     * @param tamanho        - Tamanho do arquivo em bytes.
     * @param duracao        - Duração da música em minutos ou número de páginas em Livro.
     * @param tipoArquivo    - O tipo de arquivo de acordo com o tipo de Mídia criada. MP3 para música e
     *                       PDF ou EPUB para Livro.
     * @param genero         - Gênero da Mídia para tipo Cinema.
     * @param autorOuArtista -Nome do artista da música ou do Autor do Livro.
     * @param eLivro         -Define o tipo da mídia a ser criada: {@code true} para Livro e {@code false} para Música.
     * @return O método retorna {@code true} caso o objeto tenha sido criado com sucesso e retorna {@code false} se houver o
     * cancelamento da ação ou caso alguma exceção seja lançada na hora da criação do arquivo
     * @throws CampoVazioOuNuloExcecao       - Lançada quando o campo recebido estiver vazio ou nulo.
     * @throws CampoMenorOuIgualAZeroExcecao - Lançada quando o campo recebido for igual ou menor que zero.
     */
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo,
                                  Genero genero, String autorOuArtista, boolean eLivro) throws CampoMenorOuIgualAZeroExcecao, CampoVazioOuNuloExcecao {
        String caminhoCompleto = Paths.get(caminho, nome + ".tpoo").toString();
        modelo.midias.Midia novaMidia = null;

        try {
            novaMidia = eLivro
                    ? new Livro(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, autorOuArtista)
                    : new Musica(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, autorOuArtista);
        } catch (CampoVazioOuNuloExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro(excecao.getMessage());
        } catch (CampoMenorOuIgualAZeroExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro(excecao.getMessage());
        }

        File arquivoNovo = new File(caminhoCompleto);

        try {
            if (arquivoNovo.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPaneUtil.mostrarMensagemErro("Ação cancelada!");
                return false;
            }

            salvamento.incluirMidia(novaMidia);

            try {
                SerializadorTpoo.salvarMidia(novaMidia);
            } catch (IOException excecao) {
                JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia:\n" + excecao.getMessage());
            }

            GerenciadorCSV.atualizarCSV(salvamento.getMidias());

            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia:\n" + excecao.getMessage());
            return false;
        }
    }

    /**
     * Altera os atributos de uma mídia já existente do tipo Filme.
     * O caminho do arquivo permanece inalterado e apenas os atributos internos da mídia
     * são atualizados (tamanho, duração, tipo de arquivo e idioma).
     * <p>
     * O método carrega a mídia do arquivo informado, verifica se ela é um Filme e,
     * caso seja, atualiza seus valores e salva novamente o arquivo serializado
     *
     * @param caminhoArquivo - Caminho completo do arquivo .tpoo já existente.
     * @param tamanho        - O tamanho do arquivo em Bytes.
     * @param duracao        - Duração do Filme em minutos.
     * @param genero         - Gênero da Mídia.
     * @param eTipoArquivo   - O tipo do arquivo criado.
     * @param idioma         - Principal idioma do filme.
     * @return Se a mídia for alterada com sucesso é retornado {@code true}. Se a Mídia não for Filme ou
     * houver algum erro é retornado {@code false}
     * @throws ArquivoNaoExisteExcecao       - Se o arquivo informado não existir no caminho especificado.
     * @throws CampoVazioOuNuloExcecao       - Lançada quando o campo recebido estiver vazio ou nulo.
     * @throws CampoMenorOuIgualAZeroExcecao - Lançada quando o campo recebido for igual ou menor que zero.
     */
    public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao, Genero genero,
                                ETipoArquivo eTipoArquivo, Idioma idioma)
            throws ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao {

        try {
            if (!ExcecaoUtil.arquivoExiste(caminhoArquivo)) {
                throw new ArquivoNaoExisteExcecao();
            }

            File arquivo = new File(caminhoArquivo);
            modelo.midias.Midia midiaAlterando = SerializadorTpoo.carregarMidia(arquivo);

            if (!(midiaAlterando instanceof Filme filmeAlterando)) {
                return false; // não é um filme, ignora
            }

            filmeAlterando.setGenero(genero);
            filmeAlterando.setTamanho(tamanho);
            filmeAlterando.setDuracao(duracao);
            filmeAlterando.setTipoArquivo(eTipoArquivo);
            filmeAlterando.setIdioma(idioma);

            salvamento.atualizarMidia(filmeAlterando);
            SerializadorTpoo.salvarMidia(filmeAlterando);

            GerenciadorCSV.atualizarCSV(salvamento.getMidias());

            homePage.atualizarTabela();
            return true;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + e.getMessage(), e);
        }
    }

    /**
     * Altera os atributos de uma mídia já existente do tipo Música e Livro.
     * O caminho do arquivo permanece inalterado e apenas os atributos internos da mídia
     * são atualizados (tamanho, duração, tipo de arquivo e idioma).
     * <p>
     * O método carrega a mídia do arquivo informado, verifica se ela é um Música ou Livro, e
     * caso seja, atualiza seus valores e salva novamente o arquivo serializado.
     *
     * @param caminhoArquivo - Caminho completo do arquivo .tpoo já existente.
     * @param tamanho        - Novo tamanho do arquivo em bytes.
     * @param duracao        - Nova duração do filme em minutos.
     * @param genero         - Gênero da Mídia.
     * @param eTipoArquivo   - Novo tipo de arquivo da mídia.
     * @param autorOuArtista - Novo autor ou artista da Música ou Livro.
     * @param eLivro         -Define o tipo da mídia a ser criada: {@code true} para Livro e {@code false} para Música.
     * @return Se a mídia for alterada com sucesso é retornada {@code true}, caso a Mídia não for Música ou Filme
     * ou houver algum erro, é retornado {@code false}
     * @throws ArquivoNaoExisteExcecao - Se o arquivo informado não existir no caminho especificado.
     */
    public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao, Genero genero,
                                ETipoArquivo eTipoArquivo, String autorOuArtista, boolean eLivro)
            throws ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao {

        try {
            if (!ExcecaoUtil.arquivoExiste(caminhoArquivo)) {
                throw new ArquivoNaoExisteExcecao();
            }

            File arquivo = new File(caminhoArquivo);
            modelo.midias.Midia midiaAlterando = SerializadorTpoo.carregarMidia(arquivo);

            if (eLivro && midiaAlterando instanceof Livro livroAlterando) {
                livroAlterando.setGenero(genero);
                livroAlterando.setTamanho(tamanho);
                livroAlterando.setDuracao(duracao);
                livroAlterando.setAutor(autorOuArtista);
                livroAlterando.setTipoArquivo(eTipoArquivo);

                SerializadorTpoo.salvarMidia(livroAlterando);
                salvamento.atualizarMidia(livroAlterando);
                homePage.atualizarTabela();
                return true;

            } else if (!eLivro && midiaAlterando instanceof modelo.midias.Musica musicaAlterando) {
                musicaAlterando.setGenero(genero);
                musicaAlterando.setTamanho(tamanho);
                musicaAlterando.setDuracao(duracao);
                musicaAlterando.setArtista(autorOuArtista);
                musicaAlterando.setTipoArquivo(eTipoArquivo);

                SerializadorTpoo.salvarMidia(musicaAlterando);
                salvamento.atualizarMidia(musicaAlterando);

                GerenciadorCSV.atualizarCSV(salvamento.getMidias());

                homePage.limparPainelDireito();
                homePage.atualizarTabela();
                return true;
            }

            return false; // tipo incompatível

        } catch (IOException excecao) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + excecao.getMessage(), excecao);
        }
    }

    /**
     * Exclui uma mídia tanto do sistema de arquivos (desktop) quanto da lista
     * de mídias armazenadas no sistema. A exclusão do arquivo é realizada por meio
     * Files.deleteIfExists.
     *
     * @param midia - Mídia que será deletada.
     * @return O método retorna {@code true} caso seja deletada com sucesso, e se houver algum problema na hora de exclusão
     * é retornado {@code false}.
     */
    public boolean excluirMidia(Midia midia) {
        try {
            Path caminho = Paths.get(midia.getCaminho());
            Files.deleteIfExists(caminho);
            salvamento.removerMidia(midia);

            GerenciadorCSV.atualizarCSV(salvamento.getMidias());

            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia:\n" + excecao.getMessage());
            return false;
        }
    }

    /**
     * Renomeia uma mídia, alterando também o caminho do arquivo no sistema.
     * <p>
     * O método verifica a existência do arquivo, constrói o novo caminho com o nome atualizado
     * e executa a operação de renomeação no sistema de arquivos.
     *
     * @param midia          - Mídia que será renomeada.
     * @param novoNomeString - Novo nome que o arquivo deverá receber.
     * @return O método retorna {@code true} se o arquivo for renomeado com sucesso; {@code false} caso contrário.
     * @throws ArquivoNaoExisteExcecao se o arquivo original não existir.
     */
    public boolean renomearMidia(Midia midia, String novoNomeString) throws ArquivoNaoExisteExcecao {
        if (!ExcecaoUtil.arquivoExiste(midia.getCaminho())) {
            return false;
        }
        try {
            File arquivo = new File(midia.getCaminho());
            File novoNome = new File(arquivo.getParent(), novoNomeString + ".tpoo");

            Files.move(arquivo.toPath(), novoNome.toPath(), StandardCopyOption.REPLACE_EXISTING);

            midia.setCaminho(novoNome.getAbsolutePath());
            midia.setNome(novoNomeString);

            SerializadorTpoo.salvarMidia(midia);

            GerenciadorCSV.atualizarCSV(salvamento.getMidias());

            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;

        } catch (IOException e) {
            return false;
        }
    }


    /**
     * Move a mídia para um novo diretório, atualizando o caminho salvo no objeto.
     * <p>
     * O método verifica se o arquivo original existe, monta o novo destino com base no
     * diretório informado e no nome atual da mídia, confirma substituição caso o arquivo
     * já exista no destino e, por fim, realiza a operação de movimentação.
     *
     * @param midia             - Mídia cujo arquivo será movido.
     * @param novoCaminhoString - Caminho do diretório onde o arquivo deverá ser movido.
     * @return {@code true} se o arquivo for movido com sucesso; {@code false} caso a operação
     * seja cancelada ou não possa ser concluída.
     * @throws ArquivoNaoExisteExcecao Se o arquivo original não existir.
     * @throws CampoVazioOuNuloExcecao Se o novo caminho informado for vazio ou nulo.
     */
    public boolean moverMidia(Midia midia, String novoCaminhoString)
            throws ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao {
        if (ExcecaoUtil.arquivoExiste(midia.getCaminho())) {
            File arquivo = new File(midia.getCaminho());
            File novoCaminho = new File(novoCaminhoString, midia.getNome() + ".tpoo");

            if (novoCaminho.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPaneUtil.mostrarMensagemErro("Ação cancelada!");
                return false;
            }

            arquivo.renameTo(novoCaminho);
            midia.setCaminho(novoCaminho.getAbsolutePath());

            try {
                SerializadorTpoo.salvarMidia(midia);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            GerenciadorCSV.atualizarCSV(salvamento.getMidias());

            homePage.limparPainelDireito();
            homePage.atualizarTabela();

            return true;
        }
        return false;
    }

    /**
     *
     */
    public boolean carregarArquivo(String caminho) throws IOException {
        File arquivo = new File(caminho);
        Midia novaMidia = SerializadorTpoo.carregarMidia(arquivo);
        salvamento.incluirMidia(novaMidia);

        GerenciadorCSV.atualizarCSV(salvamento.getMidias());

        homePage.limparPainelDireito();
        homePage.atualizarTabela();
        return false;
    }

    /**
     * Abre um seletor de arquivos configurado para escolher exclusivamente arquivos
     * com extensão .tpoo. Caso o usuário selecione um arquivo,
     * retorna o caminho absoluto dele. Caso contrário, retorna {@code null}.
     *
     * @return o caminho absoluto do arquivo selecionado ou {@code null} se a seleção for cancelada.
     */
    public String abrirSeletorDeArquivoTpoo() {
        JFileChooser seletorTpoo = new JFileChooser();
        seletorTpoo.setFileFilter(new FileNameExtensionFilter("Arquivos .tpoo", "tpoo"));
        seletorTpoo.setDialogTitle("Selecione um arquivo");
        seletorTpoo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (seletorTpoo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return seletorTpoo.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    /**
     * Abre um seletor de diretórios permitindo que o usuário escolha apenas pastas.
     * <p>
     * Caso o usuário selecione um diretório, o caminho absoluto dele é retornado.
     * Caso a operação seja cancelada, o método retorna {@code null}.
     * </p>
     *
     * @return o caminho absoluto do diretório selecionado, ou {@code null} caso a seleção seja cancelada.
     */
    public String abrirSeletorDeDiretorio() {
        JFileChooser seletorDiretorio = new JFileChooser();
        seletorDiretorio.setDialogTitle("Selecione uma pasta");
        seletorDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (seletorDiretorio.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return seletorDiretorio.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    /**
     * Exibe uma caixa de diálogo solicitando ao usuário a confirmação para sobrescrever
     * um arquivo existente no destino.
     * <p>
     * O método utiliza um JOptionPane com opções "Sim" e "Não". Caso o usuário
     * confirme a operação, o método retorna {@code true} ou caso ele recuse ou feche o diálogo,
     * retorna {@code false}.
     * </p>
     *
     * @return {@code true} se o usuário confirmar a substituição; {@code false} caso contrário.
     */
    public boolean confirmarSubstituicaoArquivo() {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja substituir o arquivo destino pelo atual?",
                "Confirmação de Sobrescrita",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        return resposta == JOptionPane.YES_OPTION;
    }

    /**
     * Filtra a lista de mídias com base no gênero e no tipo de arquivo informados.
     * <p>
     * O método percorre todas as mídias armazenadas e aplica duas regras:
     * <ul>
     *     <li>O gênero deve corresponder ao filtro informado, ou ser {@code "TODOS"} para não filtrar por gênero.</li>
     *     <li>O tipo de arquivo deve corresponder ao filtro informado, ou ser {@code "TODOS"} para não filtrar por tipo.</li>
     * </ul>
     * Caso ambos os critérios sejam atendidos, a mídia é adicionada ao resultado.
     *
     * @param generoFiltrar gênero a ser filtrado, ou {@code "TODOS"} para incluir todos os gêneros.
     * @param tipoFiltrar   tipo de arquivo a ser filtrado, ou {@code "TODOS"} para incluir todos os tipos.
     * @return uma lista contendo todas as mídias que atendem aos critérios de filtro.
     */
    public List<Midia> filtrarMidias(String generoFiltrar, String tipoFiltrar) {

        List<Midia> filtradas = new ArrayList<>();

        for (Midia m : salvamento.getMidias()) {

            boolean generoOK = generoFiltrar.equalsIgnoreCase("TODOS")
                    || m.getGenero().getNome().equalsIgnoreCase(generoFiltrar);

            boolean tipoOK = tipoFiltrar.equalsIgnoreCase("TODOS")
                    || m.getTipoArquivo().name().equalsIgnoreCase(tipoFiltrar);

            if (generoOK && tipoOK) {
                filtradas.add(m);
            }
        }

        return filtradas;
    }

    /**
     * Ordena uma lista de mídias pelo tamanho do arquivo em ordem alfabética.
     * <p>
     * O método utiliza um comparador baseado no atributo {@code name} da classe Midia,
     * realizando a ordenação diretamente na lista recebida. Após a ordenação, a própria lista
     * (já modificada) é retornada.
     * </p
     *
     * @param listaMidias -  Lista de mídias que será ordenada pelo nome.
     * @return A lista de mídias ordenada em ordem alfabética crescente.
     */
    public List<Midia> ordenarMidiasPorNome(List<Midia> listaMidias) {
        listaMidias.sort(Comparator.comparing(Midia::getNome));
        return listaMidias;
    }

    /**
     * Ordena uma lista de mídias pelo tamanho do arquivo em ordem crescente.
     * <p>
     * O método utiliza um comparador baseado no atributo {@code name} da classe Midia,
     * realizando a ordenação diretamente na lista recebida. Após a ordenação, a própria lista
     * (já modificada) é retornada.
     * </p
     *
     * @param listaMidias -  Lista de mídias que será ordenada pelo tamanho do arquivo.
     * @return A lista de mídias ordenada em ordem crescente.
     */
    public List<Midia> ordenarMidiaPorTamanho(List<Midia> listaMidias) {
        listaMidias.sort(Comparator.comparing(Midia::getTamanho));
        return listaMidias;
    }

    public Salvamento getSalvamento() {
        return salvamento;
    }

    public void exploradorLimparPainelDireito() {
        homePage.limparPainelDireito();
    }
}
