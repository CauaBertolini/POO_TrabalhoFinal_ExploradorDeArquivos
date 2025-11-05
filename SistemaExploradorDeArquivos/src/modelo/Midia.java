package modelo;

import excecao.CampoMenorOuIgualAZeroExcecao;
import excecao.CampoVazioOuNuloExcecao;

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
        if (local == null || local.isEmpty()) {
            throw new CampoVazioOuNuloExcecao();
        }this.local = local;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new CampoVazioOuNuloExcecao();
        }this.titulo = titulo;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) throws CampoMenorOuIgualAZeroExcecao {
        if (tamanho <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        }this.tamanho = tamanho;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) throws CampoMenorOuIgualAZeroExcecao {
        if (duracao <= 0) {
            throw new CampoMenorOuIgualAZeroExcecao();
        }this.duracao = duracao;
    }
}
