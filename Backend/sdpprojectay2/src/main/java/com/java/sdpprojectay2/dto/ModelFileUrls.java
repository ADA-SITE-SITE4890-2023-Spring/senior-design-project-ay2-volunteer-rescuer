package com.java.sdpprojectay2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ModelFileUrls {
    @JsonIgnore
    private long idMedia;
    private String url;

    public ModelFileUrls() {
    }


    public long getIdMedia() {
        return idMedia;
    }

    public ModelFileUrls setIdMedia(long idMedia) {
        this.idMedia = idMedia;
        return this;
    }


    public String getUrl() {
        return url;
    }

    public ModelFileUrls setUrl(String url) {
        this.url = url;
        return this;
    }
}
