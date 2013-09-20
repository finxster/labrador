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
import br.com.maps.labrador.domain.mochila.Mochila;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para inclusão de um {@link Mochila} de um {@link LabradorUsuario}
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class MochilaService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String NOME = "Nome";

    private static final String USUARIO = "Usuário";

    private static final String LOCALIZACAO = "Localização";

    private DAO<Mochila> dao;

    private DAO<LocalizacaoEmprestavel> localizacaoDAO;

    private StatelessPersister<Mochila> persister;

    private String nome;

    private LabradorUsuario usuario;

    private String localizacao;

    @Output(propertyName = IDENTIFICADOR)
    @Execution
    public Mochila execute() {
        Mochila mochila = this.dao.createBean();
        mochila.setNome(this.nome);
        mochila.setProprietario(this.usuario);

        LocalizacaoEmprestavel localizacaoProjetor = this.localizacaoDAO.createBean();
        localizacaoProjetor.setNome(this.localizacao);
        mochila.setLocalizacao(localizacaoProjetor);

        this.persister.save(mochila);
        return mochila;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(DAO<Mochila> dao) {
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
    public void setPersister(StatelessPersister<Mochila> persister) {
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
