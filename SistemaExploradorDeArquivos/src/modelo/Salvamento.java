package modelo;

import modelo.midias.Midia;

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


}
