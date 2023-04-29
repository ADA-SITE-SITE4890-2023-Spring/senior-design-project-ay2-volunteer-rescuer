package com.java.sdpprojectay2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "media")
@Where(clause = "deleted = false")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    private String remoteName;

    private String localName;

    private String fileType;

    private String localPath;

    private String remotePath;

    private long fileSize;

    private LocalDateTime insertDate;

    @JsonIgnore
    private boolean deleted;

    public Media() {
        setDeleted(false);
    }

    public long getId() {
        return id;
    }

    public Media setId(long id) {
        this.id = id;
        return this;
    }

    public String getRemoteName() {
        return remoteName;
    }

    public Media setRemoteName(String remoteName) {
        this.remoteName = remoteName;
        return this;
    }

    public String getLocalName() {
        return localName;
    }

    public Media setLocalName(String localName) {
        this.localName = localName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public Media setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getLocalPath() {
        return localPath;
    }

    public Media setLocalPath(String localPath) {
        this.localPath = localPath;
        return this;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public Media setRemotePath(String remotePath) {
        this.remotePath = remotePath;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Media setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public Media setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Media setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", remoteName='" + remoteName + '\'' +
                ", localName='" + localName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", localPath='" + localPath + '\'' +
                ", remotePath='" + remotePath + '\'' +
                ", fileSize=" + fileSize +
                ", insertDate=" + insertDate +
                ", deleted=" + deleted +
                '}';
    }
}
