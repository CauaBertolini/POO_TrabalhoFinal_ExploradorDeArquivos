package controle;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Idioma;
import modelo.Salvamento;
import modelo.Genero;
import modelo.midias.Filme;
import modelo.midias.Midia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ExploradorDeArquivos {
        Salvamento sv = new Salvamento();

        Genero gen1;

        public boolean criarNovaMidia() {

            // coleta parâmetros via JavaSwing
            String nome = "Vídeo X";
            String local = BuscadorDeCaminho.getCaminhoAreaDeTrabalho();
            float tamanho = 43.5f;
            double duracao = 32.3;
            Idioma idioma = new Idioma();
            Genero gen1 = new Genero("Romance", ETipoGenero.CINEMA);

            String caminhoAbsoluto = local + nome + ".tpoo";

            Midia novaMidia;

            //Com base no tipo selecionado, segue-se para a parte respectiva.

            try {
                novaMidia = new Filme(caminhoAbsoluto, nome, tamanho, duracao, ETipoArquivo.MP4, gen1 ,idioma );
                sv.incluirMidia(novaMidia);

                File f = new File(caminhoAbsoluto);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(novaMidia.toString());

            } catch (Exception ex) {

            }

            return true;
        }

<<<<<<< Updated upstream
=======
        /*
         * Novo Livro ou Musica */
        public boolean criarNovaMidia(String caminho, String nome, float tamanho, double duracao, Genero genero, String autorOuArtista, boolean eLivro) {
                String caminhoCompleto = nome + ".tpoo";
                Midia novaMidia;
                try {
                    if (eLivro) {
                        novaMidia = new Livro(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero, autorOuArtista);
                    } else {
                        novaMidia = new Musica(caminhoCompleto, nome, tamanho, duracao,  ETipoArquivo.MP4, genero, autorOuArtista);
                    }

                    sv.incluirMidia(novaMidia);

                    File f = new File(caminhoCompleto);
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(novaMidia.toString());

                    oos.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    return false;
                }
                return true;
        }


        public boolean excluirMidia(Midia midia) {
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

        public boolean alterarMidia(String caminhoArquivo, float tamanho, double duracao, Idioma idioma) throws ArquivoNaoExisteExcecao, CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao {
            try {
                if (Utilitario.arquivoExiste(caminhoArquivo)) {
                    Midia midiaAlterando;
                    Filme filmeAlterando = null;

                    try (FileInputStream fis = new FileInputStream(caminhoArquivo);
                         ObjectInputStream ois = new ObjectInputStream(fis)) {

                        midiaAlterando = (Midia) ois.readObject();
                    }

                    if (midiaAlterando instanceof Filme) {
                        filmeAlterando =  (Filme) midiaAlterando;
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

        public boolean alterarMidia(String caminho, float tamanho, double duracao, String autorOuArtista, boolean eLivro) {


            return false;
        }

>>>>>>> Stashed changes



}
