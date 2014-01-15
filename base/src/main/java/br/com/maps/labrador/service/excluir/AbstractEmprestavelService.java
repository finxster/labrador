package br.com.maps.labrador.service.excluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.persist.impl.validator.ValidationException;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Validation;
import br.com.maps.labrador.dao.emprestavel.AbstractEmprestavelDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço de exclusão de {@link AbstractEmprestavel}.
 * 
 * @author laercio.duarte
 * @created Oct 23, 2013
 * @param <T> tipo do emprestável.
 */
public abstract class AbstractEmprestavelService<T extends AbstractEmprestavel> {

    private static final String USUARIO = "Usuário";

    private static final String COISA = "Coisa";

    private T coisa;

    private AbstractEmprestavelDAO dao;

    private StatelessPersister<T> persister;

    private LabradorUsuario proprietario;

    /**
     * Execução do serviço de exclusão do {@link AbstractEmprestavel} para o {@link LabradorUsuario} informado.
     */
    @Execution
    public void execute() {
        try {
            T coisa = (T) this.dao.findByUsuario(this.coisa, this.proprietario);
            this.persister.remove(coisa);
        } catch (BeanNotFoundException e) {
            // Validado!
        }
    }

    /**
     * {@inheritDoc}
     */
    @Validation
    public void validate() throws BeanNotFoundException {
        try {
            this.dao.findByUsuario(this.coisa, this.proprietario);

        } catch (BeanNotFoundException e) {
            throw new ValidationException(e.getLocalizedMessageHolder());
        }
    }

    /**
     * @param dao the dao to set
     */
    @Injected(name = "dao")
    public void setDao(AbstractEmprestavelDAO dao) {
        this.dao = dao;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = USUARIO)
    public void setProprietario(LabradorUsuario labradorUsuario) {
        this.proprietario = labradorUsuario;
    }

    /**
     * @param coisa the coisa to set
     */
    @Input(fieldName = COISA)
    public void setCoisa(T coisa) {
        this.coisa = coisa;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<T> persister) {
        this.persister = persister;
    }
}
