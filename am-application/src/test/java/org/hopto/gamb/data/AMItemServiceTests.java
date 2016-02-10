package org.hopto.gamb.data;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.hopto.gamb.domain.service.StoreService;
import org.hopto.gamb.domain.service.entities.Ad;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
public class AMItemServiceTests {
    
    @Mock
    StoreService storeService;
    @Mock 
    Ad ad; 
    
    
    @Test
    public void testFindAll() {
    	AMItemService instance = new AMItemService();
        Whitebox.setInternalState(instance, "storeService", storeService);
        
        //Dummy data
        List<Ad> ads = new ArrayList<>();
        Ad ad1 = new Ad();
        ad1.setTitle("T1");
        Ad ad2 = new Ad();
        ad2.setTitle("T2");
        ads.add(ad1);
        ads.add(ad2);
        
        //Setting up, no filtering
        EasyMock.expect(storeService.fetchAllMarketAds()).andReturn(ads);
        PowerMock.replayAll();
        List<Ad> response = instance.findAll("");
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.size() == 2);
    }
    
    @Test
    public void testFindAll_FILTERED() {
    	AMItemService instance = new AMItemService();
        Whitebox.setInternalState(instance, "storeService", storeService);
        
        //Dummy data
        List<Ad> ads = new ArrayList<>();
        Ad ad1 = new Ad();
        ad1.setTitle("T1");
        Ad ad2 = new Ad();
        ad2.setTitle("T2");
        ads.add(ad1);
        ads.add(ad2);
        
        //Setting up, no filtering
        EasyMock.expect(storeService.fetchAllMarketAds()).andReturn(ads);
        PowerMock.replayAll();
        List<Ad> response = instance.findAll("T1");
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.size() == 1);
    }
    
    
    @Test
    public void testAddAd() {
    	AMItemService instance = new AMItemService();
        Whitebox.setInternalState(instance, "storeService", storeService);

        //Setting up
        EasyMock.expect(storeService.addMarketAd(ad)).andReturn(ad);
        PowerMock.replayAll();
        Ad response = instance.addAd(ad);
        PowerMock.verifyAll();        
        
    }
    
    @Test
    public void testRemoveAd() {
    	AMItemService instance = new AMItemService();
        Whitebox.setInternalState(instance, "storeService", storeService);

        Ad.Status status = Ad.Status.OK;
        String adId = "1234";
        
        //Setting up
        EasyMock.expect(storeService.removeMarketAd(adId)).andReturn(status);
        PowerMock.replayAll();
        Ad.Status response = instance.removeAd(adId);
        PowerMock.verifyAll();     
        
        Assert.assertTrue(response.equals(status));
        
    }            
    
}
