package com.java.sdpprojectay2.service.impl;

import com.java.sdpprojectay2.model.entity.Report;
import com.java.sdpprojectay2.repository.ReportRepository;
import com.java.sdpprojectay2.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public reportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report create(@RequestBody Report report) {
        reportRepository.save(report);
        return report;
    }

    public Report update(@RequestBody Report report) {
        if (report.getId() != null) {
            reportRepository.save(report);
        }
        return report;
    }

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public Report getById(Integer id) {
        return reportRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        reportRepository.deleteById(id);
    }
}