package org.hopto.gamb.domain.service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Ad bean for transferring data
 *
 * @author jani
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ad extends BaseItem implements Serializable, Cloneable {
    
    @NotNull
    private String title; 
    @NotNull
    private String description;
    @Min(5)
    @NotNull
    private Long priceCents;
    private String imageUrl;
    private String thumbnailUrl;
    @NotNull
    @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@([a-z0-9-]+(\\.[a-z0-9-]+)*?\\.[a-z]{2,6}|(\\d{1,3}\\.){3}\\d{1,3})(:\\d{4})?$", 
            message = "Enter email address")
    private String email;
    @NotNull
    private String phone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(Long priceCents) {
        this.priceCents = priceCents;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Ad clone() throws CloneNotSupportedException {
        try {
            return (Ad) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }  

    @Override
    public String toString() {
        //Used to sorting results
        return title + " " + description + " " + priceCents + " " + imageUrl + " " + thumbnailUrl + " " + email + " " + phone;
    }
}
