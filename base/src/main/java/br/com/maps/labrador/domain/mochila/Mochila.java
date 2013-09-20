package br.com.maps.labrador.domain.mochila;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.persist.impl.annotation.DiscriminatorComment;
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
public class Mochila extends AbstractEmprestavel {

    /**
     * Construtor.
     */
    protected Mochila() {
        super();
    }

}
