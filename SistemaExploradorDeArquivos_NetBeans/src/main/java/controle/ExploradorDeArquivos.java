package controle;

import enumeradores.ETipoArquivo;
import excecao.ArquivoNaoExisteExcecao;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import excecao.CampoMenorOuIgualAZeroExcecao;
import modelo.midias.Livro;
import modelo.midias.Filme;
import modelo.midias.Musica;
import modelo.midias.Midia;
import excecao.*;
import modelo.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ExploradorDeArquivos {
    private Salvamento sv = new Salvamento();
    private Listas listas = new Listas();

    SerializadorTpoo serializadorTpoo = new SerializadorTpoo();

    // ==============================================
    // CRIAÇÃO DE MÍDIA — FILME
    // ==============================================
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo, Genero genero, Idioma idioma) {

        String caminhoCompleto = caminho + File.separator + nome + ".tpoo";
        Midia novaMidia = null;

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

            sv.incluirMidia(novaMidia);

            try {
                 SerializadorTpoo.salvarMidia(novaMidia);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar midia.\n" + e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    // ==============================================
    // CRIAÇÃO DE MÍDIA — LIVRO OU MÚSICA
    // ==============================================
    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao,
                                  Genero genero, String autorOuArtista, boolean eLivro) throws CampoMenorOuIgualAZeroExcecao, CampoVazioOuNuloExcecao {
        String caminhoCompleto = caminho + File.separator + nome + ".tpoo";
        Midia novaMidia = null;

        try {
            novaMidia = eLivro
                    ? new Livro(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista)
                    : new Musica(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
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

            sv.incluirMidia(novaMidia);

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
    // EXCLUIR
    // ==============================================
    public boolean excluirMidia(Midia midia) {
        try {
            Path caminho = Paths.get(midia.getCaminho());
            Files.deleteIfExists(caminho);
            sv.removerMidia(midia);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir mídia:\n" + e.getMessage(), "Erro na exclusão",  JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // ==============================================
    // RENOMEAR
    // ==============================================
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

    // ==============================================
    // MOVER
    // ==============================================
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

    // ==============================================
    // CARREGAR ARQUIVO
    // ==============================================
    public boolean carregarArquivo(String caminho) throws IOException {
        File arquivo = new File(caminho);
        Midia novaMidia = SerializadorTpoo.carregarMidia(arquivo);
        sv.incluirMidia(novaMidia);
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
    public List<Midia> filtrarMidias (String generoFiltrar, ETipoArquivo eTipoArquivo){
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

    public List<Midia> ordenarMidiasPorNome (List < Midia > listaMidias) {
        listaMidias.sort(Comparator.comparing(Midia::getNome));
        return listaMidias;
    }

    public List<Midia> ordenarMidiaPorTamanho (List < Midia > listaMidias) {
        // O comparing chama o getTamanho() e use o valor retornado para comparar
        listaMidias.sort(Comparator.comparing(Midia::getTamanho));
        return listaMidias;
    }
}
