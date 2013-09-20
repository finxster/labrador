package br.com.maps.labrador.domain.emprestavel;

import java.io.Serializable;

import jmine.tec.persist.api.Persistable;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Define o comportamento de um objeto que pode ser emprestado.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
public interface Emprestavel extends Persistable, Serializable {

    String getNome();

    LabradorUsuario getProprietario();

    LocalizacaoEmprestavel getLocalizacao();

    StatusEmprestavel getStatus();

    void emprestar();

    void devolver();

}
