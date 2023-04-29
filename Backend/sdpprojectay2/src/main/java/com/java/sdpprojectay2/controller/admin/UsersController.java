package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.SearchForm;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    RepoUsers repoUsers;

    public UsersController(RepoUsers repoUsers) {
        this.repoUsers = repoUsers;
    }

    @GetMapping("/users")
    public String getAcceptedReports(Model model) {
        List<Users> userDetails  = repoUsers.findByIdGroup(3);
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        model.addAttribute("userDetails", userDetails);
        return "users";
    }

    @GetMapping("/users-details")
    public String getUserDetail(@RequestParam("id") long id, Model model) {
        Users user = repoUsers.findById(id).orElseThrow(NotFoundException::new);
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user1 = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user1);
        }
        if(user != null) {
            model.addAttribute("userDetails", user);
            return "user-details";
        }
        return "page404";
    }

    @PostMapping("/search-users")
    public String performSearch(@ModelAttribute("searchUser") SearchForm searchUser, Model model) {
        String searchTerm = searchUser.getSearchTerm();
        List<Users> searchResults = repoUsers.findAllBySearch(searchTerm);
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user1 = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user1);
        }
        model.addAttribute("userDetails", searchResults);

        return "search-users";
    }

    @GetMapping(value = "search-users")
    public String search(Model model, @RequestParam(value = "searchTerm", defaultValue = "", required = false) String searchTerm){
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user1 = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user1);
        }
        model.addAttribute("searchTerm", searchTerm);
        return "search-users";
    }

    @GetMapping("/employees")
    public String getAllEmployee(Model model) {
        List<Users> employees = repoUsers.findByIdGroup(2);
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user1 = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user1);
        }
        model.addAttribute("employees", employees);
        return "employees";
    }
}
