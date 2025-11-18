package modelo;

import enumerador.ETipoGenero;
import excecao.CampoVazioOuNuloExcecao;
import excecao.Utilitario;

/**
 * Representa um gênero dentro do sistema, podendo ser literário, musical ou cinematográfico
 * <p>
 * Um objeto Genero contém:
 * O nome do gênero (ex.: "Romance", "Rock", "Ação")
 * O tipo do gênero, definido pelo enum {@link ETipoGenero}, que indica
 * se o gênero pertence ao contexto literário, musical ou de cinema
 */
public class Genero {
    private String nome;
    private ETipoGenero eTipoGenero;

    /**
     * Cria um objeto do tipo Genero inicializando seu nome e seu tipo
     *
     * @param nome       - Nome do gênero (ex.: "Romance", "Rock", "Ação").
     * @param tipoGenero - Tipo do gênero, indicando se é literário, musical ou cinematográfico
     * @throws CampoVazioOuNuloExcecao Se o nome for nulo ou vazio, ou se o tipoGenero for nulo
     */
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
}
