package excecao;

public class ArquivoJaExisteExcecao extends RuntimeException {
    public ArquivoJaExisteExcecao() {

        super("Já existe um arquivo com este nome no caminho indicado.");
    }
}
