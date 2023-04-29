package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.AcceptedReportDetailsPanel;
import com.java.sdpprojectay2.dto.SearchForm;
import com.java.sdpprojectay2.enums.Status;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class EmergencyDetailsController {


    private final RepoEmergencyDetails repoEmergencyDetails;
    @Autowired
    RepoEmergencyDetailsFiles repoEmergencyDetailsFiles;
    @Autowired
    RepoUsers repoUsers;

    public EmergencyDetailsController(RepoEmergencyDetails repoEmergencyDetails) {
        this.repoEmergencyDetails = repoEmergencyDetails;
    }


    @GetMapping("/accepted-reports")
    public String getAcceptedReports(Model model) {
        AcceptedReportDetailsPanel acceptedReportDetailsPanel = new AcceptedReportDetailsPanel();
        HashMap<Boolean, String> option = new HashMap<Boolean, String>() {{
            put(true, "Doğru");
            put(false, "Yalnış");
        }};
        HashMap<Integer, String> isAcceptedOption = new HashMap<Integer, String>() {{
            put(Status.APPROVE.getStatus(), "Doğru");
            put(Status.DENIED.getStatus(), "Yalnış");
        }};
        int[] priorities = {1, 2, 3};
        List<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findByStatusOrderByCreatedDate(Status.APPROVE.getStatus());
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        model.addAttribute("emergencyDetails", emergencyDetails);
        model.addAttribute("acceptedReportDetailPanel", acceptedReportDetailsPanel);
        model.addAttribute("option", option);
        model.addAttribute("priorities", priorities);
        model.addAttribute("isAcceptedOption", isAcceptedOption);
        return "accepted-reports";
    }

    @PostMapping("/denied/reports/change/details")
    public String changeDeniedReportsDetails(@RequestParam("id") long id, @ModelAttribute AcceptedReportDetailsPanel acceptedReportDetailsPanel, Model model) {
        EmergencyDetails emergencyDetails = repoEmergencyDetails.findById(id).orElseThrow(NotFoundException::new);
        if (emergencyDetails != null) {
            emergencyDetails
                    .setStatus(acceptedReportDetailsPanel.isCheckAccept())
                    .setNote(acceptedReportDetailsPanel.getReportNote())
                    .setResolved(acceptedReportDetailsPanel.isCheckResolved())
                    .setPriority(acceptedReportDetailsPanel.getCheckPriority());
            repoEmergencyDetails.save(emergencyDetails);
        } else {
            return "redirect:page404";
        }
        return "redirect:/denied-reports";
    }


    @GetMapping("/denied-reports")
    public String getDeniedReports(Model model) {
        AcceptedReportDetailsPanel acceptedReportDetailsPanel = new AcceptedReportDetailsPanel();
        HashMap<Boolean, String> option = new HashMap<Boolean, String>() {{
            put(true, "Doğru");
            put(false, "Yalnış");
        }};
        HashMap<Integer, String> isAcceptedOption = new HashMap<Integer, String>() {{
            put(Status.APPROVE.getStatus(), "Doğru");
            put(Status.DENIED.getStatus(), "Yalnış");
        }};
        int[] priorities = {1, 2, 3};
        List<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findByStatusOrderByCreatedDate(Status.DENIED.getStatus());
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        model.addAttribute("emergencyDetails", emergencyDetails);
        model.addAttribute("deniedReportDetailPanel", acceptedReportDetailsPanel);
        model.addAttribute("option", option);
        model.addAttribute("priorities", priorities);
        model.addAttribute("isAcceptedOption", isAcceptedOption);
        return "denied-reports";
    }

    @PostMapping("/reports/change/details")
    public String changeAcceptedReportsDetails(@RequestParam("id") long id, @ModelAttribute AcceptedReportDetailsPanel acceptedReportDetailsPanel, Model model) {
        EmergencyDetails emergencyDetails = repoEmergencyDetails.findById(id).orElseThrow(NotFoundException::new);
        if (emergencyDetails != null) {
            emergencyDetails
                    .setStatus(acceptedReportDetailsPanel.isCheckAccept())
                    .setNote(acceptedReportDetailsPanel.getReportNote())
                    .setResolved(acceptedReportDetailsPanel.isCheckResolved())
                    .setPriority(acceptedReportDetailsPanel.getCheckPriority());
            repoEmergencyDetails.save(emergencyDetails);
        } else {
            return "redirect:page404";
        }
        return "redirect:/accepted-reports";
    }


    @GetMapping("/reports-detail")
    public String getReportsDetail(@RequestParam("id") long id, Model model) {
        EmergencyDetails emergencyDetails = repoEmergencyDetails.findById(id).orElseThrow(NotFoundException::new);
        if(emergencyDetails != null) {
            List<EmergencyDetailsFiles> emergencyDetailsFilesList = repoEmergencyDetailsFiles.findEmergencyDetailsFilesByIdEmergencyDetails(id);
            String email = SecurityUtil.getSessionUser();
            if(email != null){
                Users user = repoUsers.findByEmail(email).orElseThrow();
                model.addAttribute("user", user);
            }
            model.addAttribute("emergencyDetailsFilesList", emergencyDetailsFilesList);
            model.addAttribute("emergencyDetails", emergencyDetails);
            return "report-details";
        }
        return "redirect:page404";
    }


    @PostMapping("/search-accepted")
    public String searchAcceptedReports(@ModelAttribute("searchIndex") SearchForm searchIndex, Model model) {
        AcceptedReportDetailsPanel acceptedReportDetailsPanel = new AcceptedReportDetailsPanel();
        String searchTerm = searchIndex.getSearchTerm();
        HashMap<Boolean, String> option = new HashMap<Boolean, String>() {{
            put(true, "Doğru");
            put(false, "Yalnış");
        }};
        HashMap<Integer, String> isAcceptedOption = new HashMap<Integer, String>() {{
            put(Status.APPROVE.getStatus(), "Doğru");
            put(Status.DENIED.getStatus(), "Yalnış");
        }};
        int[] priorities = {1, 2, 3};
        List<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findAllBySearch(searchTerm, Status.APPROVE.getStatus());
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        model.addAttribute("emergencyDetails", emergencyDetails);
        model.addAttribute("acceptedReportDetailPanel", acceptedReportDetailsPanel);
        model.addAttribute("option", option);
        model.addAttribute("priorities", priorities);
        model.addAttribute("searchIndex", searchTerm);
        model.addAttribute("isAcceptedOption", isAcceptedOption);
        return "search-accepted";
    }

    @PostMapping("/search-denied")
    public String searchDeniedReports(@ModelAttribute("searchIndex") SearchForm searchIndex, Model model) {
        AcceptedReportDetailsPanel acceptedReportDetailsPanel = new AcceptedReportDetailsPanel();
        String searchTerm = searchIndex.getSearchTerm();
        HashMap<Boolean, String> option = new HashMap<Boolean, String>() {{
            put(true, "Doğru");
            put(false, "Yalnış");
        }};
        HashMap<Integer, String> isAcceptedOption = new HashMap<Integer, String>() {{
            put(Status.APPROVE.getStatus(), "Doğru");
            put(Status.DENIED.getStatus(), "Yalnış");
        }};
        int[] priorities = {1, 2, 3};
        List<EmergencyDetails> emergencyDetails = repoEmergencyDetails.findAllBySearch(searchTerm, Status.DENIED.getStatus());
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        model.addAttribute("emergencyDetails", emergencyDetails);
        model.addAttribute("acceptedReportDetailPanel", acceptedReportDetailsPanel);
        model.addAttribute("option", option);
        model.addAttribute("priorities", priorities);
        model.addAttribute("searchIndex", searchTerm);
        model.addAttribute("isAcceptedOption", isAcceptedOption);
        return "search-denied";
    }
}
