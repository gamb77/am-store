    package org.hopto.gamb.components;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;

/**
 * Abstract base component for common services. 
 *
 * @author jani
 */
public abstract class AMAbstractComponent extends CustomComponent {
    
    /**
     * Used for initialize layout without data
     */
    abstract public void initComponent();

    /**
     * Used for activate components with data
     */
    abstract protected void activateComponent();

    private final Panel panel = new Panel();

    public AMAbstractComponent() {
        setSizeFull();
        panel.setSizeFull();
        setCompositionRoot(panel);        
    }
    
    protected void setContent(ComponentContainer container) {
        panel.setContent(container);
    }

    
        
}
