package br.com.maps.labrador.pages.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jmine.tec.persist.api.DAOFactory;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.model.DefaultDetachableModel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.dao.LivroDAO;
import br.com.maps.labrador.dao.MochilaDAO;
import br.com.maps.labrador.dao.ModemDAO;
import br.com.maps.labrador.dao.ProjetorDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.pages.cadastro.livro.CadastroLivro;
import br.com.maps.labrador.pages.consulta.emprestavel.ConsultaEmprestavel;
import br.com.maps.labrador.pages.consulta.emprestavel.EmprestavelVO;

/**
 * Página principal do Labrador.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class LabradorMain extends WebPage {

    @SpringBean
    private DAOFactory daoFactory;

    private String query;

    private List<EmprestavelVO> resultados = new ArrayList<EmprestavelVO>();

    private boolean cadastrosPanelVisibility = false;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.addFeedbackPanel();
        this.addForm();
        this.addLinks();
        this.addPanels();
        this.search();
        this.addResultGridView();
    }

    private void addPanels() {
        // WebMarkupContainer containerCadastros = new WebMarkupContainer("containerCadastros");
        CadastrosPanel cadastrosPanel = new CadastrosPanel("cadastros");// {
        // public boolean isVisible() {
        // return cadastrosPanelVisibility;
        //
        // };
        // };
        cadastrosPanel.setOutputMarkupPlaceholderTag(true);
        cadastrosPanel.setVisible(false);
        // containerCadastros.setOutputMarkupId(true);
        // containerCadastros.add(cadastrosPanel);
        // this.add(containerCadastros);
        this.add(cadastrosPanel);

    }

    private void addResultGridView() {
        IDataProvider<EmprestavelVO> dataProvider = new IDataProvider<EmprestavelVO>() {

            public void detach() {
                resultados = new ArrayList<EmprestavelVO>();
            }

            public Iterator<? extends EmprestavelVO> iterator(long first, long count) {
                search();
                return resultados.iterator();
            }

            public long size() {
                return resultados.size();
            }

            public IModel<EmprestavelVO> model(EmprestavelVO object) {
                return new DefaultDetachableModel<EmprestavelVO>(object);
            }
        };

        GridView<EmprestavelVO> gridView = new GridView<EmprestavelVO>("pesquisa", dataProvider) {

            @Override
            protected void populateEmptyItem(Item<EmprestavelVO> item) {
                item.add(new EmptyPanel("painelPrincipal"));

            }

            @Override
            protected void populateItem(Item<EmprestavelVO> item) {
                EmprestavelVO vo = item.getModelObject();
                item.add(new EmprestavelPanel("painelPrincipal", vo));
            }
        };

        // gridView.setRows(4);
        gridView.setColumns(2);

        this.add(gridView);
    }

    private void addFeedbackPanel() {
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        this.add(feedbackPanel);
    }

    private void addLinks() {

        Link<Void> linkHome = new Link<Void>("linkHome") {
            @Override
            public void onClick() {
                setResponsePage(ConsultaEmprestavel.class);
            }
        };
        this.add(linkHome);

        AjaxLink<Void> linkCadastro = new AjaxLink<Void>("linkCadastro") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                CadastrosPanel cadastrosPanel = (CadastrosPanel) LabradorMain.this.get("cadastros");
                cadastrosPanel.setVisible(!cadastrosPanel.isVisible());
                target.add(cadastrosPanel);
            }
        };

        this.add(linkCadastro);
    }

    private void addForm() {
        Form<EmprestavelVO> form = new Form<EmprestavelVO>("mainForm") {
            @Override
            protected void onSubmit() {
                search();
            }
        };
        form.setModel(new CompoundPropertyModel<EmprestavelVO>(new EmprestavelVO()));
        form.add(ComponentHelper.createTextField("busca", this, "query"));
        this.add(form);
    }

    private void search() {
        LivroDAO livroDAO = daoFactory.getDAOByClass(LivroDAO.class);
        MochilaDAO mochilaDAO = daoFactory.getDAOByClass(MochilaDAO.class);
        ModemDAO modemDAO = daoFactory.getDAOByClass(ModemDAO.class);
        ProjetorDAO projetorDAO = daoFactory.getDAOByClass(ProjetorDAO.class);

        this.resultados = new ArrayList<EmprestavelVO>();
        String query = this.getQuery();
        Set<AbstractEmprestavel> emprestaveis = new HashSet<AbstractEmprestavel>();
        emprestaveis.addAll(livroDAO.findByTitulo(query));
        emprestaveis.addAll(livroDAO.findByProprietario(query));
        emprestaveis.addAll(livroDAO.findByLocalizacao(query));

        emprestaveis.addAll(mochilaDAO.findByLikeNaturalKey(query));
        emprestaveis.addAll(mochilaDAO.findByProprietario(query));
        emprestaveis.addAll(mochilaDAO.findByLocalizacao(query));

        emprestaveis.addAll(modemDAO.findByNome(query));
        emprestaveis.addAll(modemDAO.findByProprietario(query));
        emprestaveis.addAll(modemDAO.findByLocalizacao(query));

        emprestaveis.addAll(projetorDAO.findByNome(query));
        emprestaveis.addAll(projetorDAO.findByProprietario(query));
        emprestaveis.addAll(projetorDAO.findByLocalizacao(query));

        for (AbstractEmprestavel emprestavel : emprestaveis) {
            this.resultados.add(new EmprestavelVO(emprestavel.getNome(), emprestavel.getProprietario().getNome(), emprestavel
                    .getLocalizacao().getNome(), emprestavel.getStatus().toString()));
        }
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the resultados
     */
    public List<EmprestavelVO> getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(List<EmprestavelVO> resultados) {
        this.resultados = resultados;
    }

    /**
     * @return the cadastrosPanelVisibility
     */
    public boolean getCadastrosPanelVisibility() {
        return cadastrosPanelVisibility;
    }

}
