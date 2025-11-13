package controle;

import enumerador.*;
import excecao.*;
import modelo.*;
import modelo.midias.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ExploradorDeArquivos {
    Salvamento sv = new Salvamento();

    Listas generos = new Listas();

    public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, ETipoArquivo tipoArquivo, Genero genero, Idioma idioma) {

        String caminhoCompleto = nome + ".tpoo";
        Midia novaMidia;
        File arquivoNovo = new File(caminhoCompleto);

        if (arquivoNovo.exists()) {
            if (confirmarSubstituicaoArquivo()) {
                try {
                    novaMidia = new Filme(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, idioma);
                    sv.incluirMidia(novaMidia);

                    FileOutputStream fos = new FileOutputStream(arquivoNovo);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(novaMidia.toString());

                    oos.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                return false;
            }
        } else {
            try {
                novaMidia = new Filme(caminhoCompleto, nome, tamanho, duracao, tipoArquivo, genero, idioma);
                sv.incluirMidia(novaMidia);

                FileOutputStream fos = new FileOutputStream(arquivoNovo);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(novaMidia.toString());
            }

            public boolean criarNovaMidia (String caminho, String nome,float tamanho, double duracao, Genero
            genero, String autorOuArtista,boolean eLivro){

                String caminhoCompleto = nome + ".tpoo";
                Midia novaMidia;

                try {
                    if (eLivro) {
                        novaMidia = new Livro(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                    } else {
                        novaMidia = new Musica(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                    }
                }
                return true;
            }

            public boolean criarNovaMidia (String caminho, String nome,float tamanho, double duracao, Genero
            genero, String autorOuArtista,boolean eLivro){

                String caminhoCompleto = nome + ".tpoo";
                Midia novaMidia;

                File arquivoNovo = new File(caminhoCompleto);

                if (arquivoNovo.exists()) {
                    if (confirmarSubstituicaoArquivo()) {
                        try {
                            if (eLivro) {
                                novaMidia = new Livro(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                            } else {
                                novaMidia = new Musica(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                            }

                            sv.incluirMidia(novaMidia);

                            File f = new File(caminhoCompleto);
                            FileOutputStream fos = new FileOutputStream(f);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(novaMidia);

                            oos.close();

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                            return false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ação cancelada.");
                        return false;
                    }
                } else {
                    try {
                        if (eLivro) {
                            novaMidia = new Livro(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                        } else {
                            novaMidia = new Musica(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                        }

                        if (tamanho > 0) {
                            filmeAlterando.setTamanho(tamanho);
                        }

                        if (duracao > 0) {
                            filmeAlterando.setDuracao(duracao);
                        }

                        if (idioma != null) {
                            filmeAlterando.setIdioma(idioma);
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        return false;
                    }
                }
                return true;
            }

            public boolean excluirMidia (Midia midia){
                try {
                    Path caminho = Paths.get(midia.getCaminho());
                    Files.deleteIfExists(caminho);

                    sv.removerMidia(midia);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    return false;
                }
                return true;
            }

            public boolean alterarMidia (String caminhoArquivo,float tamanho, double duracao, ETipoArquivo
            eTipoArquivo, Idioma idioma) throws
            ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao {
                try {
                    if (Utilitario.arquivoExiste(caminhoArquivo)) {
                        Midia midiaAlterando;
                        Filme filmeAlterando = null;

                        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
                             ObjectInputStream ois = new ObjectInputStream(fis)) {

                            midiaAlterando = (Midia) ois.readObject();
                        }

                        if (midiaAlterando instanceof Filme) {
                            filmeAlterando = (Filme) midiaAlterando;
                        } else {
                            return false;
                        }

                        if (tamanho > 0) {
                            filmeAlterando.setTamanho(tamanho);
                        }

                        if (duracao > 0) {
                            filmeAlterando.setDuracao(duracao);
                        }

                        if (idioma != null) {
                            filmeAlterando.setIdioma(idioma);
                        }

                        if (eTipoArquivo != null) {
                            filmeAlterando.setTipoArquivo(eTipoArquivo);
                        }

                        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
                             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                            oos.writeObject(midiaAlterando);
                        }

                        sv.atualizarMidia(midiaAlterando);

                        return true;
                    }
                } catch (ArquivoNaoExisteExcecao a) {
                    JOptionPane.showMessageDialog(null, a.getMessage());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    throw new RuntimeException(e);

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }

            public boolean alterarMidia (String caminhoArquivo,float tamanho, double duracao, ETipoArquivo
            eTipoArquivo, String autorOuArtista,boolean eLivro) throws
            ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao {
                try {
                    if (Utilitario.arquivoExiste(caminhoArquivo)) {

                        Midia midiaAlterando;

                        if (eLivro) {
                            Livro livroAlterando = null;

                            try (FileInputStream fis = new FileInputStream(caminhoArquivo);
                                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                                midiaAlterando = (Midia) ois.readObject();
                            }

                            if (midiaAlterando instanceof Livro) {
                                livroAlterando = (Livro) midiaAlterando;
                            } else {
                                return false;
                            }

                            if (tamanho > 0) {
                                livroAlterando.setTamanho(tamanho);
                            }

                            if (duracao > 0) {
                                livroAlterando.setDuracao(duracao);
                            }

                            if (!autorOuArtista.isBlank()) {
                                livroAlterando.setAutor(autorOuArtista);
                            }

                            if (eTipoArquivo != null) {
                                livroAlterando.setTipoArquivo(eTipoArquivo);
                            }

                        } else {
                            Musica musicaAlterando = null;
                            try (FileInputStream fis = new FileInputStream(caminhoArquivo);
                                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                                midiaAlterando = (Midia) ois.readObject();
                            }

                            if (midiaAlterando instanceof Musica) {
                                musicaAlterando = (Musica) midiaAlterando;
                            } else {
                                return false;
                            }

                            if (tamanho > 0) {
                                musicaAlterando.setTamanho(tamanho);
                            }

                            if (duracao > 0) {
                                musicaAlterando.setDuracao(duracao);
                            }

                            if (!autorOuArtista.isBlank()) {
                                musicaAlterando.setArtista(autorOuArtista);
                            }

                            if (eTipoArquivo != null) {
                                musicaAlterando.setTipoArquivo(eTipoArquivo);
                            }
                        }

                        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
                             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                            oos.writeObject(midiaAlterando);
                        }

                        sv.atualizarMidia(midiaAlterando);

                        return true;
                    }
                } catch (ArquivoNaoExisteExcecao a) {
                    JOptionPane.showMessageDialog(null, a.getMessage());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    throw new RuntimeException(e);

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }

            public boolean renomearMidia (Midia midia, String novoNomeString) throws ArquivoNaoExisteExcecao {

                if (Utilitario.arquivoExiste(midia.getCaminho())) {

                    File arquivo = new File(midia.getCaminho());

                    File novoNome = new File(arquivo.getParent(), novoNomeString + ".tpoo");

                    arquivo.renameTo(novoNome);

                    midia.setCaminho(novoNome.getAbsolutePath());

                    return true;
                }

                return false;

            }

            public boolean moverMidia (Midia midia, String novoCaminhoString) throws
            ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao {

                try {
                    if (Utilitario.arquivoExiste(midia.getCaminho())) {

                        File arquivo = new File(midia.getCaminho());

                        File novoCaminho = new File(novoCaminhoString, midia.getNome() + ".tpoo");

                        if (novoCaminho.exists()) {

                            if (confirmarSubstituicaoArquivo()) {

                                arquivo.renameTo(novoCaminho);
                                midia.setCaminho(novoCaminho.getAbsolutePath());

                            } else {
                                JOptionPane.showMessageDialog(null, "Ação cancelada.");
                                return false;
                            }
                        } else {
                            arquivo.renameTo(novoCaminho);
                            midia.setCaminho(novoCaminho.getAbsolutePath());
                            return true;
                        }

                    }
                } catch (ArquivoJaExisteExcecao sobreposicao) {

                }

                return false;

            }

            public String abrirSeletorDeArquivoTpoo () {
                FileNameExtensionFilter filtroExtensao = new FileNameExtensionFilter("Filtra arquivos permitindo apenas os lidos de forma padrão pelo sistema (tpoo)", "tpoo");

                JFileChooser seletorTpoo = new JFileChooser();

                seletorTpoo.setFileFilter(filtroExtensao);
                seletorTpoo.setDialogTitle("Selecione um arquivo");
                seletorTpoo.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if (seletorTpoo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = seletorTpoo.getSelectedFile();
                    return arquivoSelecionado.getAbsolutePath();
                }
                return null;
            }

            public String abrirSeletorDeDiretorio () {
                JFileChooser seletorDiretorio = new JFileChooser();

                seletorDiretorio.setDialogTitle("Selecione uma pasta onde o arquivo será alocado.");
                seletorDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                seletorDiretorio.setAcceptAllFileFilterUsed(false);

                if (seletorDiretorio.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File diretorioSelecionado = seletorDiretorio.getSelectedFile();
                    return diretorioSelecionado.getAbsolutePath();
                }
                return null;
            }

            public boolean confirmarSubstituicaoArquivo () {
                int resposta = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza que deseja substituir o arquivo destino pelo atual?",
                        "Confirmação de Sobescrita",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                return resposta == JOptionPane.YES_OPTION;
            }

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
    }
