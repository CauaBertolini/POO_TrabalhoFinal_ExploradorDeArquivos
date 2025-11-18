package controle;

import enumerador.*;
import excecao.*;
import modelo.*;
import modelo.midias.Filme;
import modelo.midias.Livro;
import modelo.midias.Midia;
import modelo.midias.Musica;
import util.ExcecaoUtil;
import util.JOptionPaneUtil;
import visao.HomePage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ExploradorDeArquivos {
    private Salvamento salvamento;
    private Listas listas = new Listas();
    private HomePage homePage;
    SerializadorTpoo serializadorTpoo = new SerializadorTpoo();

    public ExploradorDeArquivos(HomePage homePage) {
        salvamento = new Salvamento();
        this.homePage = homePage;
    }
    // ==============================================
    // CRIAÇÃO DE MÍDIA — FILME
    // ==============================================
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
            homePage.limparPainelDireito();
            homePage.atualizarTabela();//adicionei isso para atualizar a tabela de midias na home page
            return true;
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro(excecao.getMessage());
            return false;
        }
    }

    // ==============================================
    // CRIAÇÃO DE MÍDIA — LIVRO OU MÚSICA
    // ==============================================
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

            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;
        } catch (Exception excecao) {
           JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia:\n" + excecao.getMessage());
            return false;
        }
    }

    // ==============================================
    // ALTERAÇÃO DE MÍDIAS
    // ==============================================
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

            homePage.atualizarTabela();
            return true;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + e.getMessage(), e);
        }
    }


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

                homePage.limparPainelDireito();
                homePage.atualizarTabela();
                return true;
            }

            return false; // tipo incompatível

        } catch (IOException excecao) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + excecao.getMessage(), excecao);
        }
    }

    // ==============================================
    // EXCLUIR
    // ==============================================
    public boolean excluirMidia(Midia midia) {
        try {
            Path caminho = Paths.get(midia.getCaminho());
            Files.deleteIfExists(caminho);
            salvamento.removerMidia(midia);

            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia:\n" + excecao.getMessage());
            return false;
        }
    }

    // ==============================================
    // RENOMEAR
    // ==============================================
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

            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;

        } catch (IOException e) {
            return false;
        }
    }


    // ==============================================
    // MOVER
    // ==============================================
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
            homePage.limparPainelDireito();
            homePage.atualizarTabela();
            return true;
        }
        return false;
    }

    // ==============================================
    // CARREGAR ARQUIVO
    // ==============================================
    public boolean carregarArquivo(String caminho) throws IOException {
        File arquivo = new File(caminho);
        modelo.midias.Midia novaMidia = SerializadorTpoo.carregarMidia(arquivo);
        salvamento.incluirMidia(novaMidia);
        homePage.limparPainelDireito();
        homePage.atualizarTabela();
        return false;
    }

    // ==============================================
    // SELETORES DE ARQUIVO E DIRETÓRIO
    // ==============================================
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

    public String abrirSeletorDeDiretorio() {
        JFileChooser seletorDiretorio = new JFileChooser();
        seletorDiretorio.setDialogTitle("Selecione uma pasta");
        seletorDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (seletorDiretorio.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return seletorDiretorio.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    // ==============================================
    // CONFIRMAÇÃO DE SOBRESCRITA
    // ==============================================
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

    // ===============================================
    // FILTROS DE MÍDIAS
    // ===============================================
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

    public List<modelo.midias.Midia> ordenarMidiasPorNome (List <modelo.midias.Midia> listaMidias) {
        listaMidias.sort(Comparator.comparing(modelo.midias.Midia::getNome));
        return listaMidias;
    }

    public List<modelo.midias.Midia> ordenarMidiaPorTamanho (List <modelo.midias.Midia> listaMidias) {
        // O comparing chama o getTamanho() e use o valor retornado para comparar
        listaMidias.sort(Comparator.comparing(modelo.midias.Midia::getTamanho));
        return listaMidias;
    }

    public Salvamento getSalvamento() {
        return salvamento;
    }

    public void exploradorLimparPainelDireito() {
        homePage.limparPainelDireito();
    }
}
