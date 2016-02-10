package org.hopto.gamb.domain.service.impl;


import java.util.Arrays;
import java.util.List;
import org.hopto.gamb.domain.service.ServiceUrls;
import org.hopto.gamb.domain.service.StoreService;
import org.hopto.gamb.domain.service.entities.Ad;
import org.hopto.gamb.domain.service.entities.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service("storeService")
public class StoreServiceImpl extends BaseService implements StoreService  {
        
    @Override
    public Ad fetchMarketAdById(String id) {
        String url = ServiceUrls.GET_AD + id;
        return (Ad) getItem(url, Ad.class);
    }

    @Override
    public List<Ad> fetchAllMarketAds() throws RestClientException {
        return Arrays.asList(restTemplate.getForEntity(ServiceUrls.GET_ALL_ADS, Ad[].class).getBody());
    }

    @Override
    public Image fetchImageById(String id) {
        String url = ServiceUrls.GET_IMAGE + id;
        return (Image) getItem(url, Image.class);        
    }

    @Override
    public List<Image> fetchAllImages() throws RestClientException {
        return Arrays.asList(restTemplate.getForEntity(ServiceUrls.GET_ALL_IMAGES, Image[].class).getBody());
    }

    @Override
    public Ad addMarketAd(Ad ad) throws RestClientException {
        return (Ad) addItem(ServiceUrls.POST_AD, ad, Ad.class);
    }

    @Override
    public Ad.Status removeMarketAd(String adId) { 
        return removeItem(ServiceUrls.DELETE_AD, adId, Ad.class);
    }

    @Override
    public Image addImage(Image picture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
