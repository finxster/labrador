package br.com.maps.labrador.domain.jogo;

/**
 * Determina a classificação dos jogos quanto a faixa etária.http://www.xbox.com/pt-BR/Marketplace/gameratings
 * 
 * @author Felipe Toshio
 * @since Nov 18 , 2013
 */
public enum Classificacao {
    LIVRE("LIVRE PARA TODOS OS PÚBLICOS"), MAIOR_10_ANOS("NÃO RECOMENDADO PARA MENORES DE 10 ANOS"), MAIOR_12_ANOS(
            "NÃO RECOMENDADO PARA MENORES DE 12 ANOS"), MAIOR_14_ANOS("NÃO RECOMENDADO PARA MENORES DE 14 ANOS"), MAIOR_16_ANOS(
            "NÃO RECOMENDADO PARA MENORES DE 16 ANOS"), MAIOR_18_ANOS("NÃO RECOMENDADO PARA MENORES DE 18 ANOS");

    private String descricao;

    Classificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
