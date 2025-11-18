package controle;

import enumerador.*;
import excecao.*;
import modelo.*;
import modelo.Midias.Filme;
import modelo.Midias.Livro;
import modelo.Midias.Musica;
import util.ExcecaoUtil;
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
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo, Genero genero, Idioma idioma) {

        String caminhoCompleto = Paths.get(caminho, nome + ".tpoo").toString();
        System.out.println("Caminho: " + caminhoCompleto);
        modelo.Midias.Midia novaMidia = null;

        System.out.println(">>> Caminho recebido: " + caminho);

        try {
            novaMidia = new Filme(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, idioma);
        } catch (CampoVazioOuNuloExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(), "Campo vazio", JOptionPane.WARNING_MESSAGE);
        } catch (CampoMenorOuIgualAZeroExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(),  "Campo menor ou igual a zero", JOptionPane.WARNING_MESSAGE);
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
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar midia.\n" + e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(">>> Caminho completo utilizado na mídia: " + novaMidia.getCaminho());
            homePage.atualizarTabela();//adicionei isso para atualizar a tabela de midias na home page
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    // ==============================================
    // CRIAÇÃO DE MÍDIA — LIVRO OU MÚSICA
    // ==============================================
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo,
                                  Genero genero, String autorOuArtista, boolean eLivro) throws CampoMenorOuIgualAZeroExcecao, CampoVazioOuNuloExcecao {
        String caminhoCompleto = Paths.get(caminho, nome + ".tpoo").toString();
        modelo.Midias.Midia novaMidia = null;

        try {
            novaMidia = eLivro
                    ? new Livro(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, autorOuArtista)
                    : new Musica(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, autorOuArtista);
        } catch (CampoVazioOuNuloExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(), "Campo vazio", JOptionPane.WARNING_MESSAGE);
        } catch (CampoMenorOuIgualAZeroExcecao excecao) {
            JOptionPane.showMessageDialog(null, excecao.getMessage(),  "Campo menor ou igual a zero", JOptionPane.WARNING_MESSAGE);
        }

        File arquivoNovo = new File(caminhoCompleto);

        try {
            if (arquivoNovo.exists() && !confirmarSubstituicaoArquivo()) {
                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                return false;
            }

            salvamento.incluirMidia(novaMidia);
            homePage.atualizarTabela();
            try {
                SerializadorTpoo.salvarMidia(novaMidia);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar mídia.\n" + e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao salvar mídia.\n" + e.getMessage(),  JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // ==============================================
    // ALTERAÇÃO DE MÍDIAS
    // ==============================================
    public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao, Genero genero,
                                ETipoArquivo eTipoArquivo, Idioma idioma)
            throws ArquivoNaoExisteExcecao {

        try {
            if (!ExcecaoUtil.arquivoExiste(caminhoArquivo)) {
                throw new ArquivoNaoExisteExcecao();
            }

            File arquivo = new File(caminhoArquivo);
            modelo.Midias.Midia midiaAlterando = SerializadorTpoo.carregarMidia(arquivo);

            if (!(midiaAlterando instanceof Filme filmeAlterando)) {
                return false; // não é um filme, ignora
            }

            filmeAlterando.setGenero(genero);
            filmeAlterando.setTamanho(tamanho);
            filmeAlterando.setDuracao(duracao);
            filmeAlterando.setTipoArquivo(eTipoArquivo);
            filmeAlterando.setIdioma(idioma);

            SerializadorTpoo.salvarMidia(filmeAlterando);
            salvamento.atualizarMidia(filmeAlterando);
            homePage.atualizarTabela();
            return true;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + e.getMessage(), e);
        }
    }


    public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao, Genero genero,
                                ETipoArquivo eTipoArquivo, String autorOuArtista, boolean eLivro)
            throws ArquivoNaoExisteExcecao {

        try {
            if (!ExcecaoUtil.arquivoExiste(caminhoArquivo)) {
                throw new ArquivoNaoExisteExcecao();
            }

            File arquivo = new File(caminhoArquivo);
            modelo.Midias.Midia midiaAlterando = SerializadorTpoo.carregarMidia(arquivo);

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

            } else if (!eLivro && midiaAlterando instanceof modelo.Midias.Musica musicaAlterando) {
                musicaAlterando.setGenero(genero);
                musicaAlterando.setTamanho(tamanho);
                musicaAlterando.setDuracao(duracao);
                musicaAlterando.setArtista(autorOuArtista);
                musicaAlterando.setTipoArquivo(eTipoArquivo);

                SerializadorTpoo.salvarMidia(musicaAlterando);
                salvamento.atualizarMidia(musicaAlterando);
                homePage.atualizarTabela();
                return true;
            }

            return false; // tipo incompatível

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o arquivo: " + e.getMessage(), e);
        }
    }

    // ==============================================
    // EXCLUIR
    // ==============================================
    public boolean excluirMidia(modelo.Midias.Midia midia) {
        try {
            Path caminho = Paths.get(midia.getCaminho());
            Files.deleteIfExists(caminho);
            salvamento.removerMidia(midia);
            homePage.atualizarTabela();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir mídia:\n" + e.getMessage(), "Erro na exclusão",  JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // ==============================================
    // RENOMEAR
    // ==============================================
    public boolean renomearMidia(modelo.Midias.Midia midia, String novoNomeString) throws ArquivoNaoExisteExcecao {
        if (ExcecaoUtil.arquivoExiste(midia.getCaminho())) {
            File arquivo = new File(midia.getCaminho());
            File novoNome = new File(arquivo.getParent(), novoNomeString + ".tpoo");

            if (arquivo.renameTo(novoNome)) {
                midia.setCaminho(novoNome.getAbsolutePath());
                homePage.atualizarTabela();
                return true;
            }
        }
        return false;
    }

    // ==============================================
    // MOVER
    // ==============================================
    public boolean moverMidia(modelo.Midias.Midia midia, String novoCaminhoString)
            throws ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao {
        if (ExcecaoUtil.arquivoExiste(midia.getCaminho())) {
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

    // ==============================================
    // CARREGAR ARQUIVO
    // ==============================================
    public boolean carregarArquivo(String caminho) throws IOException {
        File arquivo = new File(caminho);
        modelo.Midias.Midia novaMidia = SerializadorTpoo.carregarMidia(arquivo);
        salvamento.incluirMidia(novaMidia);
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
    public List<modelo.Midias.Midia> filtrarMidias (String generoFiltrar, String eTipoArquivo){
        List<modelo.Midias.Midia> filtradas = new ArrayList<>();

        for (modelo.Midias.Midia m : salvamento.getMidias()) {
            if (generoFiltrar.equalsIgnoreCase("TODOS") && m.getTipoArquivo().name().equalsIgnoreCase("TODOS")) {
                return salvamento.getMidias();
            } else if (m.getGenero().getNome().equalsIgnoreCase(generoFiltrar) && m.getTipoArquivo().name().equalsIgnoreCase("TODOS")) {
                filtradas.add(m);
            } else if (generoFiltrar.equalsIgnoreCase("TODOS") && m.getTipoArquivo().name().equalsIgnoreCase(eTipoArquivo)) {
                filtradas.add(m);
            } else if (m.getGenero().getNome().equals(generoFiltrar) && m.getTipoArquivo().name().equalsIgnoreCase(eTipoArquivo)) {
                filtradas.add(m);
            }
        }
        return filtradas;
    }

    public List<modelo.Midias.Midia> ordenarMidiasPorNome (List <modelo.Midias.Midia> listaMidias) {
        listaMidias.sort(Comparator.comparing(modelo.Midias.Midia::getNome));
        return listaMidias;
    }

    public List<modelo.Midias.Midia> ordenarMidiaPorTamanho (List <modelo.Midias.Midia> listaMidias) {
        // O comparing chama o getTamanho() e use o valor retornado para comparar
        listaMidias.sort(Comparator.comparing(modelo.Midias.Midia::getTamanho));
        return listaMidias;
    }

    public Salvamento getSalvamento() {
        return salvamento;
    }
}
