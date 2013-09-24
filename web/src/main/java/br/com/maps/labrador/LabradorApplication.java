package br.com.maps.labrador;

import jmine.tec.rtm.impl.RtmController;
import jmine.tec.security.api.SecurityManager;
import jmine.tec.security.web.WebSecurityContext;
import jmine.tec.web.wicket.application.JMineWicketWebApplication;
import jmine.tec.web.wicket.pages.main.Logout;
import jmine.tec.web.wicket.security.SecureSession;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.LastModifiedResourceVersion;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import br.com.maps.labrador.pages.consulta.emprestavel.ConsultaEmprestavel;
import br.com.maps.labrador.pages.login.LabradorLogin;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * Starting point webApplication
 * 
 * @author takeshi
 */
public class LabradorApplication extends JMineWicketWebApplication {

    private SecurityManager<WebSecurityContext> securityManager;

    private RtmController rtmController;

    private String style;

    private IAuthorizationStrategy authorizationStrategy;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init() {
        super.init();

        // desligando modo debug
        this.getDebugSettings().setDevelopmentUtilitiesEnabled(false);
        this.getDebugSettings().setAjaxDebugModeEnabled(false);

        // configuração do wicket-bootstrap
        Bootstrap.install(Application.get(), new BootstrapSettings());
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        this.getSecuritySettings().setAuthorizationStrategy(this.authorizationStrategy);
        this.getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
        IResourceSettings resourceSettings = this.getResourceSettings();
        // resourceSettings.addResourceFolder("");
        resourceSettings.setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(new LastModifiedResourceVersion()));

        this.mountPage("logout", Logout.class);
        this.mountPage("login", LabradorLogin.class);
        this.mountPage("accessDenied", AccessDeniedPage.class);
        this.mountPage("home", ConsultaEmprestavel.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return ConsultaEmprestavel.class;
    }

    /**
     * @return the securityManager
     */
    public SecurityManager<WebSecurityContext> getSecurityManager() {
        return securityManager;
    }

    /**
     * @param securityManager the securityManager to set
     */
    public void setSecurityManager(SecurityManager<WebSecurityContext> securityManager) {
        this.securityManager = securityManager;
    }

    /**
     * @return the rtmController
     */
    public RtmController getRtmController() {
        return rtmController;
    }

    /**
     * @param rtmController the rtmController to set
     */
    public void setRtmController(RtmController rtmController) {
        this.rtmController = rtmController;
    }

    /**
     * @return the authorizationStrategy
     */
    public IAuthorizationStrategy getAuthorizationStrategy() {
        return authorizationStrategy;
    }

    /**
     * @param authorizationStrategy the authorizationStrategy to set
     */
    public void setAuthorizationStrategy(IAuthorizationStrategy authorizationStrategy) {
        this.authorizationStrategy = authorizationStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session newSession(Request request, Response response) {
        SecureSession session = new SecureSession(request);
        session.setSecurityManager(this.securityManager);
        if (!StringUtils.isEmpty(this.style)) {
            session.setStyle(this.style);
        }

        return session;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
