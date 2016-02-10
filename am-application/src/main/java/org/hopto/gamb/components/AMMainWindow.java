package org.hopto.gamb.components;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.hopto.gamb.AMUIApplication;

/**
 * Main window over the application
 *
 * @author jani
 */
public class AMMainWindow extends Window {
    private AMTitleComponent title;
    private AMMainComponent main;
    
    public AMMainWindow() {
        super(AMUIApplication.APPLICATION_TITLE);        
    }
    
    public void initComponents() {
        title = new AMTitleComponent();
        main = new AMMainComponent();

        //Set main layout
    	VerticalLayout mainLayout = new VerticalLayout();
    	mainLayout.setSizeFull();
    	mainLayout.setMargin(true);
    	
    	//Toplevel component
    	title.initComponent();    	
    	mainLayout.addComponent(title);
    	mainLayout.setExpandRatio(title, 5f);

    	//Main component        
    	main.initComponent();    	
    	mainLayout.addComponent(main);
    	mainLayout.setExpandRatio(main, 20f);

    	setContent(mainLayout);
    }
    
}
