package com.kyalo.fbimostwanted.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
public class FileInfo implements Serializable {
    private String name;
    private String url;

    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
