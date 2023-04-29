package com.java.sdpprojectay2.controller;

import com.java.sdpprojectay2.dto.Response;
import com.java.sdpprojectay2.model.EmergencyDetails;
import com.java.sdpprojectay2.service.DaoEmergencyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mobile/volunteer-rescue-project/emergency-details")
public class ApiEmergencyDetails {

    @Autowired
    DaoEmergencyDetails daoEmergencyDetails;

    @PostMapping(value = "/insert", consumes = "multipart/form-data")
    public Response insert(@RequestParam String latitude,
                           @RequestParam String longitude,
                           @RequestParam String description,
                           @RequestParam MultipartFile[] multipartFiles,
                           @RequestParam MultipartFile voice,
                           @RequestParam long idUser,
                           HttpServletRequest httpServletRequest){
        return daoEmergencyDetails.insert(latitude, longitude, description, multipartFiles, voice, idUser, httpServletRequest);
    }

}
