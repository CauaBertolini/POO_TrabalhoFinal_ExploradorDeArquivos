package enumerador;

/**
 * Enum que representa os tipos gerais de gênero que uma mídia pode possuir
 * Utilizado para categorizar mídias como livros, filmes e músicas.
 * Usado para validação, garantindo que cada mídia receba
 * apenas gêneros compatíveis com seu tipo
 * <p>
 * LITERARIO – Gêneros relacionados a obras escritas, como livros e poesias.
 * CINEMA – Gêneros utilizados para filmes e produções audiovisuais.
 * MUSICAL – Gêneros aplicados a músicas e produções sonoras.
 */
public enum ETipoGenero {
    LITERARIO,
    CINEMA,
    MUSICAL
}
