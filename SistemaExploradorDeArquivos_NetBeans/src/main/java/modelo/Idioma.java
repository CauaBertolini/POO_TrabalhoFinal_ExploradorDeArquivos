package modelo;

import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public class Idioma {
    String nome;

    public Idioma(String nome)throws CampoVazioOuNuloExcecao {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(nome)) {
            this.nome = nome;
        }
    }
    public String toString() {
        return nome;
    }
}
