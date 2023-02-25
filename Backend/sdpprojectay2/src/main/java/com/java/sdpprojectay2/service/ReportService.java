package com.java.sdpprojectay2.service;

import com.example.mylibraryproject.model.entity.Book;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReportService {
    Report create(@RequestBody Report book);

    Report update(@RequestBody Report book);

    List<Report> getAll();

    Report getById(Integer id);

    void deleteById(Integer id);
}
