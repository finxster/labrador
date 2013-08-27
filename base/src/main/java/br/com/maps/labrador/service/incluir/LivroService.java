package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.Livro;

/**
 * Serviço de inclusão de livros.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class LivroService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String ISBN = "ISBN";

    private static final String TITULO = "Título";

    private static final String AUTOR = "Autor";

    private static final String EDITORA = "Editora";

    private DAO<Livro> dao;

    private StatelessPersister<Livro> persister;

    private String isbn;

    private String titulo;

    private String autor;

    private String editora;

    @Output(propertyName = IDENTIFICADOR)
    @Execution
    public Livro execute() {
        Livro livro = dao.createBean();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setEditora(editora);

        persister.save(livro);
        return livro;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(DAO<Livro> dao) {
        this.dao = dao;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<Livro> persister) {
        this.persister = persister;
    }

    /**
     * @param isbn the isbn to set
     */
    @Input(fieldName = ISBN)
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

}
