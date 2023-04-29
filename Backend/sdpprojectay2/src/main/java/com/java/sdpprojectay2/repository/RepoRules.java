package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.Rules;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface RepoRules extends CrudRepository<Rules, Long> {

    List<Rules> findAllByIdIn(Collection<Long> idList);

}
