package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.ModelFileUrls;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.EmergencyDetails;
import com.java.sdpprojectay2.model.EmergencyDetailsFiles;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoEmergencyDetails;
import com.java.sdpprojectay2.repository.RepoEmergencyDetailsFiles;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReportDetailsController {

    @Autowired
    RepoEmergencyDetails repoEmergencyDetails;
    @Autowired
    RepoEmergencyDetailsFiles repoEmergencyDetailsFiles;
    @Autowired
    RepoUsers repoUsers;

    @GetMapping(value = "/report-details")
    public String getReports(@RequestParam("id") long id, Model model){
        Optional<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findById(id);
        List<String> images = new ArrayList<>();
        String video = "";
        if (emergencyDetails.isPresent()) {
            for (EmergencyDetailsFiles e: repoEmergencyDetailsFiles.findAllByIdEmergencyDetails(emergencyDetails.get().getId())) {
                if (e.getUrl().contains("jpeg") || e.getUrl().contains("jpg") || e.getUrl().contains("png")) {
                    images.add(e.getUrl());
                }
                if (e.getUrl().contains("mp4") || e.getUrl().contains("webm")){
                    video = e.getUrl();
                }
            }
            String email = SecurityUtil.getSessionUser();
            if(email != null){
                Users user = repoUsers.findByEmail(email).orElseThrow();
                model.addAttribute("user", user);
            }
            model.addAttribute("emergencyDetails",emergencyDetails.get());
            model.addAttribute("images", images);
            model.addAttribute("video", video);
        return "report-details";
        }
        else {
            return "page404";
        }
    }

    @PostMapping(value = "/report-status")
    public String approve(@RequestParam long id, @RequestParam int status){
        Optional<EmergencyDetails> e = repoEmergencyDetails.findById(id);
        e.ifPresent(emergencyDetails -> repoEmergencyDetails.save(emergencyDetails.setStatus(status)));
//        return "redirect:report-details?id="+id;
        return "redirect:index";
    }

}
