package util;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;

import javax.swing.*;
import java.util.List;

public class ComboUtil {

    static Listas lista = new Listas();

    /**
     * Preenche o combo com valores do Enum ETipoArquivo + opção "Todos".
     * O combo deve ser declarado como JComboBox<String>.
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
     * Preenche o combo com nomes dos gêneros (String) + "Todos".
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
     * Preenche o combo com OBJETOS Genero filtrados por ETipoGenero.
     * Aqui o combo deve ser JComboBox<Genero>.
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

    public static void carregarIdioma(JComboBox<Idioma> combo) {
        combo.removeAllItems();

        for (Idioma idioma : lista.getListaIdiomas()) {
            combo.addItem(idioma);
        }
    }

    public static void carregarTipoArquivoFilme(JComboBox<ETipoArquivo> combo) {
        combo.removeAllItems();
        combo.addItem(ETipoArquivo.MP4);
        combo.addItem(ETipoArquivo.MKV);
    }

    public static void carregarTipoArquivoMusica(JComboBox<ETipoArquivo> combo) {
        combo.removeAllItems();
        combo.addItem(ETipoArquivo.MP4);
        combo.addItem(ETipoArquivo.MP3);
    }

    public static void carregarTipoArquivoLivro(JComboBox<ETipoArquivo> combo) {
        combo.removeAllItems();
        combo.addItem(ETipoArquivo.PDF);
        combo.addItem(ETipoArquivo.EPUB);
    }
}