package modelo;

import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public class Genero {
    private String nome;
    private ETipoGenero eTipoGenero;

    public Genero(String nome, ETipoGenero tipoGenero) throws CampoVazioOuNuloExcecao {
        setNome(nome);
        seteTipoGenero(tipoGenero);
    }

    public void setNome(String nome) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(nome)) {
            this.nome = nome.trim();
        }
    }

    public String getNome() {
        return nome;
    }

    public ETipoGenero getETipoGenero() {
        return eTipoGenero;
    }

    public void seteTipoGenero(ETipoGenero eTipoGenero) throws CampoVazioOuNuloExcecao {
        if (eTipoGenero == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.eTipoGenero = eTipoGenero;
    }
    public String toString() {
        return nome;
    }

}
