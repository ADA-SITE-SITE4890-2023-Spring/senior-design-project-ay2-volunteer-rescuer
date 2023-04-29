package com.java.sdpprojectay2.model;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String keyword;

    public Rules() {

    }

    public long getId() {
        return id;
    }

    public Rules setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Rules setName(String name) {
        this.name = name;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public Rules setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
