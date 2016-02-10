package org.hopto.gamb.components;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.hopto.gamb.SpringUtils;
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
@PrepareForTest({AMItemService.class, 
    AMMainComponent.class, 
    SpringUtils.class, Button.class, 
    Grid.class, 
    TextField.class, 
    Panel.class,
    AMItemForm.class
})
public class AMMainComponentTests {
    @Mock
    AMItemService itemService; 
    @Mock
    AMItemForm itemForm; 
    @Mock
    Grid itemList;
    @Mock
    TextField filter;
    @Mock
    Button newAd;
    @Mock
    ApplicationContext ctx;
    @Mock
    SelectionModel selectionModel;
    @Mock
    HorizontalLayout actions;
    @Mock
    VerticalLayout left;
    @Mock
    HorizontalLayout mainLayout;
    @Mock
    Panel panel;   
    @Mock
    BeanItemContainer beanItemContainer;
    @Mock
    Ad ad;
    
    
    @Test
    public void testInitComponent() throws Exception {
    	AMMainComponent instance = new AMMainComponent();
        Whitebox.setInternalState(instance, "itemService", itemService);
        Whitebox.setInternalState(SpringUtils.class, "ctx", ctx);
        Whitebox.setInternalState(instance, "panel", panel);
        
        //Dummy data
        List<Ad> ads = new ArrayList<>();
                
        PowerMock.expectNew(Button.class, "New ad").andReturn(newAd);        
        PowerMock.expectNew(TextField.class).andReturn(filter);        
        PowerMock.expectNew(Grid.class).andReturn(itemList);        
        PowerMock.expectNew(AMItemForm.class, new Class[] {AMMainComponent.class}, instance).andReturn(itemForm);                                
        PowerMock.expectNew(BeanItemContainer.class, Ad.class).andReturn(beanItemContainer);        
        itemList.setContainerDataSource(EasyMock.anyObject());
        itemList.setColumnOrder("title", "priceCents", "email", "phone");
        itemList.removeColumn("id");
        itemList.removeColumn("description");
        itemList.removeColumn("imageUrl");
        itemList.removeColumn("thumbnailUrl");        
        itemList.removeColumn("status");                
        EasyMock.expect(itemList.setSelectionMode(Grid.SelectionMode.SINGLE)).andReturn(null);
        
        PowerMock.expectNew(HorizontalLayout.class, filter, newAd).andReturn(actions);        
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);
        
        PowerMock.expectNew(VerticalLayout.class, actions, itemList).andReturn(left);        
        left.setSizeFull();
        itemList.setSizeFull();
        left.setExpandRatio(itemList, 1);

        PowerMock.expectNew(HorizontalLayout.class, left, itemForm).andReturn(mainLayout);        
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);
        panel.setContent(mainLayout);

        //activate component
        EasyMock.expect(ctx.getBean(AMItemService.class)).andReturn(itemService);
        newAd.addClickListener(EasyMock.anyObject());
        filter.setInputPrompt("Filter ads...");
        filter.addTextChangeListener(EasyMock.anyObject());
        itemList.addSelectionListener(EasyMock.anyObject());
        
        //refreshItems        
        EasyMock.expect(filter.getValue()).andReturn("");
        EasyMock.expect(itemService.findAll("")).andReturn(ads);
        PowerMock.expectNew(BeanItemContainer.class, Ad.class, ads).andReturn(beanItemContainer);                
        itemList.setContainerDataSource(beanItemContainer);
        itemForm.setVisible(false);
        
                
        PowerMock.replayAll();
        instance.initComponent();
        PowerMock.verifyAll();
    }
    
    @Test
    public void testGetItemList() {
    	AMMainComponent instance = new AMMainComponent();
        Whitebox.setInternalState(instance, "itemList", itemList);
                
        PowerMock.replayAll();
        Grid itemList = instance.getItemList();
        PowerMock.verifyAll();
        
        Assert.assertNotNull(itemList);
    }
    
    @Test
    public void testGetItemService() {
    	AMMainComponent instance = new AMMainComponent();
        Whitebox.setInternalState(instance, "itemService", itemService);
                
        PowerMock.replayAll();
        AMItemService itemService = instance.getItemService();
        PowerMock.verifyAll();
        
        Assert.assertNotNull(itemService);
    }
    
    
}
