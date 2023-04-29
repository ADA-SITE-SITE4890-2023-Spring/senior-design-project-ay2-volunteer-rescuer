package com.java.sdpprojectay2.controller;

import com.java.sdpprojectay2.dto.Response;
import com.java.sdpprojectay2.service.DaoMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/volunteer-rescue-project/media")
public class ApiMedia {

    @Autowired
    DaoMedia daoMedia;

    @PostMapping(value = "/insert", consumes = "multipart/form-data")
    public Response insert(@RequestParam MultipartFile file, HttpServletRequest request) {
        return daoMedia.insert(file, request);
    }

    @GetMapping("/download/{year}/{month}/{day}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String year,
                                                 @PathVariable String month,
                                                 @PathVariable String day,
                                                 @PathVariable String fileName,
                                                 HttpServletRequest request) {
        return daoMedia.downloadFile(year, month, day, fileName, request);
    }

}
