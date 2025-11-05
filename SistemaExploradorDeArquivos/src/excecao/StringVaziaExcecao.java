package excecao;

public class StringVaziaExcecao extends RuntimeException {
    public StringVaziaExcecao() {
        super("Campo não pode estar vazio");
    }
}
