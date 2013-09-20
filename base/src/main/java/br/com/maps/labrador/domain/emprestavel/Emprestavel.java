package br.com.maps.labrador.domain.emprestavel;

import java.io.Serializable;

import jmine.tec.persist.api.Persistable;

/**
 * Define o comportamento de um objeto que pode ser emprestado.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
public interface Emprestavel extends Persistable, Serializable {

    void emprestar();

    void devolver();

}
