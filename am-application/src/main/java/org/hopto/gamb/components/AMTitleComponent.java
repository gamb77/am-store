package org.hopto.gamb.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * Top level component of the application
 *
 * @author jani
 */
public class AMTitleComponent extends AMAbstractComponent {

    private VerticalLayout mainLayout; 
    private Label title;
    private Label slogan;
    
    
    @Override
    public void initComponent() {
        //Mainlayout
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        title = new Label("AM Store");
        title.addStyleName(Runo.LABEL_H1);
        title.setSizeUndefined();
        mainLayout.addComponent(title);
        mainLayout.setComponentAlignment(title, Alignment.TOP_CENTER);
        
        slogan = new Label(
                "http://mepa-store-api.herokuapp.com/marketads");
        slogan.addStyleName(Runo.LABEL_SMALL);
        slogan.setSizeUndefined();
        mainLayout.addComponent(slogan);
        mainLayout.setComponentAlignment(slogan, Alignment.TOP_CENTER);

        setContent(mainLayout);
        
        activateComponent();
    }

    @Override
    protected void activateComponent() {
        
    }
    
}
