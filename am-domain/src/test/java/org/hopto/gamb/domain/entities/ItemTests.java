package org.hopto.gamb.domain.entities;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.easymock.EasyMock;
import org.hopto.gamb.domain.service.entities.Ad;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(BeanUtils.class)
public class ItemTests {
    
    @Test
    public void testAdItem() throws CloneNotSupportedException {
        String TEST = "TEST";
        
        Ad ad = new Ad();
        String id = "12345";        
        ad.setId(id);
        ad.setTitle(TEST);
        ad.setDescription(TEST);
        ad.setEmail(TEST);
        ad.setImageUrl(TEST);
        ad.setPhone(TEST);
        ad.setPriceCents(123L);
        ad.setThumbnailUrl(TEST);
        
        String toString = ad.toString();
        Ad response = ad.clone();
        Assert.assertNotNull(response);
        
        Assert.assertTrue(toString.equals("TEST TEST 123 TEST TEST TEST TEST"));
        
        Assert.assertTrue(response.getStatus().equals(Ad.Status.OK));
        Assert.assertTrue(response.getId().equals(id));  
        Assert.assertTrue(response.getTitle().equals(TEST));  
        Assert.assertTrue(response.getDescription().equals(TEST));  
        Assert.assertTrue(response.getEmail().equals(TEST));  
        Assert.assertTrue(response.getImageUrl().equals(TEST));  
        Assert.assertTrue(response.getPhone().equals(TEST));  
        Assert.assertTrue(response.getPriceCents().equals(123L));  
        Assert.assertTrue(response.getThumbnailUrl().equals(TEST));  
        
    }

    @Test(expected=CloneNotSupportedException.class)
    public void testAdItem_clone_exception() throws CloneNotSupportedException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException  {
        PowerMock.mockStatic(BeanUtils.class);
        Ad ad = new Ad();
        String id = "12345";        
        EasyMock.expect(BeanUtils.cloneBean(ad)).andThrow(new IllegalAccessException(""));
        PowerMock.replayAll();
        Ad response = ad.clone();
        PowerMock.verifyAll();
        
    }

    
}
