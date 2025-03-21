package com.kyalo.fbimostwanted.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FbiImage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String large;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
