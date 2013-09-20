package br.com.maps.labrador.domain.projetor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.persist.impl.annotation.DiscriminatorComment;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Uma entidade que irá representar um Projetor em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@DiscriminatorValue("4")
@DiscriminatorComment("PROJETOR")
public class Projetor extends AbstractEmprestavel {

    /**
     * Construtor
     */
    protected Projetor() {
        super();
    }

}
