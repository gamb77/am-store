package org.hopto.gamb.components;

import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.hopto.gamb.AMUIApplication;
import org.hopto.gamb.SpringUtils;
import org.hopto.gamb.data.AMItemService;
import org.hopto.gamb.domain.service.entities.Ad;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.context.ApplicationContext;

@RunWith(PowerMockRunner.class)
//@PrepareForTest({AMMainWindow.class})
public class AMMainWindowTests {
    @Mock
    ApplicationContext ctx;
    @Mock
    AMItemService itemService; 

    /**
     * Only SpringUtils things are mocked since window component hierarchy goes so deeply on the vaadin FW and setContent(mainLayout) will 
     * cause painful things to cover up by mocking up all of classes over the AMMainWindow 
     * 
     * @throws Exception 
     */
    @Test
    public void testInitComponents() {
        AMMainWindow instance = new AMMainWindow();
        Whitebox.setInternalState(SpringUtils.class, "ctx", ctx);

        //Dummy data
        List<Ad> ads = new ArrayList<>();
        
        EasyMock.expect(ctx.getBean(AMItemService.class)).andReturn(itemService);
        EasyMock.expect(itemService.findAll("")).andReturn(ads);
        
        PowerMock.replayAll();
        instance.initComponents();
        PowerMock.verifyAll();        
        
        Assert.assertTrue(instance.getCaption().equals(AMUIApplication.APPLICATION_TITLE));
        VerticalLayout mainLayout = (VerticalLayout)instance.getContent();
        Assert.assertTrue(mainLayout.getComponentCount() == 2);
        Assert.assertTrue(mainLayout.getExpandRatio(mainLayout.getComponent(0)) == 5f);
        Assert.assertTrue(mainLayout.getExpandRatio(mainLayout.getComponent(1)) == 20f);        
    }
    
}
