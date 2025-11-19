package modelo.midias;

import enumerador.ETipoArquivo;
import excecao.CampoMenorOuIgualAZeroExcecao;
import excecao.CampoVazioOuNuloExcecao;
import util.ExcecaoUtil;
import modelo.Genero;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata responsável pela criação de objetos do tipo Mídia.
 * Define os atributos e métodos comuns que serão compartilhados
 * pelas diferentes mídias do sistema. Contém também a validação dos valores
 * inseridos, garantindo que as subclasses recebam dados corretos antes de serem
 * instanciadas.
 *
 * As subclasses irão implementar os comportamentos específicos.
 */
public abstract class Midia {
    private String caminho;
    private String nome;
    private float tamanho;
    private double duracao;
    private ETipoArquivo tipoArquivo;
    private List<Genero> generos;
    private Genero genero;

    /**
     * Este construtor aplica validações nos valores recebidos, garantindo que
     * nenhum campo obrigatório seja vazio/nulo e que valores numéricos sejam
     * positivos, conforme regras de negócio definidas pela classe.
     *
     * @param caminho      Caminho onde o arquivo está armazenado
     * @param nome         nome da mídia
     * @param eTipoArquivo tipo do arquivo da mídia (PDF, EPUB)
     * @param duracao      duração da mídia
     * @param tamanho      tamanho do arquivo
     * @throws CampoVazioOuNuloExcecao       Se nome, caminho ou tipo forem inválidos
     * @throws CampoMenorOuIgualAZeroExcecao Se duração e tamnho forem menor ou igual a 0
     */
    public Midia(String caminho, String nome, ETipoArquivo eTipoArquivo, double duracao, float tamanho) throws CampoVazioOuNuloExcecao, CampoMenorOuIgualAZeroExcecao{
        generos = new ArrayList<>();
        setNome(nome);
        setCaminho(caminho);
        setTipoArquivo(eTipoArquivo);
        setDuracao(duracao);
        setTamanho(tamanho);
    }

    /**
     * Metodo para adicionar gênero da lista de gêneros
     *
     * @param genero Utilizado para adicionar gênero a lista de gêneros (Reservado para uso em versões futuras)
     * @throws CampoVazioOuNuloExcecao Lançada quando o genero recebido estiver vazio ou nulo.
     */
    public void adicionarGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null){
            throw new CampoVazioOuNuloExcecao();
        }
        generos.add(genero);
    }

    /**
     * Método para remover genero da lista de Generos
     *
     * @param genero Utilizado para remover gênero da lista de gêneros (Reservado para uso em versões futuras)
     * @throws CampoVazioOuNuloExcecao Lançada quando o genero recebido estiver vazio ou nulo.
     */
    public void removerGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null){
            throw new CampoVazioOuNuloExcecao();
        }
        generos.remove(genero);
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String local) throws CampoVazioOuNuloExcecao {
        if (!ExcecaoUtil.campoEstaVazioOuNulo(local)) {
            this.caminho = local.trim();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String titulo) throws CampoVazioOuNuloExcecao {
        ExcecaoUtil.campoEstaVazioOuNulo(titulo);
        this.nome = titulo.trim();
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) throws CampoMenorOuIgualAZeroExcecao {
        if (!ExcecaoUtil.campoMenorOuIgualAZeroExcecao(tamanho)) {
            this.tamanho = tamanho;
        }
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) throws CampoMenorOuIgualAZeroExcecao {
        if (!ExcecaoUtil.campoMenorOuIgualAZeroExcecao(duracao)) {
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) throws CampoVazioOuNuloExcecao {
        if (genero == null) {
            throw new CampoVazioOuNuloExcecao();
        }
        this.genero = genero;
    }

}
