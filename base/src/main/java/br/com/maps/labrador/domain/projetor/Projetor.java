package br.com.maps.labrador.domain.projetor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.persist.impl.annotation.DiscriminatorComment;

import org.hibernate.annotations.Filter;

import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
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
@Filter(name = EmprestavelChineseWallEntity.FILTER_NAME, condition = EmprestavelChineseWallEntity.CONDITION)
public class Projetor extends AbstractEmprestavel {

    /**
     * Construtor
     */
    protected Projetor() {
        super();
    }

}
