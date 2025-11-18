package modelo;

import modelo.midias.Midia;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar operações de armazenamento temporário
 * de objetos do tipo Midia.
 * <p>
 * Esta classe mantém uma lista de mídias e realiza.
 * operações de inclusão, remoção e atualização básica de informações.
 *
 */
public class Salvamento {
    private List<Midia> midias;

    /**
     * Constrói um objeto Salvamento inicializando a lista de mídias.
     */
    public Salvamento() {
        midias = new ArrayList<>();
    }

    /**
     * Adiciona uma mídia à lista mídias
     *
     * @param midia - Mídia a ser adicionada.
     * @return {@code true} se a mídia foi adicionada com sucesso, {@code false} caso ocorra algum erro.
     */
    public boolean incluirMidia(Midia midia) {
        try {
            midias.add(midia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Remove uma mídia da lista
     *
     * @param midia - Mídia a ser removida
     * @return {@code true} se a mídia foi removida com sucesso,  {@code false} caso ocorra algum erro
     */
    public boolean removerMidia(Midia midia) {
        try {
            midias.remove(midia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Atualiza uma mídia existente na lista de mídias, substituindo a instância antiga
     * pela nova mídia fornecida.
     * <p>
     * A comparação é feita pelo caminho do arquivo ({@code midia.getCaminho()}),
     * que funciona como identificador único da mídia. Caso o caminho corresponda a
     * alguma mídia já cadastrada, ela é substituída pela nova instância.
     *
     * @param midia a mídia atualizada que deve substituir a versão anterior na lista.
     * @return {@code true} se a mídia foi encontrada e atualizada com sucesso;
     * {@code false} se nenhuma mídia correspondente for encontrada ou ocorrer algum erro.
     */
    public boolean atualizarMidia(Midia midia) {
        try {
            for (int i = 0; i < midias.size(); i++) {
                if (midias.get(i).getCaminho().equalsIgnoreCase(midia.getCaminho())) {
                    midias.set(i, midia);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Atualiza o caminho de uma mídia já cadastrada, substituindo o caminho antigo
     * pelo novo caminho informado.
     * <p>
     * O método percorre a lista de mídias e procura aquela cujo caminho atual
     * corresponda ao {@code caminhoAntigo}. Quando encontrada, seu caminho é
     * atualizado para {@code caminhoNovo}.
     *
     * @param caminhoAntigo o caminho atual da mídia que deve ser atualizado.
     * @param caminhoNovo   o novo caminho que será atribuído à mídia.
     * @return {@code true} se o caminho foi atualizado com sucesso;
     * {@code false} se ocorrer algum erro durante o processo.
     */
    public boolean atualizarCaminho(String caminhoAntigo, String caminhoNovo) {
        try {
            for (Midia m : getMidias()) {
                if (m.getCaminho().equalsIgnoreCase(caminhoAntigo)) {
                    m.setCaminho(caminhoNovo);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Midia> getMidias() {
        return midias;
    }

}
