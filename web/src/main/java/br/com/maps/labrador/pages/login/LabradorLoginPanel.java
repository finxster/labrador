package br.com.maps.labrador.pages.login;

import static jmine.tec.web.wicket.WebWicketMessages.PAGES_MAIN_LOGIN_DENIED;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.security.api.SecurityException;
import jmine.tec.security.api.SecurityService;
import jmine.tec.security.api.UserDataLogger;
import jmine.tec.security.web.WebSecurityContext;
import jmine.tec.security.web.WebSecurityManager;
import jmine.tec.web.wicket.security.SecureSession;
import jmine.tec.web.wicket.security.UserDetails;
import jmine.tec.web.wicket.upperCase.field.NoUpperCasePasswordTextField;
import jmine.tec.web.wicket.upperCase.field.NoUpperCaseTextFieldImpl;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.domain.contato.Contato;
import br.com.maps.labrador.pages.consulta.emprestavel.ConsultaEmprestavel;

public class LabradorLoginPanel extends Panel {

    private static final String ERROR_MESSAGE = "ERROR_MESSAGE";

    @SpringBean
    private WebSecurityManager securityManager;

    @SpringBean(name = "upperCaseLogin")
    private Boolean upperCaseLogin;

    @SpringBean(name = "userDataLogger")
    private UserDataLogger userDataLogger;

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

    @SpringBean(name = "hotPersister")
    private StatelessPersister<Contato> persister;

    private final String systemName;

    private final SecurityService securityService;

    /**
     * Construtor
     * 
     * @param id - o id do painel
     * @param systemName - o nome do sistema
     * @param securityService - o security service
     */
    public LabradorLoginPanel(String id, String systemName, SecurityService securityService) {
        super(id);
        this.systemName = systemName;
        this.securityService = securityService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();
        this.generateComponents();
        this.generateComponentsContato();
    }

    private void generateComponentsContato() {
        DAO<Contato> dao = this.daoFactory.getDAOByEntityType(Contato.class);
        Contato contato = dao.createBean();

        Form<Contato> formContato = new Form<Contato>("formContato", new CompoundPropertyModel<Contato>(contato));
        TextField<String> nome = new TextField<String>("nome");
        nome.setRequired(true);
        formContato.add(nome);

        TextField<String> email = new TextField<String>("email");
        email.setRequired(true);
        formContato.add(email);

        TextField<String> assunto = new TextField<String>("assunto");
        assunto.setRequired(true);
        formContato.add(assunto);

        TextArea<String> mensagem = new TextArea<String>("mensagem");
        mensagem.setRequired(true);
        formContato.add(mensagem);

        AjaxSubmitLink gravar = new AjaxSubmitLink("gravar", formContato) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                Contato contato = (Contato) form.getDefaultModelObject();
                LabradorLoginPanel.this.persister.save(contato);
            }
        };
        formContato.add(gravar);
        this.add(formContato);
    }

    /**
     * Gera os componentes do painel
     */
    private void generateComponents() {
        this.add(this.getUserForm());
        this.defineResponsePage();

        HttpSession session = ((ServletWebRequest) RequestCycle.get().getRequest()).getContainerRequest().getSession();
        Object object = session.getAttribute(ERROR_MESSAGE);
        if (object != null) {
            session.removeAttribute(ERROR_MESSAGE);
            this.error(String.valueOf(object));
        }
    }

    /**
     * 
     */
    private void defineResponsePage() {
        if (SecureSession.getSecureSession().getSubject() != null) {
            this.setResponsePage(this.getResponsePage());
        }
        final Subject subject = this.securityService.getCurrentThreadSubject();
        if (subject != null) {
            // maybe first access and already logged in. Its safe to redirect.
            SecureSession.getSecureSession().setSubject(subject);
            this.setResponsePage(this.getResponsePage());
        }
    }

    /**
     * Cria e devolve o {@link Form} para usuario
     * 
     * @return {@link Form}
     */
    private Form<UserDetails> getUserForm() {
        UserDetails userDetails = new UserDetails();
        final Form<UserDetails> form = new Form<UserDetails>("securityForm") {
            @Override
            protected void onSubmit() {
                final UserDetails modelObject = this.getModelObject();
                SecureSession secureSession = SecureSession.getSecureSession();
                try {
                    HttpServletRequest request = (HttpServletRequest) this.getWebRequest().getContainerRequest();
                    HttpServletResponse response = (HttpServletResponse) this.getResponse().getContainerResponse();
                    WebSecurityContext context = new WebSecurityContext(request, response);
                    Subject subject;
                    subject = LabradorLoginPanel.this.securityManager.login(modelObject.getUsername(), modelObject.getPassword(), context);
                    if (subject != null) {
                        LabradorLoginPanel.this.securityManager.storeSubject(context, subject);
                        secureSession.setSubject(subject);
                        this.setResponsePage(LabradorLoginPanel.this.getResponsePage());
                        LabradorLoginPanel.this.logUserLoginData(secureSession.getId(), modelObject.getUsername());
                    } else {
                        throw new SecurityException(PAGES_MAIN_LOGIN_DENIED.create());
                    }
                } catch (SecurityException e) {
                    ((ServletWebRequest) RequestCycle.get().getRequest()).getContainerRequest().getSession()
                            .setAttribute(ERROR_MESSAGE, e.getLocalizedMessage());
                    secureSession.getFeedbackMessages().add(this, e.getLocalizedMessage(), FeedbackMessage.ERROR);
                }
            }

        };
        form.setModel(new CompoundPropertyModel<UserDetails>(userDetails));

        // form.add(new Label("systemName", this.systemName));

        // ControlGroup userControlGroup = new ControlGroup("user.control-group");
        // userControlGroup.add(this.getUsernameField());
        // form.add(userControlGroup);
        form.add(this.getUsernameField());

        // ControlGroup passControlGroup = new ControlGroup("pass.control-group");
        // passControlGroup.add(this.getPasswordField());
        // form.add(passControlGroup);
        form.add(this.getPasswordField());

        return form;
    }

    /**
     * @return o input do nome de usuário
     */
    private TextField<String> getUsernameField() {
        TextField<String> usernameField;
        if (!this.upperCaseLogin) {
            usernameField = new NoUpperCaseTextFieldImpl("username");
        } else {
            usernameField = new TextField<String>("username");
        }

        usernameField.setRequired(true);
        usernameField.setLabel(new Model<String>("Usuário"));

        return usernameField;
    }

    /**
     * @return o input da password do usuário
     */
    private PasswordTextField getPasswordField() {
        PasswordTextField passwordField;
        if (!this.upperCaseLogin) {
            passwordField = new NoUpperCasePasswordTextField("password");
        } else {
            passwordField = new PasswordTextField("password");
        }

        passwordField.setRequired(true);
        passwordField.setLabel(new Model<String>("Senha"));

        return passwordField;
    }

    /**
     * Registra o acesso do usuário ao sistema.
     * 
     * @param sessionId o id da sessão.
     * @param username o nome do usuário.
     */
    private void logUserLoginData(String sessionId, String username) {
        if (this.userDataLogger != null) {
            this.userDataLogger.login(username, sessionId, String.valueOf(this.securityService.getSystemId()));
        }
    }

    /**
     * Hook para sobrescrever a page que será redirecionada após o login.
     * 
     * @return Class
     */
    protected Class<? extends Page> getResponsePage() {
        return ConsultaEmprestavel.class;
    }

}
