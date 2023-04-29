package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.Groups;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoGroups extends CrudRepository<Groups, Long> {

    List<Groups> findAll();
}
