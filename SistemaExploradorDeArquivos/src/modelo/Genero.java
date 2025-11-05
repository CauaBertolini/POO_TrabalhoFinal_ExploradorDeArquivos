package modelo;

import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public abstract class Genero {
    private String nome;

    public Genero(String nome) throws CampoVazioOuNuloExcecao {
        setNome(nome);
    }

    public void setNome(String nome) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(nome)) {
            this.nome = nome.trim();
        }
    }

    public String getNome() {
        return nome;
    }
}
