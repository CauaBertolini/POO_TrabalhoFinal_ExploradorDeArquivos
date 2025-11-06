package controle;

import modelo.Salvamento;
import modelo.generos.Genero;
import modelo.generos.GeneroCinema;
import modelo.midias.Filme;
import modelo.midias.Midia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ExploradorDeArquivos {
        Salvamento sv = new Salvamento();

        Genero gen1 = new GeneroCinema("Romance");

        public boolean criarNovaMidia() {
            // coleta parâmetros via JavaSwing
            String nome = "Vídeo X";
            float tamanho = 43.5f;
            String local = BuscadorDeCaminho.getCaminhoAreaDeTrabalho();
            double duracao = 32.3;

            String caminhoAbsoluto = local + nome + ".tpoo";

            Midia novaMidia;

            //Com base no tipo selecionado, segue-se para a parte respectiva.

            try {
                novaMidia = new Filme(caminhoAbsoluto, nome, tamanho, duracao);
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
