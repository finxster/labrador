package br.com.maps.labrador.service.excluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.api.annotations.Validation;
import br.com.maps.labrador.dao.LivroDAO;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para exclusão de um {@link Livro} de um {@link LabradorUsuario}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class LivroService {

    private static final String LABRADOR_USUARIO = "Usuário";

    private static final String LIVRO = "Livro";

    private Livro livro;

    private LabradorUsuario labradorUsuario;

    private LivroDAO livroDAO;

    private StatelessPersister<Livro> persister;

    @Execution
    public void execute() {
        try {
            Livro livro = this.livroDAO.findByLivroUsuario(this.livro, this.labradorUsuario);
            this.persister.remove(livro);
        } catch (BeanNotFoundException e) {
            // Validado!
        }
    }

    @Validation
    public void validate() {
        try {
            this.livroDAO.findByLivroUsuario(this.livro, this.labradorUsuario);
        } catch (BeanNotFoundException e) {
            // tratar a exception! Não é possivel excluir um livro que o usuário não tem!!!
        }
    }

    /**
     * @param livro the livro to set
     */
    @Input(fieldName = LIVRO)
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = LABRADOR_USUARIO)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.labradorUsuario = labradorUsuario;
    }

    /**
     * @param dao the dao to set
     */
    @Injected(name = "livroDAO")
    public void setDao(LivroDAO dao) {
        this.livroDAO = dao;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<Livro> persister) {
        this.persister = persister;
    }
}
