package excecao;

public class Utilitario {

    public boolean campoEstaVazioOuNulo (String campo) throws CampoVazioOuNuloExcecao {
        if (campo.isEmpty() || campo == null) {
            throw new CampoVazioOuNuloExcecao();
        } else {
            return false;
        }
    }

    public boolean campoMenorOuIgualAZeroExcecao (int campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }

    }
}
