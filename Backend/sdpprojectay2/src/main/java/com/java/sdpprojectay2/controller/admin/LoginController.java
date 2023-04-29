package com.java.sdpprojectay2.controller.admin;

import com.java.sdpprojectay2.dto.LoginAdminPanel;
import com.java.sdpprojectay2.exceptions.AccessDeniedException;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.service.EmailService;
import com.java.sdpprojectay2.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    RepoUsers repoUsers;

    @Value("${server.address.url}")
    String serverUrl;

    @Autowired
    EmailService emailService;

    @GetMapping("/login")
    public String getLoginPage(Model model){
//        LoginAdminPanel loginAdminPanel = new LoginAdminPanel();
//        model.addAttribute("login",loginAdminPanel);
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String email,
//                        @RequestParam String password){
//        Optional<Users> users = repoUsers.findByEmailAndPassword(email, new BCryptPasswordEncoder().encode(password));
//        if (users.isPresent()) {
//            return "redirect:index";
//        }
//        else {
//            return "redirect:login";
//        }
//    }

    @GetMapping("/password-send")
    public String getPassword(){
        return "password-send";
    }

    @PostMapping("/password-send")
    public String getPasswordSend(@RequestParam String email) throws MessagingException {
        Optional<Users> users = repoUsers.findByEmail(email);
        System.out.println(email);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        if (users.isPresent()) {
            repoUsers.save(users.get().setResetToken(token));
            String subject = "Reset password";
            String text = "Şifrənizi yeniləmək üçün aşağıda verilən linkə keçid edin:\n" +
                    "Link: " + serverUrl + "password-recovery?token=" + token;

            emailService.sendSimpleMessage(email, subject,text);
        }
        return "redirect:password-send";
    }

    @GetMapping("/password-recovery")
    public String getRecovery(@RequestParam String token,  Model model){
        Optional<Users> users = repoUsers.findByResetToken(token);
        if (users.isPresent()) {
            model.addAttribute("user", users);
            return "password-recovery";
        }

        else {
            return "page404";
        }
    }

    @PostMapping("/password-reset")
    public String resetPassword(@RequestParam long id, @RequestParam String password1, @RequestParam String password2){
        Optional<Users> users = repoUsers.findById(id);
        if (users.isPresent()) {
            if (password1.equals(password2)) {
                repoUsers.save(users.get().setResetToken(null).setPassword(new EncryptUtils().encrypt(password1)));
                return "redirect:login";
            }
            else {
                return "redirect:password-recovery";
            }
        }
        else {
            return "redirect:password-recovery";
        }
    }


}
