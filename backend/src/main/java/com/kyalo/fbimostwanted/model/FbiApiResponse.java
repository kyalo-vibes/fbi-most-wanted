package com.kyalo.fbimostwanted.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FbiApiResponse {
    private int total;
    private int page;

    @JsonProperty("items")
    private List<FbiItem> items;

    public List<FbiItem> getItems() {
        return items;
    }

    public void setItems(List<FbiItem> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

