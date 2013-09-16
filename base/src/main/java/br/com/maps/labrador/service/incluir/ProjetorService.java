package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.projetor.Projetor;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para inclusão de um {@link Projetor} de um {@link LabradorUsuario}
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class ProjetorService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String NOME = "Nome";

    private static final String USUARIO = "Usuário";

    private static final String LOCALIZACAO = "Localização";

    private DAO<Projetor> dao;

    private DAO<LocalizacaoEmprestavel> localizacaoDAO;

    private StatelessPersister<Projetor> persister;

    private String nome;

    private LabradorUsuario usuario;

    private String localizacao;

    @Output(propertyName = IDENTIFICADOR)
    @Execution
    public Projetor execute() {
        Projetor projetor = this.dao.createBean();
        projetor.setNome(this.nome);
        projetor.setUsuario(this.usuario);

        LocalizacaoEmprestavel localizacaoProjetor = this.localizacaoDAO.createBean();
        localizacaoProjetor.setNome(this.localizacao);
        projetor.setLocalizacao(localizacaoProjetor);

        this.persister.save(projetor);
        return projetor;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(DAO<Projetor> dao) {
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
    public void setPersister(StatelessPersister<Projetor> persister) {
        this.persister = persister;
    }

    /**
     * @param nome the nome to set
     */
    @Input(fieldName = NOME)
    public void setNome(String nome) {
        this.nome = nome;
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
