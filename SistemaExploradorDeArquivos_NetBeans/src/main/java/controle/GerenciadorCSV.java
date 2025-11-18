package controle;

import controle.ExploradorDeArquivos;
import modelo.midias.Midia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GerenciadorCSV {

    private List<Midia> midias;
    private static ExploradorDeArquivos explorador;
    private static final String NOME_ARQUIVO_CSV = "caminhoDasMidias.csv";

    public GerenciadorCSV(List<Midia> midias) {
        this.midias = midias;
    }

    public static void atualizarCSV(List<Midia> listaMidias) {
        Path caminhoCSV = Paths.get(NOME_ARQUIVO_CSV);

        try (PrintWriter escritor = new PrintWriter(new FileWriter(caminhoCSV.toFile(), false))) {
            escritor.println("Caminho_Absoluto");

            for (Midia midia : listaMidias) {
                escritor.println(midia.getCaminho());
            }
        } catch (Exception exception) {

        }
    }

    public static List<String> carregarCaminhos() throws IOException {
        Path caminhoCSV = Paths.get(NOME_ARQUIVO_CSV);

        if (!Files.exists(caminhoCSV)) {
            return List.of();
        }

        List<String> linhas = Files.readAllLines(caminhoCSV);

        if (linhas.size() > 0 && linhas.get(0).equals("Caminho_Absoluto")) {
            return linhas.subList(1, linhas.size());
        }
        return linhas;
    }
}

