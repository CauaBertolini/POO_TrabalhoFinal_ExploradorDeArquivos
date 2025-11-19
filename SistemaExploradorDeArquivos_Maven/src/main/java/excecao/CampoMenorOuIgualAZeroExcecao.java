package excecao;

/**
 * Exceção lançada quando um campo numérico recebe um valor menor ou igual a zero.
 * Essa exceção é utilizada para validar atributos que obrigatoriamente
 * devem possuir valores positivos, como tamanho de arquivo, duração,
 * quantidade, preço, páginas, entre outros.
 */
public class CampoMenorOuIgualAZeroExcecao extends RuntimeException {
    public CampoMenorOuIgualAZeroExcecao() {
        super("Campo não pode ser menor ou igual a zero.");
    }
}
