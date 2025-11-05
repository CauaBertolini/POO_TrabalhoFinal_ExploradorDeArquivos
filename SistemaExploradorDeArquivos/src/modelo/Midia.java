package modelo;

import excecao.StringVaziaExcecao;

public abstract class Midia {
    private String local;
    private String titulo;
    private float tamanho;
    private double duracao;


    public Midia(String local, String titulo, float tamanho,  double duracao) throws StringVaziaExcecao, CampoMenorOuIgualAZeroExcecao{
        setLocal(local);
        setTitulo(titulo);
        setTamanho(tamanho);
        setDuracao(duracao);
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) throws StringVaziaExcecao {
        if (local == null || local.isEmpty()) {
            throw new StringVaziaExcecao();
        }this.local = local;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new StringVaziaExcecao();
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
