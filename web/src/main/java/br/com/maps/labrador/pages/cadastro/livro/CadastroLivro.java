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
import jmine.tec.web.wicket.behavior.OnBlurAjaxBehavior;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.maps.labrador.LabradorWebException;
import br.com.maps.labrador.LabradorWebMessages;
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
        final TextField<Object> isbnTextField = ComponentHelper.createTextField("isbn10");
        final TextField<Object> isbn13 = ComponentHelper.createTextField("isbn13");
        final TextField<Object> titulo = ComponentHelper.createTextField("titulo");
        final TextField<Object> autor = ComponentHelper.createTextField("autor");
        final TextField<Object> editora = ComponentHelper.createTextField("editora");
        
        //components.add(ComponentHelper.createTextField("nome"));
       // components.add(ComponentHelper.createTextField("descricao"));
        isbnTextField.add(new OnBlurAjaxBehavior() {
			
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                try {
                    CadastroLivro.this.parseJsonObject(isbnTextField);
                    target.add(isbn13);
                    target.add(titulo);
                    target.add(autor);
                    target.add(editora);
                } catch (LabradorWebException e) {
                	Component feedBackMessage = get("feedback");
                	feedBackMessage.warn(e.getLocalizedMessageHolder().getMessage());
                    target.add(feedBackMessage);
                }
            }
        });
  
        components.add(isbnTextField.setOutputMarkupId(true));
        components.add(isbn13.setOutputMarkupId(true));
        components.add(titulo.setOutputMarkupId(true));
        components.add(autor.setOutputMarkupId(true));
		components.add(editora.setOutputMarkupId(true));
        return components;
    }

    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    private void parseJsonObject(TextField textField) {
    	String isbn10 = ((String) textField.getModelObject()).toUpperCase();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL("http://wwwb.isbndb.com/api/v2/json/" + MAPS_ISBNDB_COD + "/book/"+isbn10+"").toURI().toURL();
            try {
                Map<String, Object> mp = mapper.readValue(url, new TypeReference<Map<String, Object>>() {
                });
                
            	if(mp.get("error") != null){
            		 throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
        		}
                
                hidrateEntity(mp, getEntity());
            } catch (JsonParseException e) {
                e.printStackTrace();
                throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
            } catch (JsonMappingException e) {
            	throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
            } catch (IOException e) {
            	throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
            }
        } catch (MalformedURLException e) {
        	throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
        } catch (URISyntaxException e) {
        	throw new LabradorWebException(LabradorWebMessages.FALHA_OBTER_DADOS_ISBN.create(isbn10));
        }
    }

	private void hidrateEntity(Map<String, Object> mp, Livro livro) {
		Map<String, Object>  map =  ( Map<String, Object>) ((List) mp.get("data")).get(0);
		livro.setIsbn10(map.get("isbn10").toString());
		livro.setIsbn13(map.get("isbn13").toString());
		livro.setEditora(map.get("publisher_text").toString());
		livro.setTitulo(map.get("title").toString());
		livro.setAutor(((Map<String, Object>)((List) map.get("author_data")).get(0)).get("name").toString());
	}
}
