package br.com.maps.labrador.domain.modem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.persist.impl.annotation.DiscriminatorComment;

import org.hibernate.annotations.Filter;

import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Uma entidade que irá representar um Modem em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@DiscriminatorValue("3")
@DiscriminatorComment("MODEM")
@Filter(name = EmprestavelChineseWallEntity.FILTER_NAME, condition = EmprestavelChineseWallEntity.CONDITION)
public class Modem extends AbstractEmprestavel {

    /**
     * Construtor
     */
    protected Modem() {
        super();
    }

}
