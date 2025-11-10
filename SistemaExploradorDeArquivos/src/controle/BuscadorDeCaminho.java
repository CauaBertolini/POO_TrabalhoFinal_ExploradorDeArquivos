package controle;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BuscadorDeCaminho {

    public static String getCaminhoAreaDeTrabalho() {
        String caminhoHome = System.getProperty("user.home");
        Path caminhoDesktop = Paths.get(caminhoHome, "Desktop\\");
        return caminhoDesktop.toAbsolutePath().toString();
    }
}
