package org.hopto.gamb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Used for providing spring objects to vaadin components since they are created 
 * after the spring initialization
 *
 * @author jani
 */
@Component
public class SpringUtils {
    public static ApplicationContext ctx;

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;       
    }
}
