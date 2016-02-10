package org.hopto.gamb.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.hopto.gamb.AmStoreApplication;
import org.hopto.gamb.domain.service.StoreService;
import org.hopto.gamb.domain.service.entities.Ad;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * For testing integration to backend, by default state of IGNORE
 * 
 * @author japulkki
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmStoreApplication.class)
@WebAppConfiguration
public class StoreServiceIntegrationTests {
    
    @Autowired 
    StoreService storeService;
        
    @Test
    public void testFetchAllMarketAdds() {
        List<Ad> ads = storeService.fetchAllMarketAds();
        for(Ad ad : ads) {
            System.out.println(ad.toString());
        }
    }
    
    
            
    @Test 
    public void testAddRemoveAndVerifyAd() throws JsonProcessingException {
        Ad ad = new Ad();
        ad.setTitle("T1");
        ad.setDescription("T1");
        ad.setPriceCents(123L); //numeric
        ad.setPhone("T1");
        ad.setEmail("T1@testi.fi"); //well formed
        
        Ad response = storeService.addMarketAd(ad);
        
        Assert.assertNotNull(response.getId());
        Assert.assertTrue(ad.getStatus().equals(Ad.Status.OK));        
        Ad.Status status = storeService.removeMarketAd(response.getId());
        Assert.assertTrue(status.equals(Ad.Status.OK));
        
        response = storeService.fetchMarketAdById(response.getId());
        Assert.assertTrue(response.getStatus().equals(Ad.Status.FAIL)); 
                
    }
    
    @Test 
    public void testRemoveMarketAd() {
        String id = "11";
        Ad.Status status = storeService.removeMarketAd(id);
        Assert.assertTrue(status.equals(Ad.Status.OK));
    }
}
