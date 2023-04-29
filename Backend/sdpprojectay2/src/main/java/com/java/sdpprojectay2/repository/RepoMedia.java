package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.Media;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RepoMedia extends CrudRepository<Media, Long> {

    Optional<Media> findByRemotePath(String remotePath);

}
