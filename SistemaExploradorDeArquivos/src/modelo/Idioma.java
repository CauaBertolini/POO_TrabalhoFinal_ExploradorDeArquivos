package modelo;

import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public class Idioma {
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(nome)) {
            this.nome = nome;
        }
    }
}
