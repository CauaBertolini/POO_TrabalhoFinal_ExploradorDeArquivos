package excecao;

/**
 * Exceção lançada quando uma operação tenta criar ou mover um arquivo
 * para um caminho onde já existe um arquivo com o mesmo nome.
 * <p>
 * Essa exceção é utilizada para sinalizar conflitos de sobrescrita
 * quando a ação não deve prosseguir automaticamente.
 * </p>
 */
public class ArquivoJaExisteExcecao extends RuntimeException {
    public ArquivoJaExisteExcecao() {

        super("Já existe um arquivo com este nome no caminho indicado.");
    }
}
