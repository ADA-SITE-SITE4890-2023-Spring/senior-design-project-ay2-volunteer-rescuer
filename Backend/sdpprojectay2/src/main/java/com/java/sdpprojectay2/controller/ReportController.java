package com.java.sdpprojectay2.controller;

import com.java.sdpprojectay2.model.entity.Report;
import com.java.sdpprojectay2.service.ReportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation("To create new report")
    @PostMapping(value = "/create")
    public Report create(@RequestBody Report report) {
        return reportService.create(report);
    }

    @PostMapping(value = "/update")
    public Report update(@RequestBody Report report) {
        return reportService.update(report);
    }

    @GetMapping(value = "/reports")
    public List<Report> getAll() {
        return reportService.getAll();
    }

    @GetMapping(value = "/reports/{id}")
    public Report getById(@PathVariable("id") Integer id) {
        return reportService.getById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        reportService.deleteById(id);
    }
}
