package br.com.maps.labrador.pages.consulta.emprestimo;

import java.io.Serializable;

import jmine.tec.web.wicket.component.injection.FormInputProvider;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInput;
import br.com.maps.labrador.domain.Livro;

/**
 * Filtro para a tela de empréstimos.
 * 
 * @author finx
 * @created Aug 27, 2013
 */
@FormInputProvider
public class PesquisaEmprestimoFilter implements Serializable {

    private Livro livro;

    /**
     * @return the livro
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    @LabeledFormInput(label = "Livro")
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

}
