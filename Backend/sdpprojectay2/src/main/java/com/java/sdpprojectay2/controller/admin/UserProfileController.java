package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.UserProfileDto;
import com.java.sdpprojectay2.exceptions.AlreadyExistException;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.security.SecurityUtil;
import com.java.sdpprojectay2.utils.EncryptUtils;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
public class UserProfileController {

    @Autowired
    RepoUsers repoUsers;

    @GetMapping(value = "/profile")
    public String getProfile(Model model, @RequestParam("idUser") long idUser){
        Users users = repoUsers.findById(idUser).orElseThrow(NotFoundException::new);
        users.setPassword(new EncryptUtils().decrypt(users.getPassword()));
        UserProfileDto userProfileDto = new UserProfileDto();
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        model.addAttribute("profile", users);
        model.addAttribute("userProfileDto", userProfileDto);
        return "profile";
    }

    @PostMapping(value = "/profile-update")
    public String updateProfile(@RequestParam("idUser") long idUser, @ModelAttribute UserProfileDto userProfileDto){
        Users users = repoUsers.findById(idUser).orElseThrow(NotFoundException::new);
        if (users != null){
            repoUsers.save(users
                    .setFirstName(userProfileDto.getInputFirstname())
                    .setLastName(userProfileDto.getInputLastname())
                    .setEmail(userProfileDto.getInputEmail())
                    .setPassword(new EncryptUtils().encrypt(userProfileDto.getInputPassword()))
            );
            return "redirect:/profile?idUser=" + idUser;
        }else {
            return "page404";
        }
        // TODO

    }

    @GetMapping(value = "/create-account")
    public String getCreateAccount(Model model){
        String email = SecurityUtil.getSessionUser();
        if(email != null){
            Users user = repoUsers.findByEmail(email).orElseThrow();
            model.addAttribute("user", user);
        }
        return "create-account";
    }

    @PostMapping(value = "/create-account-save")
    public String createAccount(@ModelAttribute UserProfileDto userProfileDto){
        Optional<Users> users = repoUsers.findByEmail(userProfileDto.getInputEmail());
        if (users.isPresent()){
            throw new AlreadyExistException();
        }
        else {
            repoUsers.save(new Users()
                    .setFirstName(userProfileDto.getInputFirstname())
                    .setLastName(userProfileDto.getInputLastname())
                    .setEmail(userProfileDto.getInputEmail())
                    .setPassword(new EncryptUtils().encrypt(userProfileDto.getInputPassword()))
                    .setIdGroup(2)
                    .setCreatedDate(LocalDateTime.now())
                    .setDeleted(false)
            );
        }

        return "redirect:/profile?idUser=1";
    }

}
