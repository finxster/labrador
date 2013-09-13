package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.security.impl.domain.User;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço de inclusão de {@link LabradorUsuario}.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class LabradorUsuarioService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String USER = "Usuário";

    private static final String NOME = "Nome";

    private static final String EMAIL = "E-mail";

    private DAO<LabradorUsuario> dao;

    private StatelessPersister<LabradorUsuario> persister;

    private User user;

    private String nome;

    private String email;

    /**
     * Retorna o {@link LabradorUsuario} cadastrado.
     * 
     * @return o {@link LabradorUsuario} cadastrado.
     */
    @Execution
    @Output(propertyName = IDENTIFICADOR)
    public LabradorUsuario execute() {
        LabradorUsuario usuario = dao.createBean();
        usuario.setUser(this.user);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);

        this.persister.save(usuario);
        return usuario;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(DAO<LabradorUsuario> dao) {
        this.dao = dao;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<LabradorUsuario> persister) {
        this.persister = persister;
    }

    /**
     * @param user the user to set
     */
    @Input(fieldName = USER)
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @param nome the nome to set
     */
    @Input(fieldName = NOME)
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param email the email to set
     */
    @Input(fieldName = EMAIL)
    public void setEmail(String email) {
        this.email = email;
    }

}
