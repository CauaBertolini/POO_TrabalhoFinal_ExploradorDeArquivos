package excecao;

/**
 * Exceção lançada quando um campo obrigatório recebe um valor vazio ou nulo.
 * Essa exceção é utilizada na validação de atributos que precisam ser
 * preenchidos corretamente, como nome, caminho, autor, artista, entre outros.
 */
public class CampoVazioOuNuloExcecao extends RuntimeException {
    public CampoVazioOuNuloExcecao() {
        super("Campo não pode estar vazio");
    }
}
