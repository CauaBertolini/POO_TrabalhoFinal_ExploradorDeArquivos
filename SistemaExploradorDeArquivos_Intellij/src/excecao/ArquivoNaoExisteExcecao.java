package excecao;

public class ArquivoNaoExisteExcecao extends RuntimeException {
    public ArquivoNaoExisteExcecao() {
        super("O arquivo não existe! O caminho informado é inválido.");
    }
}
