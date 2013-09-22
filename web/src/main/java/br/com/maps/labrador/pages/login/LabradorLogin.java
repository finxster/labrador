package br.com.maps.labrador.pages.login;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.security.api.SecurityURLMapping;
import jmine.tec.web.wicket.header.AbstractHeaderInitializer;
import jmine.tec.web.wicket.pages.Template;
import jmine.tec.web.wicket.pages.main.LoginConfig;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Página de login específica para o Labrador.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
public class LabradorLogin extends Template {

    /**
     * C'tor
     */
    public LabradorLogin() {
        super();
    }

    /**
     * C'tor
     * 
     * @param parameters {@link PageParameters}
     */
    public LabradorLogin(PageParameters parameters) {
        super(parameters);
    }

    /**
     * Redireciona se for necessário
     */
    private void redirectIfNecessary() {
        if (SecurityURLMapping.getInstance().getLoginURL() != null) {
            // must redirect
            RequestCycle.get().scheduleRequestHandlerAfterCurrent(
                    new RedirectRequestHandler(SecurityURLMapping.getInstance().getLoginURL()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();
        this.get("breadcrumbPanel").setVisible(false);
        this.redirectIfNecessary();
        this.generateComponents();
    }

    /**
     * Inicializa o formulario
     */
    private void generateComponents() {
        this.add(new Label("titleSystemName", this.getSystemName()));
        this.add(new FeedbackPanel("feedback_login"));
        this.add(this.getFeedContainer("feedContainer"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getPagePath() {
        return new String[]{};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Panel getHeaderPanel(String id) {
        return new LabradorLoginPanel(id, this.getSystemName(), this.getSecurityService());
        // return new TesteLoginPanel(id);
    }

    /**
     * Retorna um componente para feed na tela de login.
     * 
     * @param wicketId o wicketId do componente.
     * @return um componente para feed na tela de login.
     */
    protected Component getFeedContainer(String wicketId) {
        return new FeedContainer(wicketId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        for (AbstractHeaderInitializer initializer : this.getLoginConfig().getHeaderInitializers()) {
            initializer.renderHead(response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    /**
     * @return a configuração da página de login.
     */
    private LoginConfig getLoginConfig() {
        return (LoginConfig) this.getApplicationStyle().getConfig(LoginConfig.KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Panel getFooterPanel(String id) {
        return new EmptyPanel(id);
    }

}
