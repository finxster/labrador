package br.com.maps.labrador.service.incluir;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.modem.Modem;

/**
 * Serviço para inclusão de um {@link Modem} de um usuário
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class ModemService extends AbstractEmprestavelService<Modem> {

}
