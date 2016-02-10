package org.hopto.gamb;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;
import org.hopto.gamb.components.AMMainWindow;

/**
 * Vaadin UI application. 
 *
 * @author jani
 */
@SpringUI
@Theme("valo")
public class AMUIApplication extends UI {
    public static final String APPLICATION_TITLE = "AM Store";

    private AMMainWindow mainWindow = null;
    
    @Override
    protected void init(VaadinRequest request) {  
        mainWindow = new AMMainWindow();
        mainWindow.initComponents();
        mainWindow.setClosable(false);
        mainWindow.setResizable(false);
        mainWindow.setSizeFull();
        addWindow(mainWindow);
    }

    /**
     * Deploy as servlet. 
     * 
     */
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = AMUIApplication.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }  
    
}
