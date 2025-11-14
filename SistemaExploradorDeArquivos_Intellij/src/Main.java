import controle.BuscadorDeCaminho;
import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Listas lista = new Listas();

        ExploradorDeArquivos exp = new ExploradorDeArquivos();

        BuscadorDeCaminho buscador = new BuscadorDeCaminho();
        Genero gen = lista.getListaGeneros().getFirst();
        Idioma id = lista.getListaIdiomas().getFirst();
        exp.criarNovaMidia(BuscadorDeCaminho.getCaminhoAreaDeTrabalho(), "TesteFile", 1.7f, 12.23, ETipoArquivo.MP4, gen, id);
    }
}