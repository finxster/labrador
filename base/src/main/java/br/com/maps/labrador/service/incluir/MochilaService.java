package br.com.maps.labrador.service.incluir;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.mochila.Mochila;

/**
 * Serviço para inclusão de um {@link Mochila} de um usuário
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class MochilaService extends AbstractEmprestavelService<Mochila> {

}
