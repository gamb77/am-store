package org.hopto.gamb.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.hopto.gamb.domain.service.entities.Ad;

/**
 * Ad item editing and viewing form
 *
 * @author jani
 */
public class AMItemForm extends FormLayout {
    private final Button save = new Button("Save", this::save);
    private final Button remove = new Button("Remove", this::remove);
    private final Button cancel = new Button("Cancel", this::cancel);
    private final TextField title = new TextField("Title");
    private final TextArea description = new TextArea("Description");
    private final TextField priceCents = new TextField("Price");
    private final TextField email = new TextField("Email");
    private final TextField phone = new TextField("Phone");
    private Embedded image = new Embedded();
    
    BeanFieldGroup<Ad> formFieldBindings;
    private AMMainComponent main;
    private Ad ad;
    
    public AMItemForm(AMMainComponent main) {
        this.main = main;
        initComponents();
        buildLayout();
    }
    
    private void initComponents() {
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);        

        title.setNullRepresentation("");
        description.setNullRepresentation("");
        priceCents.setNullRepresentation("");
        email.setNullRepresentation("");
        phone.setNullRepresentation("");
        
    }
    
    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        remove.setVisible(false);
        
        HorizontalLayout actions = new HorizontalLayout(save, remove, cancel);
        actions.setSpacing(true);

        image.setHeight("100px");
        image.setWidth("100px");
        
        addComponents(actions, title, description, priceCents, email, phone);
    }    
    
    public void save(Button.ClickEvent event) {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            // Send data to backend
            Ad response = main.getItemService().addAd(ad);
            if(response.getStatus().equals(Ad.Status.OK)) {
                String msg = "Saved " + response.getTitle() + ".";
                Notification.show(msg,Notification.Type.TRAY_NOTIFICATION);
                main.refreshItems();                
            } else {
                String msg = "Send failed for " + ad.getTitle() + ".";
                Notification.show(msg,Notification.Type.ERROR_MESSAGE);
            }

        } catch (FieldGroup.CommitException e) {
            String msg = "Fail. Required fields or field patterns not entered.";
            Notification.show(msg,Notification.Type.ERROR_MESSAGE);
        }

    }

    public void remove(Button.ClickEvent event) {
        Ad.Status status = main.getItemService().removeAd(ad.getId());        
        if(status.equals(Ad.Status.OK)) {
            String msg = ad.getTitle() + " removed.";
            Notification.show(msg,Notification.Type.TRAY_NOTIFICATION);            
        } else {
            String msg = "Remove failed for " + ad.getTitle() + ".";
            Notification.show(msg,Notification.Type.ERROR_MESSAGE);            
        }
        
        main.refreshItems();        
    }
    
    public void cancel(Button.ClickEvent event) {
        main.getItemList().select(null);
        setVisible(false);
    }

    public void add(Ad ad) {
        activate(ad);
        setComponentsState(true);        
    }
    
    public void view(Ad ad) {
        activate(ad);
        setComponentsState(false);        
    }
    
    private void activate(Ad ad) {
        this.ad = ad;
        if(ad != null) {
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(ad, this);
            title.focus();
            if(ad.getImageUrl() != null) {
                ExternalResource img = new ExternalResource(ad.getImageUrl());
                image.markAsDirty();
                image.setSource(img);
                addComponent(image);                
            } else {
                removeComponent(image);                
            }            
        }
        setVisible(ad != null);
    }
    
    private void setComponentsState(boolean state) {
        save.setEnabled(state);
        remove.setVisible(!state);
        title.setEnabled(state);
        description.setEnabled(state);
        priceCents.setEnabled(state);
        email.setEnabled(state);
        phone.setEnabled(state);        
    }
    
}
