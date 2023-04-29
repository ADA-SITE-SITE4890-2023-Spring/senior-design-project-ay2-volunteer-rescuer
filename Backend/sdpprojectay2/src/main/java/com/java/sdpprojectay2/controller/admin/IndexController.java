package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.IndexDto;
import com.java.sdpprojectay2.dto.SearchForm;
import com.java.sdpprojectay2.enums.Status;
import com.java.sdpprojectay2.model.EmergencyDetails;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoEmergencyDetails;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ibrahim Sadigov
 * @mailto : isadigov4638@ada.edu.az
 * @created : 16 April, 2023
 **/

@Controller
public class IndexController {

    @Autowired
    RepoUsers repoUsers;
    @Autowired
    RepoEmergencyDetails repoEmergencyDetails;

    @GetMapping(value = {"/", "/index"})
    public String getIndex(Model model){
        long numberOfUsers = repoUsers.countByIdGroup(3);
        long numberOfReports = repoEmergencyDetails.countByStatus(1);
        long numberOfAccepted = repoEmergencyDetails.countByStatus(2);
        long numberOfDenied = repoEmergencyDetails.countByStatus(3);
        List<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findAllByStatus(1);
        IndexDto index = new IndexDto();
        if (emergencyDetails.isEmpty()){
            emergencyDetails = new ArrayList<>();
        }
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        index.setEmergencyDetails(emergencyDetails)
                .setNumberOfUsers(numberOfUsers)
                .setNumberOfReports(numberOfReports)
                .setNumberOfAccepted(numberOfAccepted)
                .setNumberOfDenied(numberOfDenied);
        model.addAttribute("index", index);
        return "index";
    }




    @PostMapping("/search-index")
    public String performSearch(@ModelAttribute("searchIndex") SearchForm searchIndex, Model model) {
        String searchTerm = searchIndex.getSearchTerm();
        List<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findAllBySearch(searchIndex.getSearchTerm(), Status.WAITING.getStatus());
        String email = SecurityUtil.getSessionUser();
        long numberOfUsers = repoUsers.countByIdGroup(3);
        long numberOfReports = repoEmergencyDetails.countByStatus(1);
        long numberOfAccepted = repoEmergencyDetails.countByStatus(2);
        long numberOfDenied = repoEmergencyDetails.countByStatus(3);
        if(email != null){
            Users user1 = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user1);
        }

        if (emergencyDetails.isEmpty()){
            emergencyDetails = new ArrayList<>();
        }
        IndexDto index = new IndexDto();
        model.addAttribute("searchIndex", searchTerm);
        index.setEmergencyDetails(emergencyDetails)
                .setNumberOfUsers(numberOfUsers)
                .setNumberOfReports(numberOfReports)
                .setNumberOfAccepted(numberOfAccepted)
                .setNumberOfDenied(numberOfDenied);
        model.addAttribute("index", index);
        return "search-index";
    }

}
