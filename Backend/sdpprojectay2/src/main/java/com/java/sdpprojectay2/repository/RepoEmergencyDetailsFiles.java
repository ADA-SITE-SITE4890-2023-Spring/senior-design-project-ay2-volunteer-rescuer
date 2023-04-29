package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.EmergencyDetailsFiles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoEmergencyDetailsFiles extends CrudRepository<EmergencyDetailsFiles, Long> {

    List<EmergencyDetailsFiles> findAllByIdEmergencyDetails(long id);

    List<EmergencyDetailsFiles> findEmergencyDetailsFilesByIdEmergencyDetails(long id);
}
