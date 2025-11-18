package modelo;

import modelo.midias.Midia;

import java.nio.file.Path;
import java.nio.file.Paths;
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


    public boolean atualizarMidia(Midia midia) {

        try {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Atualiza o caminho de uma mídia com base em um caminho antigo
     *
     * @param caminhoAntigo - Caminho atual que será substituído
     * @param caminhoNovo   - Novo caminho a ser definido
     * @return {@code true} se a operação foi realizada,
     * {@code false} caso ocorra algum erro inesperado
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
