package modelo.generos;

import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public class Genero {
    private String nome;
    private ETipoGenero eTipoGenero;

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

    public ETipoGenero geteTipoGenero() {
        return eTipoGenero;
    }

    public void seteTipoGenero(ETipoGenero eTipoGenero) throws CampoVazioOuNuloExcecao {
        if (eTipoGenero == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.eTipoGenero = eTipoGenero;
    }
}
