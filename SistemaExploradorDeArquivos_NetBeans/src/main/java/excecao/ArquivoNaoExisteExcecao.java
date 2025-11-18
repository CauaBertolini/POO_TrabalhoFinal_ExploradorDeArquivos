package excecao;

/**
 * Exceção lançada quando um arquivo ou caminho informado não existe.
 * Essa exceção é utilizada em operações que dependem da existência de um
 * arquivo ou diretório, como leitura, carregamento, alteração ou remoção.
 */
public class ArquivoNaoExisteExcecao extends RuntimeException {
    public ArquivoNaoExisteExcecao() {
        super("O arquivo não existe! O caminho informado é inválido.");
    }
}
