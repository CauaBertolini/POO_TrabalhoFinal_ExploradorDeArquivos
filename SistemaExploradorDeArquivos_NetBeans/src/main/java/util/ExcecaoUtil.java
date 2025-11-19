package util;

import excecao.ArquivoNaoExisteExcecao;
import excecao.CampoMenorOuIgualAZeroExcecao;
import excecao.CampoVazioOuNuloExcecao;

import java.io.File;

public class ExcecaoUtil {

    /**
     * Verifica se uma string está vazia ou nula.
     * <p>
     * Caso o campo seja nulo ou esteja vazio, uma {@link CampoVazioOuNuloExcecao}
     * é lançada. Caso contrário, o método retorna {@code false}, indicando que o
     * campo é válido.
     *
     * @param campo a string a ser validada.
     * @return {@code false} se o campo não for nulo e nem vazio.
     * @throws CampoVazioOuNuloExcecao se o campo for nulo ou estiver vazio.
     */
    public static boolean campoEstaVazioOuNulo(String campo) throws CampoVazioOuNuloExcecao {
        if (campo == null || campo.isEmpty() ) {
            throw new CampoVazioOuNuloExcecao();
        } else {
            return false;
        }
    }

    /**
     * Verifica um campo int está menor ou igual a zero.
     * <p>
     * Caso o campo seja zero ou menor que zero, uma campoMenorOuIgualAZeroExcecao
     * é lançada. Caso contrário, o método retorna {@code false}, indicando que o
     * campo é válido.
     *
     * @param campo int a ser validado.
     * @return {@code false} se o campo não for zero e nem menor que zero.
     * @throws campoMenorOuIgualAZeroExcecao se o campo for menor ou igual a zero.
     */
    public static boolean campoMenorOuIgualAZeroExcecao(int campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }
    }

    /**
     * Verifica um campo double está menor ou igual a zero.
     * <p>
     * Caso o campo seja zero ou menor que zero, uma campoMenorOuIgualAZeroExcecao
     * é lançada. Caso contrário, o método retorna {@code false}, indicando que o
     * campo é válido.
     *
     * @param campo int a ser validado.
     * @return {@code false} se o campo não for zero e nem menor que zero.
     * @throws campoMenorOuIgualAZeroExcecao se o campo for menor ou igual a zero.
     */
    public static boolean campoMenorOuIgualAZeroExcecao(double campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }
    }

    /**
     * Verifica um campo float está menor ou igual a zero.
     * <p>
     * Caso o campo seja zero ou menor que zero, uma campoMenorOuIgualAZeroExcecao
     * é lançada. Caso contrário, o método retorna {@code false}, indicando que o
     * campo é válido.
     *
     * @param campo float a ser validado.
     * @return {@code false} se o campo não for zero e nem menor que zero.
     * @throws campoMenorOuIgualAZeroExcecao se o campo for menor ou igual a zero.
     */
    public static boolean campoMenorOuIgualAZeroExcecao(float campo) throws CampoMenorOuIgualAZeroExcecao {
        if (campo <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        } else {
            return false;
        }
    }

    /**
     * Verifica se o arquivo existe.
     * <p>
     * Caso o arquivo seja inexistente, uma ArquivoNaoExisteExcecao
     * é lançada. Caso contrário, o método retorna {@code true}, indicando que o
     * arquivo existe.
     *
     * @param caminho - Caminho usado para buscar o arquivo.
     * @return {@code true} se arquivo existir.
     * @throws campoMenorOuIgualAZeroExcecao se o campo for menor ou igual a zero.
     */
    public static boolean arquivoExiste(String caminho) throws ArquivoNaoExisteExcecao {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            throw new ArquivoNaoExisteExcecao();
        }
        return true;
    }
}
