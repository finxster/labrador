package br.com.maps.labrador.domain.modem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.persist.impl.annotation.DiscriminatorComment;
import br.com.maps.labrador.domain.emprestavel.enumx.AbstractEmprestavel;

/**
 * Uma entidade que irá representar um Modem em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@DiscriminatorValue("3")
@DiscriminatorComment("MODEM")
public class Modem extends AbstractEmprestavel {

    /**
     * Construtor
     */
    protected Modem() {
        super();
    }

}
