package excecao;

public class CampoMenorOuIgualAZeroExcecao extends RuntimeException {
    public CampoMenorOuIgualAZeroExcecao() {
        super("Campo não pode ser menor ou igual a zero.");
    }
}
