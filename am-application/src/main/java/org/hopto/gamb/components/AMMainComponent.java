package org.hopto.gamb.components;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.hopto.gamb.SpringUtils;
import org.hopto.gamb.data.AMItemService;
import org.hopto.gamb.domain.service.entities.Ad;

/**
 * Bottom level component of the application
 *
 * @author jani
 */
public class AMMainComponent extends AMAbstractComponent {
    private AMItemService itemService = null; 
    private AMItemForm itemForm = null; 
    private Grid itemList = null;
    private TextField filter = null;
    private Button newAd = null; 
    
    @Override
    public void initComponent() {                                
        newAd = new Button("New ad");
        filter = new TextField();
        itemList = new Grid();
        itemForm = new AMItemForm(this);
                
        itemList.setContainerDataSource(new BeanItemContainer<>(Ad.class));
        itemList.setColumnOrder("title", "priceCents", "email", "phone");
        itemList.removeColumn("id");
        itemList.removeColumn("description");
        itemList.removeColumn("imageUrl");
        itemList.removeColumn("thumbnailUrl");        
        itemList.removeColumn("status");
        itemList.setSelectionMode(Grid.SelectionMode.SINGLE);
                
        //set layout 
        HorizontalLayout actions = new HorizontalLayout(filter, newAd);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, itemList);
        left.setSizeFull();
        itemList.setSizeFull();
        left.setExpandRatio(itemList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, itemForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);
        setContent(mainLayout);
        
        activateComponent();
    }

    @Override
    protected void activateComponent() {
        //Get spring service object from the context
        itemService = (AMItemService)SpringUtils.ctx.getBean(AMItemService.class);        
        
        newAd.addClickListener(e -> itemForm.add(new Ad()));
        filter.setInputPrompt("Filter ads...");
        filter.addTextChangeListener(e -> refreshItems(e.getText()));

        itemList.addSelectionListener(e
                -> itemForm.view((Ad) itemList.getSelectedRow()));
        refreshItems();
    }
 
    public void refreshItems() {
        refreshItems(filter.getValue());
    }

    public Grid getItemList() {
        return this.itemList;
    }
    
    public AMItemService getItemService() {
        return this.itemService;
    }
    
    private void refreshItems(String stringFilter) {
        itemList.setContainerDataSource(new BeanItemContainer<>(
                Ad.class, itemService.findAll(stringFilter) ));
        itemForm.setVisible(false); 
    }
    
}
