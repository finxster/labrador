package br.com.maps.labrador.service.excluir;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.mochila.Mochila;

/**
 * Serviço para exclusão de uma {@link Mochila} de um usuário
 * 
 * @author laercio.duarte
 * @created Oct 23, 2013
 */
@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class MochilaService extends AbstractEmprestavelService<Mochila> {

}
