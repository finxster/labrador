package br.com.maps.labrador.service.validar;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.mochila.Mochila;

/**
 * Serviço que valida um {@link Mochila}.
 * 
 * @author laercio.duarte
 * @created Sep 27, 2013
 */
@ServiceImplementor(action = ActionsEnum.VALIDAR)
public class MochilaService extends AbstractEmprestavelService<Mochila> {

}
