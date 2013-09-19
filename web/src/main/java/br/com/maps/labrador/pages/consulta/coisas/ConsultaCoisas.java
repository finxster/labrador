package br.com.maps.labrador.pages.consulta.coisas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.ReportBuilder;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.component.command.button.ButtonCommand;
import jmine.tec.web.wicket.component.command.button.SearchCommand;
import jmine.tec.web.wicket.pages.form.BaseListPage;
import jmine.tec.web.wicket.result.BaseResultPanel;
import jmine.tec.web.wicket.result.providers.BaseResultTableProvider;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.dao.LivroDAO;
import br.com.maps.labrador.dao.MochilaDAO;
import br.com.maps.labrador.dao.ModemDAO;
import br.com.maps.labrador.dao.ProjetorDAO;
import br.com.maps.labrador.domain.emprestavel.Emprestavel;

public class ConsultaCoisas extends BaseListPage<ConsultaCoisasFilter, EmprestavelVO> {

    @SpringBean
    private DAOFactory daoFactory;

    /**
     * {@inheritDoc}
     */
    public List<EmprestavelVO> search(DAOFactory daoFactory) {
        LivroDAO livroDAO = daoFactory.getDAOByClass(LivroDAO.class);
        MochilaDAO mochilaDAO = daoFactory.getDAOByClass(MochilaDAO.class);
        ModemDAO modemDAO = daoFactory.getDAOByClass(ModemDAO.class);
        ProjetorDAO projetorDAO = daoFactory.getDAOByClass(ProjetorDAO.class);

        ConsultaCoisasFilter m = this.getModel();
        Set<Emprestavel> emprestaveis = new HashSet<Emprestavel>();
        emprestaveis.addAll(livroDAO.findByTitulo(m.getCoisa()));
        emprestaveis.addAll(livroDAO.findByProprietario(m.getCoisa()));
        emprestaveis.addAll(livroDAO.findByLocalizacao(m.getCoisa()));
        
        emprestaveis.addAll(mochilaDAO.findByNome(m.getCoisa()));
        emprestaveis.addAll(mochilaDAO.findByProprietario(m.getCoisa()));
        emprestaveis.addAll(mochilaDAO.findByLocalizacao(m.getCoisa()));
        
        emprestaveis.addAll(modemDAO.findByNome(m.getCoisa()));
        emprestaveis.addAll(modemDAO.findByProprietario(m.getCoisa()));
        emprestaveis.addAll(modemDAO.findByLocalizacao(m.getCoisa()));
        
        emprestaveis.addAll(projetorDAO.findByNome(m.getCoisa()));
        emprestaveis.addAll(projetorDAO.findByProprietario(m.getCoisa()));
        emprestaveis.addAll(projetorDAO.findByLocalizacao(m.getCoisa()));

        List<EmprestavelVO> vos = new ArrayList<EmprestavelVO>();
        for (Emprestavel emprestavel : emprestaveis) {
            vos.add(new EmprestavelVO(emprestavel.getNome(), emprestavel.getProprietario().getNome(), emprestavel.getLocalizacao()
                    .getNome()));
        }

        return vos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ConsultaCoisasFilter createModel() {
        return new ConsultaCoisasFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<EmprestavelVO> table) {
        table.addStringColumn("nome", "Nome", "nome");
        table.addStringColumn("proprietario", "Proprietário", "proprietario");
        table.addStringColumn("localizacao", "Localização", "localizacao");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ButtonCommand> getPageCommands() {
        List<ButtonCommand> commands = new ArrayList<ButtonCommand>();
        commands.add(new SearchCommand(this) {
            @Override
            public boolean isPrimaryButton() {
                return true;
            }
        });
        return commands;
    }

    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // protected List<ImageCommand> getTableCommands(Emprestavel entity) {
    // List<ImageCommand> commands = new ArrayList<ImageCommand>();
    // return commands;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Panel createSearchResultPanel(String id, ReportBuilder reportBuilder) {
        this.applyRenderingParameters(reportBuilder);
        return new BaseResultPanel<EmprestavelVO>(id, reportBuilder.createReport(), new BaseResultTableProvider<EmprestavelVO>(
                this.getEntityClass(), this)) {

            @Override
            protected Component createReportTitle(String id) {
                return new Label(id, getSearchHandler().search(daoFactory).size() + " resultado(s) encontrado(s) na pesquisa.");
            }

        };
    }

}
