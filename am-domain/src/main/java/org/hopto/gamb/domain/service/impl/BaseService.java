package org.hopto.gamb.domain.service.impl;

import java.util.ArrayList;
import org.hopto.gamb.domain.service.ServiceUrls;
import org.hopto.gamb.domain.service.entities.Ad;
import org.hopto.gamb.domain.service.entities.BaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Base service for common service methods, Ad & Picture
 *
 * @author jani
 * @param <I>
 */
public abstract class BaseService<I extends BaseItem> {
    
    @Autowired
    protected RestTemplate restTemplate;
        
    protected I getItem(String url, Class<I> cls) {
        I response = newItemInstance(cls);
        
        if(response != null) {
            try {
                response = restTemplate.getForObject(url, cls);
                response.setStatus(I.Status.OK);
            } catch(RestClientException exception) {
                response.setStatus(I.Status.FAIL);
            }            
        }
        return response; 
    }
    
    protected I addItem(String url, I item, Class<I> cls) {
        I response = newItemInstance(cls);
        if(item != null) {
            try {
                response = restTemplate.postForObject(url, item, cls);
                response.setStatus(I.Status.OK);
            } catch(RestClientException exception) {
                response.setStatus(I.Status.FAIL);
            }                    
        }
        return response;
    }
    
    protected I.Status removeItem(String url, String id, Class<I> cls) {
        I.Status response = I.Status.OK;
            try {
                restTemplate.delete(url + id);
            } catch(RestClientException exception) {
                response = I.Status.FAIL;
            }                    
           
        return response;
    }

    private I newItemInstance(Class<I> cls) {
        I item = null;
        try {
            item = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) { 
        }
        return item; 
    }
    
    
}
