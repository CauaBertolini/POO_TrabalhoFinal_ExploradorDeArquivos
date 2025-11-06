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
                oos.writeObject(novaMidia);

            } catch (Exception ex) {

            }

            return true;
        }




}
