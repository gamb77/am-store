package org.hopto.gamb.data;

import java.util.ArrayList;
import java.util.List;
import org.hopto.gamb.domain.service.StoreService;
import org.hopto.gamb.domain.service.entities.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Item service between domain and UI components. 
 *
 * @author jani
 */
@Service("itemService")
public class AMItemService {
    
    @Autowired 
    private StoreService storeService;
    
    public List<Ad> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (Ad ad : storeService.fetchAllMarketAds()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || ad.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(ad.clone());
                }
            } catch (CloneNotSupportedException ex) {
            }
        }
        return arrayList;
    }
    
    public Ad addAd(Ad ad) {
        return storeService.addMarketAd(ad);
    }
        
    public Ad.Status removeAd(String adId) {
        return storeService.removeMarketAd(adId);
    }
}
