package enumerador;

/**
 * Representa os tipos de arquivos suportados pelo sistema de gerenciamento de mídias.
 * Os tipos são utilizados para validação, filtragem e categorização de mídias
 * <p>
 * MP4 – Formato de vídeo
 * MKV – Formato de vídeo que suporta múltiplas faixas de áudio e legenda
 * MP3 – Formato de áudio usado para músicas
 * PDF – Arquivo textual utilizado para livros
 * EPUB – Formato específico para e-books
 */
public enum ETipoArquivo {
    MP4,
    MKV,
    MP3,
    PDF,
    EPUB
}
