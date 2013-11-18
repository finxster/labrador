package br.com.maps.labrador;

import jmine.tec.web.wicket.component.menu.cfg.AbstractMenuConfigFactoryBean;
import jmine.tec.web.wicket.component.menu.cfg.MenuConfig;
import br.com.maps.labrador.pages.AlterarSenhaPage;
import br.com.maps.labrador.pages.cadastro.contato.CadastroContato;
import br.com.maps.labrador.pages.cadastro.emprestimo.CadastroEmprestimo;
import br.com.maps.labrador.pages.cadastro.emprestimo.controle.ControleEmprestimo;
import br.com.maps.labrador.pages.cadastro.emprestimo.devolucao.CadastroDevolucaoEmprestimo;
import br.com.maps.labrador.pages.cadastro.livro.CadastroLivro;
import br.com.maps.labrador.pages.cadastro.mochila.CadastroMochila;
import br.com.maps.labrador.pages.cadastro.modem.CadastroModem;
import br.com.maps.labrador.pages.cadastro.projetor.CadastroProjetor;
import br.com.maps.labrador.pages.consulta.contato.PesquisaContato;
import br.com.maps.labrador.pages.consulta.emprestimo.PesquisaEmprestimo;
import br.com.maps.labrador.pages.consulta.livro.PesquisaLivro;
import br.com.maps.labrador.pages.consulta.mochila.PesquisaMochila;
import br.com.maps.labrador.pages.consulta.modem.PesquisaModem;
import br.com.maps.labrador.pages.consulta.projetor.PesquisaProjetor;

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
        config.addPage(CadastroLivro.class, "Cadastros", "Livros");
        config.addPage(CadastroMochila.class, "Cadastros", "Mochilas");
        config.addPage(CadastroModem.class, "Cadastros", "Modems");
        config.addPage(CadastroProjetor.class, "Cadastros", "Projetores");
        config.addPage(CadastroContato.class, "Cadastros", "Contato");

        config.addPage(PesquisaEmprestimo.class, "Empréstimos", "Pesquisar");
        config.addPage(CadastroEmprestimo.class, "Empréstimos", "Tomar emprestado");
        config.addPage(ControleEmprestimo.class, "Empréstimos", "Emprestar");
        config.addPage(CadastroDevolucaoEmprestimo.class, "Empréstimos", "Devolver");

        config.addPage(PesquisaLivro.class, "Consultas", "Livros");
        config.addPage(PesquisaMochila.class, "Consultas", "Mochilas");
        config.addPage(PesquisaModem.class, "Consultas", "Modems");
        config.addPage(PesquisaProjetor.class, "Consultas", "Projetores");
        config.addPage(PesquisaContato.class, "Consultas", "Contato");

        // config.addPage(Authorization.class, "Autorização", "Autorizar");
        // config.addPage(ConsultaTrilhaAuditoria.class, "Autorização", "Auditoria");

        // config.addPage(ConsultaDiagnosticador.class, "Infra", "Infra", "Diagnosticador");
        // config.addPage(ConsultaExceptionRecord.class, "Infra", "Infra", "Exceptions");
        // config.addPage(Importacao.class, "Infra", "Infra", "Importacao");

        config.addPage(AlterarSenhaPage.class, "Infra", "Usuários", "Alterar senha");
        // config.addPage(CrudUsuarioPage.class, "Infra", "Usuários", "Controle de acesso");

        // config.addPage(PesquisaGroup.class, "Infra", "Controle de acesso", "Grupo de usuários");

        return config;
    }

}
