package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoMenorOuIgualAZeroExcecao;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;
import modelo.generos.Genero;

import java.util.ArrayList;
import java.util.List;

public abstract class Midia {
    private String local;
    private String titulo;
    private float tamanho;
    private double duracao;
    private ETipoArquivo tipoArquivo;
    private List<Genero> generos;


    public Midia(String local, String titulo, float tamanho,  double duracao, ETipoArquivo tipoArquivo) throws CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao{
        generos = new ArrayList<>();
        setNome(nome);
        setCaminho(caminho);
        setTipoArquivo(eTipoArquivo);
        setDuracao(duracao);
        setTamanho(tamanho);
        setDuracao(duracao);
        setTipoArquivo(tipoArquivo);
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

    public ETipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(ETipoArquivo tipoArquivo) throws CampoVazioOuNuloExcecao {
        if (tipoArquivo == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.tipoArquivo = tipoArquivo;
    }

    public void adicionarGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null){
            throw new CampoVazioOuNuloExcecao();
        }
        generos.add(genero);
    }

    public void removerGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null){
            throw new CampoVazioOuNuloExcecao();
        }
        generos.remove(genero);
    }
}
