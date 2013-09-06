package br.com.maps.labrador.validator;

import static br.com.maps.labrador.LabradorBaseMessages.LIVRO_NAO_DISPONIVEL_PARA_EMPRESTIMO;
import static br.com.maps.labrador.LabradorBaseMessages.NAO_EH_POSSIVEL_EMPRESTAR_UM_LIVRO_PROPRIO;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.annotations.Unmodifiable;
import jmine.tec.persist.impl.validator.AbstractValidator;
import jmine.tec.persist.impl.validator.ValidationError;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.livro.Livro;

/**
 * Validações referentes ao empréstimo de um livro.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
@Unmodifiable
public class EmprestimoValidator extends AbstractValidator<Emprestimo> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidationError> validateInsert(Emprestimo bean) {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        Livro livro = bean.getLivro();
        if (!livro.getStatus().isDisponivel()) {
            this.addError(errors, new ValidationError(LIVRO_NAO_DISPONIVEL_PARA_EMPRESTIMO.create(livro.getTitulo())));
        }

        if (bean.getTomador().equals(livro.getUsuario())) {
            this.addError(errors, new ValidationError(NAO_EH_POSSIVEL_EMPRESTAR_UM_LIVRO_PROPRIO.create()));
        }

        return errors;
    }

}
