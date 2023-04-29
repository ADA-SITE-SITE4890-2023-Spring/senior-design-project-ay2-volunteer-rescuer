package com.java.sdpprojectay2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "media")
public class MediaStorageProperties {

    private String uploadDir;
    private String soundsDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getSoundsDir() {
        return soundsDir;
    }

    public MediaStorageProperties setSoundsDir(String soundsDir) {
        this.soundsDir = soundsDir;
        return this;
    }
}
