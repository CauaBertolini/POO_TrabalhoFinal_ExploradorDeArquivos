package modelo.midias;

import excecao.CampoMenorOuIgualAZeroExcecao;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

public abstract class Midia {
    private String local;
    private String titulo;
    private float tamanho;
    private double duracao;


    public Midia(String local, String titulo, float tamanho,  double duracao) throws CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao{
        setLocal(local);
        setTitulo(titulo);
        setTamanho(tamanho);
        setDuracao(duracao);
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(local)) {
            this.local = local.trim();
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws CampoVazioOuNuloExcecao {
        if (!Utilitario.campoEstaVazioOuNulo(titulo)) {
            this.titulo = titulo.trim();
        }
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) throws CampoMenorOuIgualAZeroExcecao {
        if (!Utilitario.campoMenorOuIgualAZeroExcecao(tamanho)) {
            this.tamanho = tamanho;
        }
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) throws CampoMenorOuIgualAZeroExcecao {
        if (!Utilitario.campoMenorOuIgualAZeroExcecao(duracao)) {
            this.duracao = duracao;
        }
    }
}
