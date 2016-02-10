package org.hopto.gamb.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VerticalLayout.class, AMTitleComponent.class, Label.class, Panel.class})
public class AMTitleComponentTests {
    
    @Mock
    Panel panel;    
    @Mock
    VerticalLayout mainLayout; 
    @Mock
    Label title;
    @Mock
    Label slogan;

    
    @Test
    public void testInitComponent() throws Exception {
    	AMTitleComponent instance = new AMTitleComponent();
        Whitebox.setInternalState(instance, "mainLayout", mainLayout);
        Whitebox.setInternalState(instance, "title", title);
        Whitebox.setInternalState(instance, "slogan", slogan);
        Whitebox.setInternalState(instance, "panel", panel);
                
        //Setting up data to mock
        PowerMock.expectNew(VerticalLayout.class).andReturn(mainLayout);
        mainLayout.setSizeFull();
        PowerMock.expectNew(Label.class, "AM Store").andReturn(title);
        title.addStyleName(Runo.LABEL_H1);
        title.setSizeUndefined();
        mainLayout.addComponent(title);
        mainLayout.setComponentAlignment(title, Alignment.TOP_CENTER);
        PowerMock.expectNew(Label.class, "http://mepa-store-api.herokuapp.com/marketads").andReturn(slogan);
        slogan.addStyleName(Runo.LABEL_SMALL);
        slogan.setSizeUndefined();
        mainLayout.addComponent(slogan);
        mainLayout.setComponentAlignment(slogan, Alignment.TOP_CENTER);        
        panel.setContent(mainLayout);
        
        PowerMock.replayAll();
        instance.initComponent();
        PowerMock.verifyAll();
    }
    
}
