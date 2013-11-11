package br.com.maps.labrador.service.validar;

import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.impl.AbstractValidationService;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço que valida um {@link AbstractEmprestavel}.
 * 
 * @author laercio.duarte
 * @created Sep 27, 2013
 */
public abstract class AbstractEmprestavelService<T extends AbstractEmprestavel> extends AbstractValidationService {

    private static final String COISA = "Coisa";

    private static final String NOME = "Nome";

    private static final String PROPRIETARIO = "Proprietario";

    private static final String LOCALIZACAO = "Localização";

    private T coisa;

    private String nome;

    private LabradorUsuario proprietario;

    private LocalizacaoEmprestavel localizacao;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void validate() throws ServiceExecutionException {
        this.assertEquals(NOME, this.nome, this.coisa.getNome());
        this.assertEquals(PROPRIETARIO, this.proprietario, this.coisa.getProprietario());
        this.assertEquals(LOCALIZACAO, this.localizacao.getNome(), this.coisa.getLocalizacao().getNome());

        this.doSpecificValidations(this.coisa);
    }

    protected void doSpecificValidations(T coisa) {
        // hook para ser sobreescrito caso necessário
    }

    /**
     * @param coisa the coisa to set
     */
    @Input(fieldName = COISA)
    public void setCoisa(T coisa) {
        this.coisa = coisa;
    }

    /**
     * @param nome the nome to set
     */
    @Input(fieldName = NOME)
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param usuario the usuario to set
     */
    @Input(fieldName = PROPRIETARIO)
    public void setProprietario(LabradorUsuario proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * @param localizacao the localizacao to set
     */
    @Input(fieldName = LOCALIZACAO)
    public void setLocalizacao(LocalizacaoEmprestavel localizacao) {
        this.localizacao = localizacao;
    }
}
