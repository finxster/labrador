package br.com.maps.labrador.pages.main;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Modal with a {@link WebMarkupContainer} inside.
 * 
 * @author finx
 * @created Oct 4, 2013
 */
public class WebMarkupContainerModal extends Modal {

    private WebMarkupContainer content;

    public WebMarkupContainerModal(String markupId) {
        super(markupId);
        this.content = new WebMarkupContainer("content");
        this.add(this.content);
    }

    public void addContent(Component component) {
        this.content.add(component);
    }

    public MarkupContainer removeContent(Component component) {
        return this.content.remove(component);
    }

    /**
     * @return the content
     */
    public WebMarkupContainer getContent() {
        return this.content;
    }

}
