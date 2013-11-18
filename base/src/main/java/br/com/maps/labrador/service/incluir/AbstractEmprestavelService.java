package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.export.ReferenceMap;
import jmine.tec.services.api.export.ServiceFiller;
import jmine.tec.services.api.io.ServiceBean;
import jmine.tec.services.impl.export.impl.ExportUtils;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço de inclusão de {@link AbstractEmprestavel}.
 * 
 * @author laercio.duarte
 * @created Sep 27, 2013
 * @param <T> tipo do emprestável.
 */
public abstract class AbstractEmprestavelService<T extends AbstractEmprestavel> implements ServiceFiller<T> {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String NOME = "Nome";

    private static final String LOCALIZACAO = "Localização";

    private static final String STATUS = "Status";

    private static final String PROPRIETARIO = "Proprietário";

    private DAO<T> dao;

    private DAO<LocalizacaoEmprestavel> localizacaoDAO;

    private StatelessPersister<T> persister;

    private String nome;

    private String localizacao;

    private StatusEmprestavel status;

    private LabradorUsuario proprietario;

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
        coisa.setStatus(this.status);
        coisa.setProprietario(this.proprietario);

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
     * {@inheritDoc}
     */
    public void fillServiceBean(ServiceBean bean, ReferenceMap referenceMap, T entity) {
        bean.setAction(ActionsEnum.INCLUIR);
        bean.setName(ExportUtils.getServiceName(this.getClass()));

        ExportUtils.put(bean, IDENTIFICADOR, "coisa" + entity.getId());
        ExportUtils.put(bean, PROPRIETARIO, entity.getProprietario().getNome());
        ExportUtils.put(bean, NOME, entity.getNome());
        ExportUtils.put(bean, LOCALIZACAO, entity.getLocalizacao().getNome());
        ExportUtils.put(bean, STATUS, entity.getStatus());
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
     * @param status the status to set
     */
    // FIXME (diego.ferreira) Este campo não deveria existir, faz sentido cadastrar algo que não está disponível?
    @Input(fieldName = STATUS, required = false, defaultValue = "Disponível")
    public void setStatus(StatusEmprestavel status) {
        this.status = status;
    }

    /**
     * @param proprietario the proprietario to set
     */
    @Input(fieldName = PROPRIETARIO, required = false)
    public void setProprietario(LabradorUsuario proprietario) {
        this.proprietario = proprietario;
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
