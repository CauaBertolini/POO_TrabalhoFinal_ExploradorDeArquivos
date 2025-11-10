package controle;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Idioma;
import modelo.ListaGenero;
import modelo.Salvamento;
import modelo.Genero;
import modelo.midias.Filme;
import modelo.midias.Midia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutionException;

public class ExploradorDeArquivos {
        Salvamento sv = new Salvamento();

        ListaGenero generos = new ListaGenero();

        public boolean criarNovaMidia(String nome, String caminho, float tamanho, double duracao, Idioma idioma, Genero genero) {

            String caminhoCompleto = nome + ".tpoo";
            Midia novaMidia;

            try {

                novaMidia = new Filme(caminhoCompleto, nome, tamanho, duracao, ETipoArquivo.MP4, genero ,idioma );
                sv.incluirMidia(novaMidia);

                File f = new File(caminhoCompleto);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(novaMidia);

            } catch (Exception ex) {

            }
            return true;
        }

        public boolean excluirMidia(Midia midia) {
            try {
                File file = new File(midia.getCaminho());
                if (file.exists()) {
                    file.delete();
                }
                sv.removerMidia(midia);


            } catch (Exception e) {

            }
            return true;
        }




}
