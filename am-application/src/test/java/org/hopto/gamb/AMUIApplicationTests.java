package org.hopto.gamb;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.hopto.gamb.components.AMMainWindow;
import org.hopto.gamb.data.AMItemService;
import org.hopto.gamb.domain.service.entities.Ad;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.context.ApplicationContext;


@RunWith(PowerMockRunner.class)
@PrepareForTest({AMMainWindow.class, SpringUtils.class})
public class AMUIApplicationTests {
    @Mock
    AMItemService itemService;     
    @Mock
    AMMainWindow mainWindow;
    @Mock
    VaadinRequest request;
    @Mock
    ApplicationContext ctx;
    
    
    @Test
    public void testInit() throws Exception{
    	AMUIApplication instance = new AMUIApplication();
        Whitebox.setInternalState(SpringUtils.class, "ctx", ctx);

        //Dummy data
        List<Ad> ads = new ArrayList<>();
        
        //Setting up
        EasyMock.expect(ctx.getBean(AMItemService.class)).andReturn(itemService);
        EasyMock.expect(itemService.findAll("")).andReturn(ads);

        PowerMock.replayAll();
        instance.init(request);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(instance);
        Window window = instance.getWindows().iterator().next();
        Assert.assertFalse(window.isClosable());
        Assert.assertFalse(window.isResizable());
    }
    
    @Test
    public void testMyUIServlet() {
        VaadinServlet servlet = AMUIApplication.MyUIServlet.getCurrent();
        Assert.assertNull(servlet);
    }
}
