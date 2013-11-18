package br.com.maps.labrador.domain.jogo;

/**
 * Representa um console de um jogo.
 * 
 * @author Felipe Toshio
 * @since Nov 18, 2013
 */
public enum Console implements IConsole {

    PS1("Playstation"), PS2("Playstation 2"), PS3("Playstation 3"), XBOX("XBOX"), MEGA_DRIVE("Mega Drive"), SNES("Super Nintendo");

    private String nome;

    Console(String nome) {
        this.nome = nome;
    }

    /**
     * O nome do console do jogo.
     */
    public String getConsole() {
        return this.nome;
    }
}