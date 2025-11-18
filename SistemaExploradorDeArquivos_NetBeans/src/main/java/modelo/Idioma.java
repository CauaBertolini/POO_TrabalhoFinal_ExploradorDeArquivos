package modelo;

import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;

/**
 * Representa um idioma utilizado em filme.
 * A classe contém apenas o nome do idioma.
 */
public class Idioma {
    String nome;

    /**
     * Cria um novo idioma com o nome informado.
     *
     * @param nome - Nome do idioma.
     * @throws CampoVazioOuNuloExcecao - Se o nome for nulo, vazio ou apenas espaços.
     */
    public Idioma(String nome)throws CampoVazioOuNuloExcecao {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoVazioOuNuloExcecao {
        if (!ExcecaoUtil.campoEstaVazioOuNulo(nome)) {
            this.nome = nome;
        }
    }
    public String toString() {
        return nome;
    }
}
