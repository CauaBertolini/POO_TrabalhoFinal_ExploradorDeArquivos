package modelo;

import modelo.Midias.Midia;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Salvamento {
    private List<Midia> midias;

    public Salvamento() {
        midias = new ArrayList<>();
    }

    public boolean incluirMidia(Midia midia) {
        try {
            midias.add(midia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
