package util;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;

import javax.swing.*;
import java.util.List;

/**
 *
 */
public class ComboUtil {

    static Listas lista = new Listas();

    /**
     * Carrega todos os tipos de arquivo disponíveis no enum ETipoArquivo
     * dentro de um JComboBox, adicionando também a opção "Todos" para uso
     * em filtros.
     * <p>
     * O método cria um novo {@code DefaultComboBoxModel}, insere a opção "Todos"
     * e depois adiciona cada valor do enum, definindo o modelo resultante no combo.
     *
     * @param combo o componente {@code JComboBox} que receberá os tipos de arquivo.
     */
    public static void carregarTiposArquivoParaFiltro(JComboBox<String> combo) {
        ETipoArquivo[] tipos = ETipoArquivo.values();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todos");

        for (ETipoArquivo t : tipos) {
            model.addElement(t.toString());
        }

        combo.setModel(model);
    }

    /**
     * Carrega os gêneros disponíveis em uma lista dentro de um JComboBox,
     * adicionando também a opção "Todos" para uso em filtros.
     * <p>
     * O método garante que nenhum gênero seja repetido, utilizando um
     * {@code HashSet} para armazenar nomes únicos antes de adicioná-los ao combo.
     *
     * @param combo o componente {@code JComboBox} que receberá os gêneros.
     * @param lista a lista de objetos Genero da qual os nomes serão extraídos.
     */
    public static void carregarGenerosParaFiltro(JComboBox<String> combo, List<Genero> lista) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todos");

        java.util.Set<String> generosUnicos = new java.util.HashSet<>();

        for (Genero g : lista) {
            generosUnicos.add(g.getNome());
        }

        for (String g : generosUnicos) {
            model.addElement(g);
        }

        combo.setModel(model);
    }

    /**
     * Carrega os gêneros disponíveis em uma lista dentro de um JComboBox,
     * adicionando também a opção "Todos" para uso em filtros.
     * <p>
     * O método garante que nenhum gênero seja repetido, utilizando um
     * {@code HashSet} para armazenar nomes únicos antes de adicioná-los ao combo.
     *
     * @param combo o componente {@code JComboBox} que receberá os gêneros.
     * @param lista a lista de objetos Genero da qual os nomes serão extraídos.
     */
    public static void carregarGenerosComFiltro(JComboBox<Genero> combo, ETipoGenero tipo) {
        DefaultComboBoxModel<Genero> model = new DefaultComboBoxModel<>();

        for (Genero g : lista.getListaGeneros()) {
            if (g.getETipoGenero() == tipo) {
                model.addElement(g);
            }
        }

        combo.setModel(model);
    }

    /**
     * Carrega os idiomas disponíveis  dentro de um JComboBox,
     * adicionando também a opção "Todos" para uso em filtros.
     *
     * @param combo
     */
    public static void carregarIdioma(JComboBox<Idioma> combo) {
        combo.removeAllItems();

        for (Idioma idioma : lista.getListaIdiomas()) {
            combo.addItem(idioma);
        }
    }

    /**
     * Carrega no ComboBox os tipos de arquivo permitidos para mídias do tipo Filme.
     *
     * @param combo o JComboBox<ETipoArquivo> que receberá os tipos de arquivo.
     */
    public static void carregarTipoArquivoFilme(JComboBox<ETipoArquivo> combo) {
        combo.removeAllItems();
        combo.addItem(ETipoArquivo.MP4);
        combo.addItem(ETipoArquivo.MKV);
    }

    /**
     * Carrega no ComboBox os tipos de arquivo permitidos para mídias do tipo Musica.
     *
     * @param combo o JComboBox<ETipoArquivo> que receberá os tipos de arquivo.
     */
    public static void carregarTipoArquivoMusica(JComboBox<ETipoArquivo> combo) {
        combo.removeAllItems();
        combo.addItem(ETipoArquivo.MP3);
    }

    /**
     * Carrega no ComboBox os tipos de arquivo permitidos para mídias do tipo Livro.
     *
     * @param combo o JComboBox<ETipoArquivo> que receberá os tipos de arquivo.
     */
    public static void carregarTipoArquivoLivro(JComboBox<ETipoArquivo> combo) {
        combo.removeAllItems();
        combo.addItem(ETipoArquivo.PDF);
        combo.addItem(ETipoArquivo.EPUB);
    }

    public static void carregarInstanciasParaFiltro(JComboBox<String> combo) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todos");
        model.addElement("Filme");
        model.addElement("Musica");
        model.addElement("Livro");
        combo.setModel(model);
    }
    public static void carregarOrdenacoes(JComboBox<String> combo) {
        combo.addItem("Nenhum");
        combo.addItem("Nome A-Z");
        combo.addItem("Duração Crescente");
        combo.addItem("Duração Decrescente");
    }
}