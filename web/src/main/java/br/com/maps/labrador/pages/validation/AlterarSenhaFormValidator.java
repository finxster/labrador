package br.com.maps.labrador.pages.validation;

import br.com.maps.labrador.pages.messages.ErrorMessages;
import jmine.tec.web.wicket.pages.validator.AbstractFormValidator;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.security.impl.domain.User;
import jmine.tec.utils.md5sum.MD5SumTool;

/**
 * Valida que a nvoa senha é diferente da anterior e que a nova senha foi digitada corretamente duas vezes.
 * 
 * @author renan
 */
public class AlterarSenhaFormValidator extends AbstractFormValidator {

    private static final long serialVersionUID = 1L;

    private final FormComponent<?>[] components;

    private User user;

    /**
     * @param currentPassword a senha atual
     * @param password a nova senha
     * @param retypePassword a verificação da nova senha
     * @param user o usuário atual
     */
    public AlterarSenhaFormValidator(PasswordTextField currentPassword, PasswordTextField password, PasswordTextField retypePassword,
            User user) {
        this.components = new FormComponent[]{ currentPassword, password, retypePassword };
        this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    public FormComponent<?>[] getDependentFormComponents() {
        return this.components;
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Form form) {

        FormComponent<?> currentPassword = this.components[0];
        FormComponent<?> password = this.components[1];
        FormComponent<?> retypePassword = this.components[2];

        boolean senhasAnteriorDiferente = !MD5SumTool.md5Sum(currentPassword.getInput()).equals(this.user.getPassword());
        boolean senhasDiferentes = !password.getInput().equals(retypePassword.getInput());
        if (senhasAnteriorDiferente) {
            LocalizedMessageHolder holder = ErrorMessages.SENHA_ANTERIOR_DIFERENTE.create();
            this.error(currentPassword, holder);
        }
        if (senhasDiferentes) {
            LocalizedMessageHolder holder = ErrorMessages.SENHAS_DIFERENTES.create();
            this.error(password, holder);
        }

    }

}
