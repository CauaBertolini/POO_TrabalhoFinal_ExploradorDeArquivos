package controle;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;
import modelo.midias.Filme;
import modelo.midias.Livro;
import modelo.midias.Midia;
import modelo.midias.Musica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class SerializadorTpoo {

    private static Listas listas = new Listas();

    public static void salvarMidia(Midia midia) throws IOException {
        try (DataOutputStream serializador = new DataOutputStream(new FileOutputStream(new File(midia.getCaminho())))) {
            // Rótulo que coloca um celo de tipagem no arquivo
            serializador.write("tpoo".getBytes(StandardCharsets.UTF_8));

            // Versão
            serializador.writeInt((1));

            // Cabeçalho: pega o tipo de mídia
            serializador.writeUTF(midia.getClass().getSimpleName());

            // Serializando atributos da clase mídia
            serializador.writeUTF(midia.getCaminho());
            serializador.writeUTF(midia.getNome());
            serializador.writeUTF(midia.getTipoArquivo().name());
            serializador.writeDouble(midia.getDuracao());
            serializador.writeFloat(midia.getTamanho());
            serializador.writeUTF(midia.getGenero().getNome());
            serializador.writeUTF(midia.getGenero().getETipoGenero().name());

            // Atributos específicos por tipo
            if (midia instanceof Filme filme) {
                serializador.writeUTF(filme.getIdioma().getNome());
            } else if (midia instanceof Musica musica) {
                serializador.writeUTF(musica.getArtista());
            } else if (midia instanceof Livro livro) {
                serializador.writeUTF(livro.getAutor());
            }
        }

    }

    public static Midia carregarMidia(File arquivo) throws IOException {
        try (DataInputStream descerializador = new DataInputStream(new FileInputStream(arquivo))) {
            byte[] celo = new byte[4];

            descerializador.readFully(celo);

            if (!new String(celo, StandardCharsets.UTF_8).equals("tpoo")) {
                throw new IOException("Arquivo inválido.");
            }

            int versao = descerializador.readInt();

            if (versao == 1) {
                String tipoMidia = descerializador.readUTF().toUpperCase(Locale.ROOT);

                String caminho = descerializador.readUTF();
                String nome = descerializador.readUTF();
                ETipoArquivo eTipoArquivo = (ETipoArquivo.valueOf(descerializador.readUTF()));
                double duracao = descerializador.readDouble();
                float tamanho = descerializador.readFloat();

                // Pega os atributos de genero em String e converte tudo para encontrar a classe correta.
                String generoString = descerializador.readUTF();
                ETipoGenero tipoGenero = (ETipoGenero.valueOf(descerializador.readUTF()));
                Genero genero = encontrarGenero(generoString, tipoGenero);

                Midia midia = null;

                switch (tipoMidia) {
                    case "FILME":
                        String idiomaString = descerializador.readUTF();
                        Idioma idioma = encontrarIdioma(idiomaString);
                        midia = new Filme(caminho, nome, tamanho, duracao, eTipoArquivo, genero, idioma);
                        break;

                    case "MUSICA":
                        String artista = descerializador.readUTF();
                        midia = new Musica(caminho, nome, tamanho, duracao, eTipoArquivo, genero, artista);
                        break;

                    case "LIVRO":
                        String autor = descerializador.readUTF();
                        midia = new Livro(caminho, nome, tamanho, duracao, eTipoArquivo, genero, autor);
                        break;
                }
                return midia;
            }

        } catch (Exception e) {
            throw new IOException("Erro ao carregar mídia: " + e.getMessage());
        }
        return null;
    }

    public static Genero encontrarGenero(String genero, ETipoGenero tipoGenero) throws IOException {
        for (Genero gen : listas.getListaGeneros()) {
            if (gen.getETipoGenero().equals(tipoGenero)) {
                if (gen.getNome().equals(genero)) {
                    return gen;
                }
            }
        }
        return null;
    }

    public static Idioma encontrarIdioma(String idioma) throws IOException {
        for (Idioma idi : listas.getListaIdiomas()) {
            if (idi.getNome().equals(idioma)) {
                return idi;
            }
        }
        return null;
    }
}
