package com.java.sdpprojectay2.repository;

import com.java.sdpprojectay2.model.entity.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface ReportRepository extends CrudRepository<Report, Integer> {
    List<Report> findAll();
}
