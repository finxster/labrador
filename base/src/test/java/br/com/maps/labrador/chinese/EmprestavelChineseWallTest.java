package br.com.maps.labrador.chinese;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAOFactory;
import jmine.tec.security.api.SecurityPrincipal;
import jmine.tec.security.api.authorization.Permission;
import jmine.tec.security.api.chinesewall.ChineseWallContext;
import jmine.tec.security.api.chinesewall.ChineseWallContextHolder;
import jmine.tec.security.impl.dao.ChineseWallCredentialDAO;
import jmine.tec.security.impl.dao.ChineseWallEntityIdDAO;
import jmine.tec.security.impl.dao.GroupDAO;
import jmine.tec.security.impl.dao.SecurityClientDAO;
import jmine.tec.security.impl.dao.UserDAO;
import jmine.tec.security.impl.domain.ChineseWallCredential;
import jmine.tec.security.impl.domain.ChineseWallEntityId;
import jmine.tec.security.impl.domain.Group;
import jmine.tec.security.impl.domain.SecurityClient;
import jmine.tec.security.impl.domain.User;
import jmine.tec.services.impl.testcase.DefaultRefDBTestCase;
import br.com.maps.labrador.dao.LivroDAO;
import br.com.maps.labrador.domain.emprestavel.Emprestavel;
import br.com.maps.labrador.domain.livro.Livro;

/**
 * Teste de ChineseWall para a entidade {@link Emprestavel}.
 * 
 * @author diego.ferreira
 * @date Dec 14, 2011
 */
public class EmprestavelChineseWallTest extends DefaultRefDBTestCase {

    private static Subject subject;

    private static UserDAO userDAO;

    private static GroupDAO groupDAO;

    private static SecurityClientDAO securityClientDAO;

    private static ChineseWallCredentialDAO cwCredentialDAO;

    private static ChineseWallEntityIdDAO cwEntityIdDAO;

    private static LivroDAO livroDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        super.initializeTestData();
        BaseDAOFactory daoFactory = this.getController().getDAOFactory();
        userDAO = daoFactory.getDAOByClass(UserDAO.class);
        groupDAO = daoFactory.getDAOByClass(GroupDAO.class);
        securityClientDAO = daoFactory.getDAOByClass(SecurityClientDAO.class);
        cwCredentialDAO = daoFactory.getDAOByClass(ChineseWallCredentialDAO.class);
        livroDAO = daoFactory.getDAOByClass(LivroDAO.class);
        cwCredentialDAO = daoFactory.getDAOByClass(ChineseWallCredentialDAO.class);
        cwEntityIdDAO = daoFactory.getDAOByClass(ChineseWallEntityIdDAO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();

        this.getEnvironment().markDirty();
        this.getEnvironment().restart();

        // Security Client
        SecurityClient securityClient = securityClientDAO.createBean();
        securityClient.setName("BOOK SYSTEM");
        securityClient.getPersister().save();

        // Credential Chinese Wall
        ChineseWallCredential chineseWallCredentialFundo = cwCredentialDAO.createBean();
        chineseWallCredentialFundo.setMnemonico("CREDENTIAL DE ACESSO LIMITADO (EMPRESTAVEL)");
        chineseWallCredentialFundo.setDescription("CREDENTIAL DE ACESSO LIMITADO (EMPRESTAVEL)");
        chineseWallCredentialFundo.setEntityName("EMPRESTAVEL");
        chineseWallCredentialFundo.setClient(securityClient);
        chineseWallCredentialFundo.setFullAccess(false);

        chineseWallCredentialFundo.getPersister().save();

        // Grupo
        Group group = groupDAO.createBean();
        group.setName("GRUPO TESTE");
        group.addCredential(chineseWallCredentialFundo);
        group.getPersister().save();

        // Usuário
        User user = userDAO.createBean();
        user.setUsername("USUARIO");
        user.setPassword("USUARIO");
        user.addGroup(group);
        user.getPersister().save();
    }

    /**
     * Teste de credential de chinese wall para entidade {@link Emprestavel} <br>
     * <br>
     * O teste consiste em:
     * <ul>
     * <li>Verificar que o usuário corrente possui acesso a todos os fundos presentes na base de referência antes da criação do contexto de
     * chinese wall.</li>
     * <li>Validar que o usuário possui acesso somente aos fundos associados a credential</li>
     * <li>Validar que o usuário <b>não</b> possui acesso aos fundos que não foram associados a credential</li>
     * </ul>
     * 
     * @throws Exception caso ocorra algum erro na execução do teste.
     */
    public void testChineseWallEmprestavel() throws Exception {

        Livro livro1 = livroDAO.findByNaturalKey("200 DICAS PARA EMAGRECER");

        // Consulta sem credential de chinesewall, acesso a todos os livros
        assertEquals(3, livroDAO.findAll().size());

        // Validando que o livro favorito do finx foi encontrado.
        assertNotNull(livro1);

        ChineseWallCredential chineseWallCredentialeEmprestavel =
                cwCredentialDAO.findByNaturalKey("CREDENTIAL DE ACESSO LIMITADO (EMPRESTAVEL)");

        // Entidade FI ARROJADO autorizada.
        ChineseWallEntityId chineWallEntityIdLivroFinx = cwEntityIdDAO.createBean();
        chineWallEntityIdLivroFinx.setEntityId(livro1.getId());
        chineWallEntityIdLivroFinx.setCredential(chineseWallCredentialeEmprestavel);

        chineseWallCredentialeEmprestavel.getIds().add(chineWallEntityIdLivroFinx);
        chineseWallCredentialeEmprestavel.getPersister().save();

        // Sincroniza o os dados criados antes da criação do contexto de chinese wall.
        this.getController().getSessionFactory().getCurrentSession().flush();

        Set<Permission> pubCredentials = new HashSet<Permission>();
        pubCredentials.add(chineseWallCredentialeEmprestavel.createPermission());

        User user = userDAO.findByNaturalKey("USUARIO");

        Set<Principal> principals = new HashSet<Principal>();
        principals.add(new SecurityPrincipal(user.getUsername()));

        subject = new Subject(false, principals, pubCredentials, new HashSet<Permission>());
        this.getController().getSecurityService().setCurrentThreadSubject(subject);

        // Validando o usuário na thread local
        assertEquals(user.getUsername(), this.getController().getSecurityService().getCurrentUser());

        ChineseWallContext chineseWallContext = new ChineseWallContext();
        chineseWallContext.addParameter(EmprestavelChineseWallEntity.FILTER_NAME, EmprestavelChineseWallEntity.PARAM_NAME,
                Collections.<Serializable> singleton(chineseWallCredentialeEmprestavel.getId()));
        ChineseWallContextHolder.set(chineseWallContext);

        // Validando o acesso a quantidade de fundos permitidos pela credential associada ao usuário corrente.
        assertEquals(1, livroDAO.findAll().size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        ChineseWallContextHolder.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getControllerSpringId() {
        return "labradorBaseController";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSpringMainXMLFilename() {
        return "base-test-beans.xml";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "base-db.xml" };
    }
}
