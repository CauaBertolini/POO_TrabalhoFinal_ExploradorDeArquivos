package modelo;

import enumerador.EtipoPessoa;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public class Pessoa {
    private String nome;
    private EtipoPessoa tipo;

    public Pessoa(String nome, EtipoPessoa tipo) throws CampoVazioOuNuloExcecao {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(nome)) {
            this.nome = nome;
        }
    }

    public EtipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(EtipoPessoa tipo) {
        this.tipo = tipo;
    }
}
