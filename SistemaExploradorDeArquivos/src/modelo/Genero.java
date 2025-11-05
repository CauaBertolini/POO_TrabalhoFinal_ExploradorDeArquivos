package modelo;

import excecao.StringVaziaExcecao;

public abstract class Genero {
    private String nome;

    public Genero(String nome) throws StringVaziaExcecao {
        setNome(nome);
    }

    public void setNome(String nome) throws StringVaziaExcecao {
        if (nome == null || nome.isBlank()) {
            throw new CampoVazioOuNuloExcecao();
        }
    }

    public String getNome() {
        return nome;
    }
}
