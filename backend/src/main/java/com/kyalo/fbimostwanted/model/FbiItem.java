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

    @JsonProperty("age_min")
    private Integer ageMin;

    @JsonProperty("age_max")
    private Integer ageMax;

    @JsonProperty("hair")
    private String hairColor;

    @JsonProperty("eyes")
    private String eyeColor;


    @JsonProperty("field_offices")
    private List<String> fieldOffices;

    @JsonProperty("images")
    private List<FbiImage> images;

    @JsonProperty("files")
    private List<FbiFile> files;

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

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
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

    public List<FbiFile> getFiles() {
        return files;
    }

    public void setFiles(List<FbiFile> files) {
        this.files = files;
    }
}

