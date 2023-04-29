package com.java.sdpprojectay2.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="emergency_details_files")
public class EmergencyDetailsFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idEmergencyDetails;
    private long idMedia;
    private String url;

    public EmergencyDetailsFiles() {
    }

    public long getId() {
        return id;
    }

    public EmergencyDetailsFiles setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdEmergencyDetails() {
        return idEmergencyDetails;
    }

    public EmergencyDetailsFiles setIdEmergencyDetails(long idEmergencyDetails) {
        this.idEmergencyDetails = idEmergencyDetails;
        return this;
    }

    public long getIdMedia() {
        return idMedia;
    }

    public EmergencyDetailsFiles setIdMedia(long idMedia) {
        this.idMedia = idMedia;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public EmergencyDetailsFiles setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "EmergencyDetailsFiles{" +
                "id=" + id +
                ", idEmergencyDetails=" + idEmergencyDetails +
                ", idMedia=" + idMedia +
                ", url='" + url + '\'' +
                '}';
    }
}
