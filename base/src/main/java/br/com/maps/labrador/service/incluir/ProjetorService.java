package br.com.maps.labrador.service.incluir;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.projetor.Projetor;

/**
 * Serviço para inclusão de um {@link Projetor} de um usuário
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class ProjetorService extends AbstractEmprestavelService<Projetor> {

}
