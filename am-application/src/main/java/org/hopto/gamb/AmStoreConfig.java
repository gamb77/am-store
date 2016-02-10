package org.hopto.gamb;

import org.hopto.gamb.data.AMItemService;
import org.hopto.gamb.domain.service.StoreService;
import org.hopto.gamb.domain.service.impl.StoreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * AM Store configuration for spring context
 *
 * @author jani
 */
@Configuration
@ComponentScan({"org.hopto.gamb", "org.hopto.gamb.service"})
public class AmStoreConfig {

   @Bean 
   public RestTemplate restTemplate(){
      return new RestTemplate();
   }

   @Bean 
   public StoreService storeService(){
      return new StoreServiceImpl();
   }
   
   @Bean
   public AMItemService itemService() {
       return new AMItemService();
   }

}

