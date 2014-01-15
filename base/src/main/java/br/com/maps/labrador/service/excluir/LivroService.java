package br.com.maps.labrador.service.excluir;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.livro.Livro;

/**
 * Serviço para exclusão de um {@link Livro} de um usuário
 * 
 * @author laercio.duarte
 * @created Oct 23, 2013
 */
@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class LivroService extends AbstractEmprestavelService<Livro> {

}
