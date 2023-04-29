package com.java.sdpprojectay2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "`groups`")
@Where(clause = "deleted = false")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonIgnore
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted;

    public Groups() {

    }

    public long getId() {
        return id;
    }

    public Groups setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Groups setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Groups setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
