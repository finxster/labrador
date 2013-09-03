package br.com.maps.labrador;

import jmine.tec.web.pages.datadigester.importacao.Importacao;
import jmine.tec.web.pages.persist.audit.ConsultaTrilhaAuditoria;
import jmine.tec.web.pages.persist.auth.Authorization;
import jmine.tec.web.pages.rtm.ConsultaExceptionRecord;
import jmine.tec.web.pages.rtm.diagnosticador.ConsultaDiagnosticador;
import jmine.tec.web.wicket.component.menu.cfg.AbstractMenuConfigFactoryBean;
import jmine.tec.web.wicket.component.menu.cfg.MenuConfig;
import br.com.maps.labrador.pages.AlterarSenhaPage;
import br.com.maps.labrador.pages.CrudUsuarioPage;
import br.com.maps.labrador.pages.cadastro.emprestimo.CadastroEmprestimo;
import br.com.maps.labrador.pages.cadastro.livro.CadastroLivro;
import br.com.maps.labrador.pages.consulta.emprestimo.PesquisaEmprestimo;
import br.com.maps.labrador.pages.consulta.livro.PesquisaLivro;

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
        config.addPage(CadastroLivro.class, "Base", "Cadastros", "Livros");
        config.addPage(CadastroEmprestimo.class, "Base", "Cadastros", "Empréstimos");

        config.addPage(PesquisaLivro.class, "Base","Pesquisas", "Livros");
        config.addPage(PesquisaEmprestimo.class, "Base","Pesquisas", "Empréstimos");

        config.addPage(Authorization.class, "Autorização", "Autorização", "Autorizar");
        config.addPage(ConsultaTrilhaAuditoria.class, "Autorização", "Autorização", "Auditoria");
        
        config.addPage(ConsultaDiagnosticador.class, "Infra", "Infra", "Diagnosticador");
        config.addPage(ConsultaExceptionRecord.class, "Infra", "Infra", "Exceptions");
        config.addPage(Importacao.class, "Infra", "Infra", "Importacao");
        config.addPage(AlterarSenhaPage.class, "Infra", "Usuários", "Alterar senha");
        config.addPage(CrudUsuarioPage.class, "Infra", "Usuários", "Controle de acesso");

        return config;
    }

}
