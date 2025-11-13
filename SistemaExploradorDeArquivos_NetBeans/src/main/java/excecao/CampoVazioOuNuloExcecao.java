package excecao;

public class CampoVazioOuNuloExcecao extends RuntimeException {
    public CampoVazioOuNuloExcecao() {
        super("Campo não pode estar vazio");
    }
}
