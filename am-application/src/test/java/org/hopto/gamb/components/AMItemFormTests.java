package org.hopto.gamb.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import org.easymock.EasyMock;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest({Notification.class, BeanFieldGroup.class})
public class AMItemFormTests {
    
    @Mock
    AMMainComponent main;
    @Mock
    ClickEvent click;
    @Mock
    Grid itemList;
    @Mock
    Ad ad;
    @Mock
    TextField title;
    @Mock
    AMItemService itemService;
    @Mock
    Notification notification;
    @Mock
    BeanFieldGroup<Ad> formFieldBindings;
    @Mock
    Embedded image;
           
    
    @Test
    public void testSave() throws FieldGroup.CommitException {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "formFieldBindings", formFieldBindings);
        Whitebox.setInternalState(instance, "ad", ad);

        formFieldBindings.commit();
        EasyMock.expect(main.getItemService()).andReturn(itemService);        
        EasyMock.expect(itemService.addAd(ad)).andReturn(ad);
        EasyMock.expect(ad.getStatus()).andReturn(Ad.Status.OK);
        EasyMock.expect(ad.getTitle()).andReturn("TEST");
        PowerMock.mockStatic(Notification.class);
        Notification.show("Saved TEST.", Notification.Type.TRAY_NOTIFICATION);
        
        main.refreshItems();
        
        PowerMock.replayAll();
        instance.save(click);
        PowerMock.verifyAll();
                
    }

    @Test
    public void testSave_FAIL() throws FieldGroup.CommitException {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "formFieldBindings", formFieldBindings);
        Whitebox.setInternalState(instance, "ad", ad);

        formFieldBindings.commit();
        EasyMock.expect(main.getItemService()).andReturn(itemService);        
        EasyMock.expect(itemService.addAd(ad)).andReturn(ad);
        EasyMock.expect(ad.getStatus()).andReturn(Ad.Status.FAIL);
        EasyMock.expect(ad.getTitle()).andReturn("TEST");
        PowerMock.mockStatic(Notification.class);
        Notification.show("Send failed for TEST.", Notification.Type.ERROR_MESSAGE);
                
        PowerMock.replayAll();
        instance.save(click);
        PowerMock.verifyAll();
    }
    
    @Test
    public void testSave_exception() throws FieldGroup.CommitException {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "formFieldBindings", formFieldBindings);

        formFieldBindings.commit();
        EasyMock.expectLastCall().andThrow(new CommitException(""));
        
        PowerMock.mockStatic(Notification.class);
        Notification.show("Fail. Required fields or field patterns not entered.", Notification.Type.ERROR_MESSAGE);
        
        PowerMock.replayAll();
        instance.save(click);
        PowerMock.verifyAll();
        
    }

    
    @Test
    public void testRemove() throws Exception {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "ad", ad);
        
        String adId = "12345";
        
        EasyMock.expect(main.getItemService()).andReturn(itemService);        
        EasyMock.expect(itemService.removeAd(adId)).andReturn(Ad.Status.OK);
        EasyMock.expect(ad.getId()).andReturn(adId);
        EasyMock.expect(ad.getTitle()).andReturn("TEST");
        PowerMock.mockStatic(Notification.class);
        Notification.show("TEST removed.", Notification.Type.TRAY_NOTIFICATION);
        
        main.refreshItems();
                
        PowerMock.replayAll();
        instance.remove(click);
        PowerMock.verifyAll();
        
    }

    @Test
    public void testRemove_FAIL() {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "ad", ad);
        
        String adId = "12345";
        
        EasyMock.expect(main.getItemService()).andReturn(itemService);        
        EasyMock.expect(itemService.removeAd(adId)).andReturn(Ad.Status.FAIL);
        EasyMock.expect(ad.getId()).andReturn(adId);
        EasyMock.expect(ad.getTitle()).andReturn("TEST");
        PowerMock.mockStatic(Notification.class);
        Notification.show("Remove failed for TEST.", Notification.Type.ERROR_MESSAGE);
        main.refreshItems();
                
        PowerMock.replayAll();
        instance.remove(click);
        PowerMock.verifyAll();
        
    }
    
    
    @Test
    public void testCancel() {
        AMItemForm instance = new AMItemForm(main);
        
        EasyMock.expect(main.getItemList()).andReturn(itemList);
        EasyMock.expect(itemList.select(null)).andReturn(true);
        
        PowerMock.replayAll();
        instance.cancel(click);
        PowerMock.verifyAll();
        
        Assert.assertFalse(instance.isVisible());
    }
    
    @Test 
    public void testAdd() {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "title", title);
        Whitebox.setInternalState(instance, "image", image);
        
        //activate
        PowerMock.mockStatic(BeanFieldGroup.class);                
        EasyMock.expect(BeanFieldGroup.bindFieldsBuffered(ad,instance)).andReturn(formFieldBindings);
        title.focus();        
        EasyMock.expect(ad.getImageUrl()).andReturn(null);
        EasyMock.expect(image.getParent()).andReturn(null);
        title.setEnabled(true);
        
        PowerMock.replayAll();
        instance.add(ad);
        PowerMock.verifyAll();
    }

    @Test 
    public void testAddHasImage() throws Exception {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "title", title);
        Whitebox.setInternalState(instance, "image", image);
        
        //activate
        PowerMock.mockStatic(BeanFieldGroup.class);                
        EasyMock.expect(BeanFieldGroup.bindFieldsBuffered(ad,instance)).andReturn(formFieldBindings);
        title.focus();        
        EasyMock.expect(ad.getImageUrl()).andReturn("").times(2);
        image.markAsDirty();
        image.setSource(EasyMock.anyObject());
        EasyMock.expect(image.getParent()).andReturn(null);
        image.setParent(instance);
        image.markAsDirtyRecursive();
        title.setEnabled(true);
        
        PowerMock.replayAll();
        instance.add(ad);
        PowerMock.verifyAll();
    }
    
    
    @Test 
    public void testAdd_null() {
        AMItemForm instance = new AMItemForm(main);
        Whitebox.setInternalState(instance, "title", title);
        Whitebox.setInternalState(instance, "image", image);
        
        title.setEnabled(true);
        
        PowerMock.replayAll();
        instance.add(null);
        PowerMock.verifyAll();
    }

    
    @Test 
    public void testView() {
        
    }
    
}
