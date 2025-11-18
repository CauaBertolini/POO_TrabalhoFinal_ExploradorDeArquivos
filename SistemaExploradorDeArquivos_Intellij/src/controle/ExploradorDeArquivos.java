package controle;

import enumerador.*;
import excecao.*;
import modelo.*;
import modelo.midias.*;

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
    private Salvamento sv = new Salvamento();
    private Listas listas = new Listas();

    SerializadorTpoo serializadorTpoo = new SerializadorTpoo();

    /**
     * Cria uma nova mídia do tipo Filme
     * <p>
     * Ele monta o caminho completo do arquivo, instancia a mídia, trata possíveis
     * exceções de validação, confirma substituição caso o arquivo já exista,
     * inclui a mídia no sistema e realiza a serialização para disco.
     *
     * @param caminho     - Caminho do usuário onde o arquivo vai ser salvo.
     * @param nome        - Nome da Mídia a ser criada.
     * @param tamanho     - Tamanho do arquivo em bytes.
     * @param duracao     - Duração do filme em minutos.
     * @param tipoArquivo - O tipo de arquivo de acordo com o tipo de Mídia criada. No caso de filme, MKV ou MP4.
     * @param genero      - Gênero da Mídia para tipo cinema.
     * @param idioma      - Idioma principal do filme criado.
     * @return O método retorna {@code true} caso o objeto tenha sido criado com sucesso e retorna {@code false} se a ação for
     * cancelada ou ocorrerem erros
     */
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo, Genero genero, Idioma idioma) {

        String caminhoCompleto = Paths.get(caminho, nome + ".tpoo").toString();
        System.out.println("Caminho: " + caminhoCompleto);
        Midia novaMidia = null;

        System.out.println(">>> Caminho recebido: " + caminho);

        try {
            novaMidia = new Filme(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, idioma);
        } catch (CampoVazioOuNuloExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(), "Campo vazio", JOptionPane.WARNING_MESSAGE);
        } catch (CampoMenorOuIgualAZeroExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(), "Campo menor ou igual a zero", JOptionPane.WARNING_MESSAGE);
        }

        File arquivoNovo = new File(caminhoCompleto);

        try {
            if (arquivoNovo.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                return false;
            }

            sv.incluirMidia(novaMidia);
            try {
                SerializadorTpoo.salvarMidia(novaMidia);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar midia.\n" + e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(">>> Caminho completo utilizado na mídia: " + novaMidia.getCaminho());

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
     */
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao,
                                  Genero genero, String autorOuArtista, boolean eLivro) throws CampoMenorOuIgualAZeroExcecao, CampoVazioOuNuloExcecao {
        String caminhoCompleto = Paths.get(caminho, nome + ".tpoo").toString();
        Midia novaMidia = null;

        try {
            novaMidia = eLivro
                    ? new Livro(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista)
                    : new Musica(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
        } catch (CampoVazioOuNuloExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(), "Campo vazio", JOptionPane.WARNING_MESSAGE);
        } catch (CampoMenorOuIgualAZeroExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(), "Campo menor ou igual a zero", JOptionPane.WARNING_MESSAGE);
        }

        File arquivoNovo = new File(caminhoCompleto);

        try {
            if (arquivoNovo.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                return false;
            }

            sv.incluirMidia(novaMidia);

            try {
                SerializadorTpoo.salvarMidia(novaMidia);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar mídia.\n" + e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao salvar mídia.\n" + e.getMessage(), JOptionPane.WARNING_MESSAGE);
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
     * @param eTipoArquivo   - O tipo do arquivo criado.
     * @param idioma         - Principal idioma do filme.
     * @return Se a mídia for alterada com sucesso é retornado {@code true}. E se a Mídia não for Filme ou
     * houver algum erro é retornado {@code false}
     * retornado {@code false}
     * @throws ArquivoNaoExisteExcecao - Se o arquivo informado não existir no caminho especificado.
     */
    public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao,
                                ETipoArquivo eTipoArquivo, Idioma idioma)
            throws ArquivoNaoExisteExcecao {

        try {
            if (!Utilitario.arquivoExiste(caminhoArquivo)) {
                throw new ArquivoNaoExisteExcecao();
            }

            File arquivo = new File(caminhoArquivo);
            Midia midiaAlterando = SerializadorTpoo.carregarMidia(arquivo);

            if (!(midiaAlterando instanceof Filme filmeAlterando)) {
                return false; // não é um filme, ignora
            }

            filmeAlterando.setTamanho(tamanho);
            filmeAlterando.setDuracao(duracao);
            filmeAlterando.setTipoArquivo(eTipoArquivo);
            filmeAlterando.setIdioma(idioma);

            SerializadorTpoo.salvarMidia(filmeAlterando);
            sv.atualizarMidia(filmeAlterando);

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
     * @param eTipoArquivo   - Novo tipo de arquivo da mídia.
     * @param autorOuArtista - Novo autor ou artista da Música ou Livro.
     * @param eLivro         -Define o tipo da mídia a ser criada: {@code true} para Livro e {@code false} para Música.
     * @return Se a mídia for alterada com sucesso é retornada {@code true}, caso a Mídia não for Música ou Filme
     * ou houver algum erro, é retornado {@code false}
     * @throws ArquivoNaoExisteExcecao - Se o arquivo informado não existir no caminho especificado.
     */
    public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao,
                                ETipoArquivo eTipoArquivo, String autorOuArtista, boolean eLivro)
            throws ArquivoNaoExisteExcecao {

        try {
            if (!Utilitario.arquivoExiste(caminhoArquivo)) {
                throw new ArquivoNaoExisteExcecao();
            }

            File arquivo = new File(caminhoArquivo);
            Midia midiaAlterando = SerializadorTpoo.carregarMidia(arquivo);

            if (eLivro && midiaAlterando instanceof Livro livroAlterando) {
                livroAlterando.setTamanho(tamanho);
                livroAlterando.setDuracao(duracao);
                livroAlterando.setAutor(autorOuArtista);
                livroAlterando.setTipoArquivo(eTipoArquivo);

                SerializadorTpoo.salvarMidia(livroAlterando);
                sv.atualizarMidia(livroAlterando);
                return true;

            } else if (!eLivro && midiaAlterando instanceof Musica musicaAlterando) {
                musicaAlterando.setTamanho(tamanho);
                musicaAlterando.setDuracao(duracao);
                musicaAlterando.setArtista(autorOuArtista);
                musicaAlterando.setTipoArquivo(eTipoArquivo);

                SerializadorTpoo.salvarMidia(musicaAlterando);
                sv.atualizarMidia(musicaAlterando);
                return true;
            }

            return false; // tipo incompatível

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + e.getMessage(), e);
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
            sv.removerMidia(midia);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir mídia:\n" + e.getMessage(), "Erro na exclusão", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    /**
     * Renomeia uma mídia, alterando também o caminho do arquivo no sistema.
     * <p>
     * O método verifica a existência do arquivo, constrói o novo caminho com o nome atualizado
     * e executa a operação de renomeação no sistema de arquivos.
     *
     * @param midia           - Mídia que será renomeada.
     * @param novoNomeString  - Novo nome que o arquivo deverá receber.
     * @return O método retorna {@code true} se o arquivo for renomeado com sucesso; {@code false} caso contrário.
     * @throws ArquivoNaoExisteExcecao se o arquivo original não existir.
     */
    public boolean renomearMidia(Midia midia, String novoNomeString) throws ArquivoNaoExisteExcecao {
        if (Utilitario.arquivoExiste(midia.getCaminho())) {
            File arquivo = new File(midia.getCaminho());
            File novoNome = new File(arquivo.getParent(), novoNomeString + ".tpoo");

            if (arquivo.renameTo(novoNome)) {
                midia.setCaminho(novoNome.getAbsolutePath());
                return true;
            }
        }
        return false;
    }

    /**
     * Move a mídia para um novo diretório, atualizando também o caminho salvo no objeto.
     * <p>
     * O método verifica se o arquivo original existe, monta o novo destino com base no
     * diretório informado e no nome atual da mídia, confirma substituição caso o arquivo
     * já exista no destino e, por fim, realiza a operação de movimentação.
     *
     * @param midia              - Mídia cujo arquivo será movido.
     * @param novoCaminhoString  - Caminho do diretório onde o arquivo deverá ser movido.
     * @return {@code true} se o arquivo for movido com sucesso; {@code false} caso a operação
     *         seja cancelada ou não possa ser concluída.
     * @throws ArquivoNaoExisteExcecao  Se o arquivo original não existir.
     * @throws CampoVazioOuNuloExcecao Se o novo caminho informado for vazio ou nulo.
     */
    public boolean moverMidia(Midia midia, String novoCaminhoString)
            throws ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao {
        if (Utilitario.arquivoExiste(midia.getCaminho())) {
            File arquivo = new File(midia.getCaminho());
            File novoCaminho = new File(novoCaminhoString, midia.getNome() + ".tpoo");

            if (novoCaminho.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                return false;
            }

            arquivo.renameTo(novoCaminho);
            midia.setCaminho(novoCaminho.getAbsolutePath());
            return true;
        }
        return false;
    }

    /**
     * Carrega um arquivo de mídia a partir do caminho informado, desserializa o objeto
     * e o inclui no serviço de mídias.
     *
     * @param caminho o caminho completo do arquivo a ser carregado.
     * @return sempre retorna {@code false}. (Considere ajustar se esse retorno
     *         tiver outro propósito no futuro.)
     * @throws IOException se ocorrer um erro ao acessar ou ler o arquivo.
     */
    public boolean carregarArquivo(String caminho) throws IOException {
        File arquivo = new File(caminho);
        Midia novaMidia = SerializadorTpoo.carregarMidia(arquivo);
        sv.incluirMidia(novaMidia);
        return false;
    }

    /**
     * Abre um seletor de arquivos configurado para escolher exclusivamente arquivos
     * com extensão <code>.tpoo</code>. Caso o usuário selecione um arquivo,
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
     * Abre um seletor de arquivos configurado para permitir apenas a escolha
     * de arquivos com extensão <code>.tpoo</code>.
     * <p>
     * Caso o usuário selecione um arquivo, o caminho absoluto dele é retornado.
     * Caso o usuário cancele a operação, o método retorna {@code null}.
     * </p>
     *
     * @return o caminho absoluto do arquivo selecionado, ou {@code null} caso a seleção seja cancelada.
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
     * confirme a operação, o método retorna {@code true}; caso ele recuse ou feche o diálogo,
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
     * Filtra a lista de mídias cadastradas de acordo com o gênero e o tipo de arquivo informados.
     * <p>
     * O método percorre todas as mídias disponíveis no sistema e aplica as seguintes regras:
     * </p>
     * <ul>
     *     <li>Se o gênero pesquisado for {@code "TODOS"} e o tipo de arquivo da mídia for
     *     {@link ETipoArquivo#TODOS}, todas as mídias são retornadas imediatamente.</li>
     *     <li>Se o gênero da mídia coincidir com o gênero filtrado e o tipo de arquivo for
     *     {@link ETipoArquivo#TODOS}, a mídia é adicionada ao resultado.</li>
     *     <li>Se o gênero filtrado for {@code "TODOS"} e o tipo de arquivo coincidir com o informado,
     *     a mídia é adicionada ao resultado.</li>
     *     <li>Se tanto o gênero quanto o tipo de arquivo coincidirem exatamente com os parâmetros,
     *     a mídia é adicionada ao resultado.</li>
     * </ul>
     *
     * @param generoFiltrar Gênero desejado para filtragem (ou {@code "TODOS"} para ignorar o gênero).
     * @param eTipoArquivo  Tipo de arquivo desejado para filtragem.
     * @return Uma lista contendo apenas as mídias que atendem aos critérios definidos.
     */
    public List<Midia> filtrarMidias(String generoFiltrar, ETipoArquivo eTipoArquivo) {
        List<Midia> filtradas = new ArrayList<>();

        for (Midia m : sv.getMidias()) {
            if (generoFiltrar.equalsIgnoreCase("TODOS") && m.getTipoArquivo() == ETipoArquivo.TODOS) {
                return sv.getMidias();
            } else if (m.getGenero().getNome().equalsIgnoreCase(generoFiltrar) && m.getTipoArquivo() == ETipoArquivo.TODOS) {
                filtradas.add(m);
            } else if (generoFiltrar.equalsIgnoreCase("TODOS") && m.getTipoArquivo() == eTipoArquivo) {
                filtradas.add(m);
            } else if (m.getGenero().getNome().equals(generoFiltrar) && m.getTipoArquivo() == eTipoArquivo) {
                filtradas.add(m);
            }
        }
        return filtradas;
    }

    /**
     * Ordena uma lista de mídias pelo nome em ordem alfabética.
     * <p>
     * O método utiliza um comparador baseado no atributo {@code nome} da classe {@link Midia},
     * realizando a ordenação diretamente na lista recebida. Após a ordenação, a própria lista
     * (agora modificada) é retornada.
     * </p>
     *
     * @param listaMidias Lista de mídias que será ordenada pelo nome.
     * @return A lista de mídias ordenada em ordem alfabética crescente.
     */
    public List<Midia> ordenarMidiasPorNome(List<Midia> listaMidias) {
        listaMidias.sort(Comparator.comparing(Midia::getNome));
        return listaMidias;
    }

    /**
     * Ordena uma lista de mídias pelo tamanho do arquivo em ordem crescente.
     * <p>
     * O método utiliza um comparador baseado no atributo {@code tamanho} da classe {@link Midia},
     * realizando a ordenação diretamente na lista recebida. Após a ordenação, a própria lista
     * (já modificada) é retornada.
     * </p>
     *
     * @param listaMidias Lista de mídias que será ordenada pelo tamanho do arquivo.
     * @return A lista de mídias ordenada em ordem crescente de tamanho.
     */
    public List<Midia> ordenarMidiaPorTamanho(List<Midia> listaMidias) {
        listaMidias.sort(Comparator.comparing(Midia::getTamanho));
        return listaMidias;
    }
}
