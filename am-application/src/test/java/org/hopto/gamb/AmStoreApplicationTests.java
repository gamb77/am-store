package org.hopto.gamb;


import org.hopto.gamb.data.AMItemService;
import org.hopto.gamb.domain.service.StoreService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmStoreApplication.class)
@WebAppConfiguration
public class AmStoreApplicationTests {
    
    @Autowired 
    RestTemplate restTemplate;
    
    @Autowired 
    StoreService storeService;

    @Autowired 
    AMItemService itemService;
            
    @Test
    public void testSpringContext() {
        Assert.assertNotNull(restTemplate);
        Assert.assertNotNull(storeService);
        Assert.assertNotNull(itemService);
        Assert.assertNotNull((RestTemplate)SpringUtils.ctx.getBean(RestTemplate.class));  
        Assert.assertNotNull((StoreService)SpringUtils.ctx.getBean(StoreService.class));  
        Assert.assertNotNull((AMItemService)SpringUtils.ctx.getBean(AMItemService.class));  
    }
    
    @Ignore
    @Test
    public void testMain() {
        String[] args = {""};
        AmStoreApplication.main(args);
    }

}
