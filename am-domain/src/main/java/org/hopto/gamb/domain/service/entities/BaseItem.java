package org.hopto.gamb.domain.service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 *
 * @author jani
 */
public class BaseItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Status {
        OK,
        FAIL
    }
    private String id;
    @JsonIgnore
    private Status status = Status.OK; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
