package br.com.maps.labrador;

import br.com.maps.labrador.pages.AlterarSenhaPage;
import br.com.maps.labrador.pages.CrudUsuarioPage;
import jmine.tec.web.pages.datadigester.importacao.Importacao;
import jmine.tec.web.pages.persist.audit.ConsultaTrilhaAuditoria;
import jmine.tec.web.pages.persist.auth.Authorization;
import jmine.tec.web.pages.rtm.ConsultaExceptionRecord;
import jmine.tec.web.pages.rtm.diagnosticador.ConsultaDiagnosticador;

import jmine.tec.web.wicket.component.menu.cfg.AbstractMenuConfigFactoryBean;
import jmine.tec.web.wicket.component.menu.cfg.MenuConfig;

/**
 * Starting point menu creator
 * 
 * @author takeshi
 */
public class LabradorMenuCreator extends AbstractMenuConfigFactoryBean {

    /**
     * {@inheritDoc}
     */
    @Override
    protected MenuConfig createMenuConfig() {
        MenuConfig config = new MenuConfig();

        // add menu here
        config.addPage(Authorization.class, "Autorização", "Autorizar");
        config.addPage(ConsultaTrilhaAuditoria.class, "Autorização", "Auditoria");
        config.addPage(ConsultaDiagnosticador.class, "Infra", "Diagnosticador");
        config.addPage(ConsultaExceptionRecord.class, "Infra", "Exceptions");
        config.addPage(Importacao.class, "Infra", "Importacao");
        config.addPage(AlterarSenhaPage.class, "Infra", "Alterar senha");
        config.addPage(CrudUsuarioPage.class, "Infra", "Controle de acesso");
        
        return config;
    }

}
