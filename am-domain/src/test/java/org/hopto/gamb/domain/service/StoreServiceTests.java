package org.hopto.gamb.domain.service;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.hopto.gamb.domain.service.entities.Ad;
import org.hopto.gamb.domain.service.entities.Image;
import org.hopto.gamb.domain.service.impl.StoreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RunWith(PowerMockRunner.class)
public class StoreServiceTests {
    @Mock
    RestTemplate restTemplate;
    @Mock
    ResponseEntity<Ad[]> responseAdEntity;
    @Mock
    ResponseEntity<Image[]> responseImageEntity;
    
    
    @Test
    public void testFetchMarketAdById() {
        String TEST = "TEST";
        
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        Ad add = new Ad();
        String id = "12345";        
        add.setId(id);
        add.setTitle(TEST);
        add.setDescription(TEST);
        add.setEmail(TEST);
        add.setImageUrl(TEST);
        add.setPhone(TEST);
        add.setPriceCents(123L);
        add.setThumbnailUrl(TEST);
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.getForObject(ServiceUrls.GET_AD + id, Ad.class)).andReturn(add);
        PowerMock.replayAll();
        Ad response = instance.fetchMarketAdById(id);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatus().equals(Ad.Status.OK));
        Assert.assertTrue(response.getId().equals(id));  
        Assert.assertTrue(response.getTitle().equals(TEST));  
        Assert.assertTrue(response.getDescription().equals(TEST));  
        Assert.assertTrue(response.getEmail().equals(TEST));  
        Assert.assertTrue(response.getImageUrl().equals(TEST));  
        Assert.assertTrue(response.getPhone().equals(TEST));  
        Assert.assertTrue(response.getPriceCents().equals(123L));  
        Assert.assertTrue(response.getThumbnailUrl().equals(TEST));  
        
        
        Assert.assertTrue(response.getId().equals(id));  
        Assert.assertTrue(response.getId().equals(id));  
        Assert.assertTrue(response.getId().equals(id));  
    }

    @Test
    public void testFetchMarketAdById_FAIL() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
                
        Ad add = new Ad();
        String id = "12345";
        add.setId(id);
                
        //Setting up data to mock
        EasyMock.expect(restTemplate.getForObject(ServiceUrls.GET_AD + id, Ad.class))
             .andThrow(new RestClientException("TEST EXCEPTION")).anyTimes();
        
        PowerMock.replayAll();
        Ad response = instance.fetchMarketAdById(id);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatus().equals(Ad.Status.FAIL));
        Assert.assertNull(response.getId());        
    }
 
    @Test
    public void testFetchAllMarketAds() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);

        List<Ad> ads = new ArrayList<>();
        Ad ad = new Ad();
        String id = "12345";
        ad.setId(id);
        ads.add(ad);
        
        //Setting up data to mock
        EasyMock.expect(responseAdEntity.getBody()).andReturn(ads.toArray(new Ad[ads.size()]));
        EasyMock.expect(restTemplate.getForEntity(ServiceUrls.GET_ALL_ADS, Ad[].class)
        ).andReturn(responseAdEntity);
        
        PowerMock.replayAll();
        List<Ad> response = instance.fetchAllMarketAds();
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertTrue(response.get(0).getId().equals(id));
    }

    @Test
    public void testFetchAllMarketAds_FAIL() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);

        List<Ad> ads = new ArrayList<>();
        Ad ad = new Ad();
        String id = "12345";
        ad.setId(id);
        ads.add(ad);
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.getForEntity(ServiceUrls.GET_ALL_ADS, Ad[].class)
        ).andThrow(new RestClientException("TEST EXCEPTION"));
        
        String exception = null; 
        PowerMock.replayAll();
        try {
            instance.fetchAllMarketAds();            
        } catch (RestClientException ex) {
            exception = ex.getMessage();
        }
        PowerMock.verifyAll();
        
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.equals("TEST EXCEPTION"));
    }
    
    @Test
    public void testFetchImageById() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        Image image = new Image();
        String id = "12345";
        image.setId(id);
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.getForObject(ServiceUrls.GET_IMAGE + id, Image.class)).andReturn(image);
        PowerMock.replayAll();
        Image response = instance.fetchImageById(id);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatus().equals(Image.Status.OK));
        Assert.assertTrue(response.getId().equals(id));        
    }

    @Test
    public void testFetchImageById_FAIL() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
                
        Image image = new Image();
        String id = "12345";
        image.setId(id);
                
        //Setting up data to mock
        EasyMock.expect(restTemplate.getForObject(ServiceUrls.GET_IMAGE + id, Image.class))
             .andThrow(new RestClientException("TEST EXCEPTION")).anyTimes();
        
        PowerMock.replayAll();
        Image response = instance.fetchImageById(id);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatus().equals(Image.Status.FAIL));
        Assert.assertNull(response.getId());        
    }
 
    @Test
    public void testFetchAllImages() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);

        List<Image> images = new ArrayList<>();
        Image image = new Image();
        String id = "12345";
        image.setId(id);
        images.add(image);
        
        //Setting up data to mock
        EasyMock.expect(responseImageEntity.getBody()).andReturn(images.toArray(new Image[images.size()]));
        EasyMock.expect(restTemplate.getForEntity(ServiceUrls.GET_ALL_IMAGES, Image[].class)
        ).andReturn(responseImageEntity);
        
        PowerMock.replayAll();
        List<Image> response = instance.fetchAllImages();
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertTrue(response.get(0).getId().equals(id));
    }

    @Test
    public void testFetchAllImages_FAIL() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);

        List<Image> images = new ArrayList<>();
        Image image = new Image();
        String id = "12345";
        image.setId(id);
        images.add(image);
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.getForEntity(ServiceUrls.GET_ALL_IMAGES, Image[].class)
        ).andThrow(new RestClientException("TEST EXCEPTION"));
        
        String exception = null; 
        PowerMock.replayAll();
        try {
            instance.fetchAllImages();
        } catch (RestClientException ex) {
            exception = ex.getMessage();
        }
        PowerMock.verifyAll();
        
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.equals("TEST EXCEPTION"));
    }
    
    @Test
    public void testAddMarketAd() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        Ad ad = new Ad();
        ad.setTitle("TEST");
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.postForObject(ServiceUrls.POST_AD, ad, Ad.class)
        ).andReturn(ad);
        
        
        PowerMock.replayAll();
        Ad response = instance.addMarketAd(ad);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getTitle().equals("TEST"));        
        Assert.assertTrue(response.getStatus().equals(Ad.Status.OK));        
    }

    @Test
    public void testAddMarketAd_FAIL() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        Ad ad = new Ad();
        ad.setTitle("TEST");
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.postForObject(ServiceUrls.POST_AD, ad, Ad.class)
        ).andThrow(new RestClientException("TEST EXCEPTION"));
        
        
        PowerMock.replayAll();
        Ad response = instance.addMarketAd(ad);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatus().equals(Ad.Status.FAIL));        
    }

    @Test
    public void testRemoveMarketAd() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        String id = "12345";
        
        //Setting up data to mock
        restTemplate.delete(ServiceUrls.DELETE_AD + id);        
        
        PowerMock.replayAll();
        Ad.Status response = instance.removeMarketAd(id);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.equals(Ad.Status.OK));        
    }

    @Test
    public void testRemoveMarketAd_FAIL() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        String id = "12345";
        
        //Setting up data to mock
        restTemplate.delete(ServiceUrls.DELETE_AD + id);        
        EasyMock.expectLastCall().andThrow(new RestClientException("TEST EXCEPTION"));
        
        PowerMock.replayAll();
        Ad.Status response = instance.removeMarketAd(id);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.equals(Ad.Status.FAIL));        
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testAddImage() {
    	StoreService instance = new StoreServiceImpl();
        Whitebox.setInternalState(instance, "restTemplate", restTemplate);
        
        Image image = new Image();
        //TODO: 
        
        //Setting up data to mock
        EasyMock.expect(restTemplate.postForObject(ServiceUrls.POST_IMAGE, image, Image.class)
        ).andReturn(image);
        
        
        PowerMock.replayAll();
        Image response = instance.addImage(image);
        PowerMock.verifyAll();
        
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatus().equals(Image.Status.OK));        
        
    }
    
    @Test
    public void testServiceUrls() {
        ServiceUrls urls = new ServiceUrls();
        Assert.assertNotNull(urls);
    }
}
