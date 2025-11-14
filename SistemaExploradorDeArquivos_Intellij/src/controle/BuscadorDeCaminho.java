package controle;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuscadorDeCaminho {

    public static String getCaminhoAreaDeTrabalho() {
        String home = System.getProperty("user.home");
        return Paths.get(home, "Desktop").toString() + File.separator;
    }

    //criar o metodo para buscar o salvamento

}
