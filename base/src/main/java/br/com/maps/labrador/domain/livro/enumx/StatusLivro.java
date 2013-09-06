package br.com.maps.labrador.domain.livro.enumx;

/**
 * Status dos empréstimos dos livros.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public enum StatusLivro {

    DISPONIVEL, EMPRESTADO;

    public boolean isDisponivel() {
        return this.equals(DISPONIVEL);
    }
    
    public boolean isEmprestado() {
        return this.equals(EMPRESTADO);
    }
    
}
