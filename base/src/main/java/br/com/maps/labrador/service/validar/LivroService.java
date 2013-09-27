package br.com.maps.labrador.service.validar;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.impl.AbstractValidationService;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço que valida um {@link Livro}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
@ServiceImplementor(action = ActionsEnum.VALIDAR)
public class LivroService extends AbstractValidationService {

    private static final String LIVRO = "Livro";

    private static final String ISBN_10 = "ISBN 10";

    private static final String ISBN_13 = "ISBN 13";

    private static final String TITULO = "Título";

    private static final String AUTOR = "Autor";

    private static final String EDITORA = "Editora";

    private static final String USUARIO = "Usuário";

    private static final String LOCALIZACAO = "Localização";

    private Livro livro;

    private String isbn10;

    private String isbn13;

    private String titulo;

    private String autor;

    private String editora;

    private LabradorUsuario usuario;

    private String localizacao;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validate() throws ServiceExecutionException {
        assertEquals(USUARIO, this.usuario, this.livro.getProprietario());
        assertEquals(ISBN_10, this.isbn10, this.livro.getIsbn10());
        assertEquals(ISBN_13, this.isbn13, this.livro.getIsbn13());
        assertEquals(TITULO, this.titulo, this.livro.getNome());
        assertEquals(AUTOR, this.autor, this.livro.getAutor());
        assertEquals(EDITORA, this.editora, this.livro.getEditora());
        assertEquals(LOCALIZACAO, this.localizacao, this.livro.getLocalizacao().getNome());
    }

    /**
     * @param livro the livro to set
     */
    @Input(fieldName = LIVRO)
    public void setLivro(Livro livro) {
        this.livro = livro;
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
     * @param titulo the titulo to set
     */
    @Input(fieldName = TITULO)
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    /**
     * @param usuario the usuario to set
     */
    @Input(fieldName = USUARIO)
    public void setUsuario(LabradorUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @param localizacao the localizacao to set
     */
    @Input(fieldName = LOCALIZACAO)
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

}
