package com.kyalo.fbimostwanted.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FbiItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String uid;
    private String title;
    private String description;
    private String sex;
    private String race;
    private String nationality;
    private String status;
    private String rewardText;
    private String url;

    @JsonProperty("aliases")
    private List<String> aliases;

    @JsonProperty("field_offices")
    private List<String> fieldOffices;

    @JsonProperty("images")
    private List<FbiImage> images;

    public List<FbiImage> getImages() {
        return images;
    }

    public void setImages(List<FbiImage> images) {
        this.images = images;
    }

    public List<String> getFieldOffices() {
        return fieldOffices;
    }

    public void setFieldOffices(List<String> fieldOffices) {
        this.fieldOffices = fieldOffices;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRewardText() {
        return rewardText;
    }

    public void setRewardText(String rewardText) {
        this.rewardText = rewardText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

