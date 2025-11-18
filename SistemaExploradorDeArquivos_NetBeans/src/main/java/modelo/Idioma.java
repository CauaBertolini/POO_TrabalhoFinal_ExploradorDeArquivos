package modelo;

import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;

public class Idioma {
    String nome;

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
