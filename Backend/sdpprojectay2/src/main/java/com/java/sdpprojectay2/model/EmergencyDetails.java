package com.java.sdpprojectay2.model;

import com.java.sdpprojectay2.dto.ModelFileUrls;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="emergency_details")
@Where(clause = "deleted=false")
public class EmergencyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;
    private Double latitude;
    private Double longitude;
    @JsonIgnore
    private String location;
    @Transient
    private List<ModelFileUrls> mediaFiles;
    private String description;
    private String voiceRecord;
    private int status;
    @JsonIgnore
    @Column(columnDefinition = "SMALLINT")
    private boolean deleted;
    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isAccepted;

    private boolean isResolved;

    private int priority;
    private String note;

    public String getNote() {
        return note;
    }

    public EmergencyDetails setNote(String note) {
        this.note = note;
        return this;
    }

    public EmergencyDetails() {
    }

    public long getId() {
        return id;
    }

    public EmergencyDetails setId(long id) {
        this.id = id;
        return this;
    }

    public Users getUser() {
        return user;
    }

    public EmergencyDetails setUser(Users user) {
        this.user = user;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public EmergencyDetails setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public EmergencyDetails setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public EmergencyDetails setLocation(String location) {
        this.location = location;
        return this;
    }

    public List<ModelFileUrls> getMediaFiles() {
        return mediaFiles;
    }

    public EmergencyDetails setMediaFiles(List<ModelFileUrls> mediaFiles) {
        this.mediaFiles = mediaFiles;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EmergencyDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVoiceRecord() {
        return voiceRecord;
    }

    public EmergencyDetails setVoiceRecord(String voiceRecord) {
        this.voiceRecord = voiceRecord;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public EmergencyDetails setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public EmergencyDetails setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public EmergencyDetails setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public EmergencyDetails setAccepted(boolean accepted) {
        isAccepted = accepted;
        return this;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public EmergencyDetails setResolved(boolean resolved) {
        isResolved = resolved;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public EmergencyDetails setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String toString() {
        return "EmergencyDetails{" +
                "id=" + id +
                ", user=" + user +
                ", location='" + location + '\'' +
                ", mediaFiles=" + mediaFiles +
                ", description='" + description + '\'' +
                ", voiceRecord='" + voiceRecord + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", isAccepted=" + isAccepted +
                ", isResolved=" + isResolved +
                ", priority=" + priority +
                ", note='" + note + '\'' +
                '}';
    }
}
