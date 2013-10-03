package br.com.maps.labrador.domain.mochila;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.persist.impl.annotation.DiscriminatorComment;

import org.hibernate.annotations.Filter;

import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Uma entidade que irá representar uma Mochila em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@DiscriminatorValue("2")
@DiscriminatorComment("MOCHILA")
@Filter(name = EmprestavelChineseWallEntity.FILTER_NAME, condition = EmprestavelChineseWallEntity.CONDITION)
public class Mochila extends AbstractEmprestavel {

    /**
     * Construtor.
     */
    protected Mochila() {
        super();
    }

}
