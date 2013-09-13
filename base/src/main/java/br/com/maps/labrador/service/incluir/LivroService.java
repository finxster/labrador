package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.livro.LocalizacaoLivro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço de inclusão de livros.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class LivroService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String ISBN_10 = "ISBN 10";

    private static final String ISBN_13 = "ISBN 13";

    private static final String TITULO = "Título";

    private static final String AUTOR = "Autor";

    private static final String EDITORA = "Editora";

    private static final String USUARIO = "Usuário";

    private static final String LOCALIZACAO = "Localização";

    private DAO<Livro> dao;

    private DAO<LocalizacaoLivro> localizacaoDAO;

    private StatelessPersister<Livro> persister;

    private String isbn10;

    private String isbn13;

    private String titulo;

    private String autor;

    private String editora;

    private LabradorUsuario usuario;

    private String localizacao;

    @Output(propertyName = IDENTIFICADOR)
    @Execution
    public Livro execute() {
        Livro livro = this.dao.createBean();
        livro.setIsbn10(this.isbn10);
        livro.setIsbn13(this.isbn13);
        livro.setTitulo(titulo);
        livro.setAutor(this.autor);
        livro.setEditora(this.editora);
        livro.setUsuario(this.usuario);

        LocalizacaoLivro localizacaoLivro = this.localizacaoDAO.createBean();
        localizacaoLivro.setNome(this.localizacao);
        livro.setLocalizacao(localizacaoLivro);

        this.persister.save(livro);
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
     * @param localizacaoDAO the localizacaoDAO to set
     */
    @Injected
    public void setLocalizacaoDAO(DAO<LocalizacaoLivro> localizacaoDAO) {
        this.localizacaoDAO = localizacaoDAO;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<Livro> persister) {
        this.persister = persister;
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
