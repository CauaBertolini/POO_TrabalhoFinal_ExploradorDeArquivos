package excecao;

import java.io.File;

public class Utilitario {

    public static boolean campoEstaVazioOuNulo (String campo) throws CampoVazioOuNuloExcecao {
        if (campo.isEmpty() || campo == null) {
            throw new CampoVazioOuNuloExcecao();
        } else {
            return false;
        }
    }

    public static boolean campoMenorOuIgualAZeroExcecao (int campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }
    }

    public static boolean campoMenorOuIgualAZeroExcecao (double campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }
    }

    public static boolean campoMenorOuIgualAZeroExcecao (float campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }
    }

    public static boolean arquivoExiste (String caminho) throws ArquivoNaoExisteExcecao {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            throw new ArquivoNaoExisteExcecao();
        }
        return true;
    }
}
