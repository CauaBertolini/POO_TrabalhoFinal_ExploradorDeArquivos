package controle;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A classe BuscadorDeCaminho contém um método utilitário responsável
 * por localizar o caminho da área de trabalho (Desktop) do usuário atual.
 */
public class BuscadorDeCaminho {

    /**
     * O método utiliza System.getProperty("user.home") para recuperar o
     * diretório raiz do usuário. Em seguida, utiliza
     * Paths.get() junto ao File.separator para montar o caminho correto
     * até a pasta Desktop, garantindo compatibilidade com diferentes SOs.
     *
     * @return uma String contendo o caminho completo até a área de trabalho.
     */
    public static String getCaminhoAreaDeTrabalho() {
        String home = System.getProperty("user.home");
        return Paths.get(home, "Desktop").toString() + File.separator;
    }
}
