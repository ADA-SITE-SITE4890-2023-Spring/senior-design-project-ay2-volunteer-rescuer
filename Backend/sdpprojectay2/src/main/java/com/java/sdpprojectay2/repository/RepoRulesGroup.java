package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.RulesGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoRulesGroup extends CrudRepository<RulesGroup, Long> {

    List<RulesGroup> findAllByIdGroup(long idGroup);

}
