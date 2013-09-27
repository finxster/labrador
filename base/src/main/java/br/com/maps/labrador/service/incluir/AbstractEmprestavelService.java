package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço de inclusão de {@link AbstractEmprestavel}.
 * 
 * @author laercio.duarte
 * @created Sep 27, 2013
 * @param <T> tipo do emprestável.
 */
public abstract class AbstractEmprestavelService<T extends AbstractEmprestavel> {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String NOME = "Nome";

    private static final String LOCALIZACAO = "Localização";

    private DAO<T> dao;

    private DAO<LocalizacaoEmprestavel> localizacaoDAO;

    private StatelessPersister<T> persister;

    private String nome;

    private String localizacao;

    /**
     * Executa o serviço
     * 
     * @return emprestavel construido pelo serviço
     */
    @Output(propertyName = IDENTIFICADOR)
    @Execution
    public T execute() {
        T coisa = this.dao.createBean();
        coisa.setNome(this.nome);

        LocalizacaoEmprestavel localizacaoCoisa = this.localizacaoDAO.createBean();
        localizacaoCoisa.setNome(this.localizacao);
        coisa.setLocalizacao(localizacaoCoisa);

        this.doSpecificInclusions(coisa);

        this.persister.save(coisa);

        return coisa;
    }

    /**
     * Huck para complemento dos atributos da entidade à ser incluída.
     * 
     * @param coisa a entidade a ser incluída.
     */
    protected void doSpecificInclusions(T coisa) {
        // hook para ser sobreescrito caso necessário
    }

    /**
     * @param nome the nome to set
     */
    @Input(fieldName = NOME)
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param localizacao the localizacao to set
     */
    @Input(fieldName = LOCALIZACAO)
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(DAO<T> dao) {
        this.dao = dao;
    }

    /**
     * @param localizacaoDAO the localizacaoDAO to set
     */
    @Injected
    public void setLocalizacaoDAO(DAO<LocalizacaoEmprestavel> localizacaoDAO) {
        this.localizacaoDAO = localizacaoDAO;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<T> persister) {
        this.persister = persister;
    }
}
