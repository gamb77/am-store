package org.hopto.gamb.domain.service;

import java.util.List;
import org.hopto.gamb.domain.service.entities.Ad;
import org.hopto.gamb.domain.service.entities.Image;
import org.springframework.web.client.RestClientException;

/**
 * Store service provider
 *
 * @author jani
 */
public interface StoreService {
    
    /**
     * Fetch ad identified by id from the service
     * 
     * @param id
     * @return add object
     */
    Ad fetchMarketAdById(String id);
    
    /**
     * Fetch all ads of the service
     * 
     * @return list of add objects
     * @throws RestClientException 
     */
    List<Ad> fetchAllMarketAds() throws RestClientException;
    
    /**
     * Add the ad object to the service.
     * 
     * @param ad
     * @return ad from the service
     */
    Ad addMarketAd(Ad ad);
    
    /**
     * Removes the ad object from the service identified by id
     * 
     * @param adId
     * @return status of removing (OK,FAIL)
     */
    Ad.Status removeMarketAd(String adId);
    
    /**
     * Fetch image identified by id from the service
     * 
     * @param id
     * @return image object
     */
    Image fetchImageById(String id);
    
    /**
     * Fetch all images of the service
     * 
     * @return list of images
     * @throws RestClientException 
     */
    List<Image> fetchAllImages() throws RestClientException;
    
    /**
     * Add the image object to the service.
     * 
     * @param picture
     * @return image from the service
     */
    Image addImage(Image picture);
    
}
