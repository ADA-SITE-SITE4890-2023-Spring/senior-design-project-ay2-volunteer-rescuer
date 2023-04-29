package com.java.sdpprojectay2.model;

import javax.persistence.*;

@Entity
@Table(name = "rules_group")
public class RulesGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idGroup;
    private long idRule;

    public RulesGroup() {

    }

    public long getId() {
        return id;
    }

    public RulesGroup setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public RulesGroup setIdGroup(long idGroup) {
        this.idGroup = idGroup;
        return this;
    }

    public long getIdRule() {
        return idRule;
    }

    public RulesGroup setIdRule(long idRule) {
        this.idRule = idRule;
        return this;
    }

    @Override
    public String toString() {
        return "RulesGroup{" +
                "id=" + id +
                ", idGroup=" + idGroup +
                ", idRule=" + idRule +
                '}';
    }
}
