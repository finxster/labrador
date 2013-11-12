package br.com.maps.labrador.persister.action;

import jmine.tec.component.Action;
import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;

public class AtualizarLocalizacao implements Action<AbstractEmprestavel> {

    @Injected
    private DAO<LocalizacaoEmprestavel> localizacaoDAO;

    /**
     * Verifica se a localização de uma coisa/objeto já existe na base de dados, se sim, não cadastra novamente
     * 
     * @param coisa
     */
    public void act(AbstractEmprestavel coisa) {
        LocalizacaoEmprestavel localizacaoCoisa;

        String nomeLocalizacao = coisa.getLocalizacao().getNome();
        try {
            localizacaoCoisa = this.localizacaoDAO.findByNaturalKey(nomeLocalizacao);
        } catch (BeanNotFoundException e) {
            localizacaoCoisa = this.localizacaoDAO.createBean();
            localizacaoCoisa.setNome(nomeLocalizacao);
        }
        coisa.setLocalizacao(localizacaoCoisa);
    }

}
