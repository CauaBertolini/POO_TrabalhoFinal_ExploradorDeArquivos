package excecao;

public class StringVaziaExcecao extends RuntimeException {
    public StringVaziaExcecao(String message) {
        super(message+", não pode estar vazio");
    }
}
