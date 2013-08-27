package br.com.maps.labrador.pages.cadastro.livro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.maps.labrador.domain.Livro;

public class CadastroLivro extends FormPage<Livro> {

    private static final String MAPS_ISBNDB_COD = "SQGBZAKH";

    public CadastroLivro(Class<? extends Page> source, PageParameters sourcePageParameters) {
        super(source, sourcePageParameters);
    }

    public CadastroLivro(Class<? extends Page> source, PageParameters sourcePageParameters, br.com.maps.labrador.domain.Livro entity,
            FormType formType) {
        super(source, sourcePageParameters, entity, formType);
    }

    public CadastroLivro(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        final TextField<Object> isbnTextField = ComponentHelper.createTextField("isbn");
        components.add(isbnTextField);
        components.add(ComponentHelper.createTextField("nome"));
        components.add(ComponentHelper.createTextField("descricao"));
        isbnTextField.add(new OnChangeAjaxBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                try {
                    CadastroLivro.this.parseJsonObject((Livro) isbnTextField.getModelObject());
                    target.add(CadastroLivro.this.get("nome"));
                    target.add(CadastroLivro.this.get("descricao"));
                } catch (Exception e) {
                    CadastroLivro.this.info("Não foi possível obter as informações");
                }
            }
        });

        return components;
    }

    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    private void parseJsonObject(Livro livro) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL("http://wwwb.isbndb.com/api/v2/json/" + MAPS_ISBNDB_COD + "/book/0132350882").toURI().toURL();
            try {
                Map<String, Object> mp = mapper.readValue(url, new TypeReference<Map<String, Object>>() {
                });
                System.out.println("oi");
            } catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // @JsonIgnoreProperties(ignoreUnknown = true)
    // public class Livro {
    //
    // private String title;
    //
    // @JsonCreator
    // public Livro() {
    // super();
    // }
    //
    // /**
    // * @return the title
    // */
    // public String getTitle() {
    // return this.title;
    // }
    //
    // /**
    // * @param title the title to set
    // */
    // public void setTitle(String title) {
    // this.title = title;
    // }
    // }

}
