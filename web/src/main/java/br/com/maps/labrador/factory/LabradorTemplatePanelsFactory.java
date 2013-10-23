package br.com.maps.labrador.factory;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

import jmine.tec.web.wicket.pages.factory.BootstrapTemplatePanelsFactory;

public class LabradorTemplatePanelsFactory extends BootstrapTemplatePanelsFactory {

    @Override
    public Panel createFooterPanel(String id) {
        return new EmptyPanel(id);
    }
    
}
