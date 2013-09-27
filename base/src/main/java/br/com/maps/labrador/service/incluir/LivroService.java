package br.com.maps.labrador.service.incluir;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.livro.Livro;

/**
 * Serviço de inclusão de livros.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class LivroService extends AbstractEmprestavelService<Livro> {

    private static final String ISBN_10 = "ISBN 10";

    private static final String ISBN_13 = "ISBN 13";

    private static final String AUTOR = "Autor";

    private static final String EDITORA = "Editora";

    private String isbn10;

    private String isbn13;

    private String autor;

    private String editora;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doSpecificInclusions(Livro coisa) {
        coisa.setIsbn10(this.isbn10);
        coisa.setIsbn13(this.isbn13);
        coisa.setAutor(this.autor);
        coisa.setEditora(this.editora);
    }

    /**
     * @param isbn10 the isbn10 to set
     */
    @Input(fieldName = ISBN_10)
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * @param isbn13 the isbn13 to set
     */
    @Input(fieldName = ISBN_13)
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * @param autor the autor to set
     */
    @Input(fieldName = AUTOR)
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @param editora the editora to set
     */
    @Input(fieldName = EDITORA)
    public void setEditora(String editora) {
        this.editora = editora;
    }
}
