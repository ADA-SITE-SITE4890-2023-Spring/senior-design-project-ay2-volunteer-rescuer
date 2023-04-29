package com.java.sdpprojectay2.service;


import com.java.sdpprojectay2.dto.Response;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.Groups;
import com.java.sdpprojectay2.model.Rules;
import com.java.sdpprojectay2.model.RulesGroup;
import com.java.sdpprojectay2.repository.RepoGroups;
import com.java.sdpprojectay2.repository.RepoRules;
import com.java.sdpprojectay2.repository.RepoRulesGroup;
import com.java.sdpprojectay2.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class DaoGroups {

    @Autowired
    private RepoGroups repoGroups;
    @Autowired
    RepoRules repoRules;
    @Autowired
    RepoRulesGroup repoRulesGroup;
    @Autowired
    PageUtils pageUtils;


    public Response insert(Groups group) {
        return new Response().setResponse(repoGroups.save(group.setDeleted(false)));
    }

    public Response update(Groups group) {
        Optional<Groups> oldGroup = repoGroups.findById(group.getId());
        if (oldGroup.isPresent()) {
            return new Response().setResponse(repoGroups.save(oldGroup.get().setName(group.getName())));
        } else {
            throw new NotFoundException();
        }
    }

    public Response changeVisibility(Groups group) {
        Optional<Groups> oldGroup = repoGroups.findById(group.getId());
        if (oldGroup.isPresent()) {
            return new Response().setResponse(repoGroups.save(oldGroup.get()));
        } else {
            throw new NotFoundException();
        }
    }

/*    public Response delete(Groups group) {
        Optional<Groups> oldGroup = repoGroups.findById(group.getId());
        if (oldGroup.isPresent()) {
            daoRulesGroup.delete(oldGroup.get().getId());
            return new Response().setBody(repoGroups.save(oldGroup.get().setDeleted(true)));
        } else {
            throw new NotFoundException("Data Not Found");
        }
    }*/


    public List<Rules> localSelectRulesByGroup(long idGroup){
        List<Rules> rules = new ArrayList<>();
        List<RulesGroup> rulesGroups = repoRulesGroup.findAllByIdGroup(idGroup);
        for (RulesGroup rule : rulesGroups) {
            rules.add(repoRules.findById(rule.getIdRule()).orElse(null));
        }
        return rules;
    }


    public Response selectById(long id) {
        Optional<Groups> group = repoGroups.findById(id);
        if (group.isPresent()) {
            return new Response().setResponse(group);
        } else {
            throw new NotFoundException();
        }
    }

    List<Groups> localSelect() {
        List<Groups> groupsList = repoGroups.findAll();
        if (groupsList != null && groupsList.size() > 0) {
            return groupsList;
        } else {
            return null;
        }
    }

    public Groups localSelectById(long id) {
        Optional<Groups> group = repoGroups.findById(id);
        return group.orElse(null);
    }

}
