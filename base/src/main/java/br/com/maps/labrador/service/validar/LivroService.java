package br.com.maps.labrador.service.validar;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.livro.Livro;

/**
 * Serviço que valida um {@link Livro}.
 * 
 * @author laercio.duarte
 * @created Sep 27, 2013
 */
@ServiceImplementor(action = ActionsEnum.VALIDAR)
public class LivroService extends AbstractEmprestavelService<Livro> {

    private static final String ISBN_10 = "ISBN 10";

    private static final String ISBN_13 = "ISBN 13";

    private static final String AUTOR = "Autor";

    private static final String EDITORA = "Editora";

    private String isbn10;

    private String isbn13;

    private String autor;

    private String editora;

    @Override
    protected void doSpecificValidations(Livro livro) {
        this.assertEquals(ISBN_10, this.isbn10, livro.getIsbn10());
        this.assertEquals(ISBN_13, this.isbn13, livro.getIsbn13());
        this.assertEquals(AUTOR, this.autor, livro.getAutor());
        this.assertEquals(EDITORA, this.editora, livro.getEditora());
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
