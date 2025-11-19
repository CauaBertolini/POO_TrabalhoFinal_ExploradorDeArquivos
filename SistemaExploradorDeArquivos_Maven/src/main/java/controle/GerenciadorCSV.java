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

    /**
     * Constrói um novo gerenciador de CSV com a lista de mídias informada.
     * <p>
     * A lista recebida passa a ser a base utilizada pelo gerenciador para
     * operações de leitura e escrita no arquivo CSV.
     *
     * @param midias Lista de objetos {@link Midia} que será mantida pelo gerenciador.
     */
    public GerenciadorCSV(List<Midia> midias) {
        this.midias = midias;
    }

    /**
     * Atualiza o arquivo CSV com os caminhos absolutos das mídias registradas.
     * <p>
     * O método sobrescreve o conteúdo do CSV, insere o cabeçalho padrão e
     * escreve linha a linha o caminho absoluto de cada mídia presente na lista.
     * Em caso de erro durante a escrita, a exceção é ignorada e nenhuma ação
     * corretiva é tomada.
     *
     * @param listaMidias Lista de mídias que terá seus caminhos gravados no CSV.
     */
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

    /**
     * Carrega do arquivo CSV todos os caminhos absolutos previamente
     * registrados.
     * <p>
     * Se o arquivo não existir, o método retorna uma lista vazia. Caso exista,
     * lê todas as linhas, identifica a presença do cabeçalho e retorna apenas
     * os caminhos das mídias. O método sempre retorna uma lista imutável.
     *
     * @return Lista de caminhos absolutos das mídias registrados no CSV.
     * @throws IOException Lançada quando ocorre erro ao acessar ou ler o arquivo CSV.
     */
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

