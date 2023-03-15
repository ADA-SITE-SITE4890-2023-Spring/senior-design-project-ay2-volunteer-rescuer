package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.model.entity.Report;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReportService {
    Report create(@RequestBody Report report);

    Report update(@RequestBody Report report);

    List<Report> getAll();

    Report getById(Integer id);

    void deleteById(Integer id);
}
