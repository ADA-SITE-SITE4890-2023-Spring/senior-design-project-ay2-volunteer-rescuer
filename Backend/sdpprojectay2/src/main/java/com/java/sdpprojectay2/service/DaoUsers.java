package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.dto.*;
import com.java.sdpprojectay2.exceptions.AccessDeniedException;
import com.java.sdpprojectay2.exceptions.AlreadyExistException;
import com.java.sdpprojectay2.exceptions.AuthErrorException;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.java.sdpprojectay2.utils.EncryptUtils;
import com.java.sdpprojectay2.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;


@Component
public class DaoUsers {

    @Autowired
    RepoUsers repoUsers;
    @Autowired
    DaoMedia daoMedia;
    @Autowired
    DaoGroups daoGroups;
    @Autowired
    PageUtils pageUtils;
    @Autowired
    DaoOTP daoOTP;
    @Autowired
    DaoLsim daoLsim;

    Map<String, Users> auth = new HashMap<>();

    @Autowired
    private void init() {
        List<Users> users = repoUsers.findAllByTokenNotNull();
        for (Users e : users) {
            auth.remove(e.getToken());
            auth.put(e.getToken(), e);
        }
    }

    public Response insert(Users user, String phoneNumber) {
        if (repoUsers.findByEmail(user.getEmail()).isPresent() || repoUsers.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new AlreadyExistException();
        }
        repoUsers.save(user.setPassword(new EncryptUtils().encrypt(user.getPassword()))
                        .setPhoneNumber(phoneNumber)
                        .setIdGroup(3)
                        .setCreatedDate(LocalDateTime.now()));
        return new Response();
    }

    public Response update(Users users) {
        Optional<Users> optionalUser = repoUsers.findById(users.getId());
        if (optionalUser.isPresent()) {
            repoUsers.save(optionalUser.get()
                    .setFirstName(users.getFirstName() != null && users.getFirstName().length() > 0
                    ? users.getFirstName() : optionalUser.get().getFirstName())
                    .setLastName(users.getLastName() != null && users.getLastName().length() > 0
                            ? users.getLastName() : optionalUser.get().getLastName())
                    .setEmail(users.getEmail() != null && users.getEmail().length() > 0
                            ? users.getEmail() : optionalUser.get().getEmail())
                    .setPassword(users.getPassword() != null && users.getPassword().length() > 0
                            ? new EncryptUtils().encrypt(users.getPassword()) : optionalUser.get().getPassword())
            );
            return new Response();
        } else {
            throw new NotFoundException();
        }
    }

    public Response delete(long id) {
        Optional<Users> user = repoUsers.findById(id);
        if (user.isPresent()) {
            repoUsers.save(user.get().setDeleted(true));
            return new Response();
        } else {
            throw new NotFoundException();
        }
    }

    public Response selectBy(String query, Integer limit, Integer ascending, Integer page, String orderBy) {
        Page<Users> users = repoUsers.findAllBy(query, pageUtils.getPageConfig(page, limit, ascending, orderBy, false));
        if (users != null && !users.isEmpty()) {
            return new Response().setResponse(new ResponsePagination()
                    .setDataList(users.getContent())
                    .setTotal(users.getTotalElements())
            );
        } else {
            throw new NotFoundException();
        }
    }


    public Response login(RequestLogin requestLogin){
        Optional<Users> user = repoUsers.findByPhoneNumberAndPassword(requestLogin.getPhoneNumber(),
                new EncryptUtils().encrypt(requestLogin.getPassword()));
        if (user.isPresent()) {
            if (user.get().getIdGroup() != 3){
                throw new AccessDeniedException();
            }
            repoUsers.save(user.get().setToken(UUID.randomUUID().toString().replaceAll("-","")));
            auth.put(user.get().getToken(), user.get());
            return new Response().
                    setResponse(new ResponseLogin()
                            .setToken(user.get().getToken())
                            .setUser(user.get())
                            .setRules(daoGroups.localSelectRulesByGroup(user.get().getIdGroup()))
                    );
        }else {
            throw new NotFoundException();
        }
    }

    public Response checkToken(String token) {
        Users users = auth.get(token);
        if (users != null) {
            return new Response().setResponse(users);
        } else {
            throw new AuthErrorException();
        }
    }

    public Response resetPassword(OtpCreateRequestDto otpCreateRequestDto) {
        Optional<Users> user = repoUsers.findByPhoneNumber(otpCreateRequestDto.getPhoneNumber());
        if (user.isPresent()) {
            System.out.println("isliyirem");
            return daoOTP.createOtp(otpCreateRequestDto);

        } else {
            throw new NotFoundException();
        }
    }

    public Response resetPasswordConfirm(OtpCheckRequestDto otpCheckRequestDto) {
        Optional<Users> user = repoUsers.findByPhoneNumber(otpCheckRequestDto.getPhoneNumber());
        if (user.isPresent()) {
            return daoOTP.checkOtp(otpCheckRequestDto);
        } else {
            throw new NotFoundException();
        }
    }


    public Users localSelectByToken(String token){
        Users user = auth.get(token);
        if (user != null) {
            return user;
        }else {
            throw new AccessDeniedException();
        }
    }

    private String getOTP(int size) {
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedToken.toString();
    }

    public Users getCustomerById(long id) {
        Optional<Users> users = repoUsers.findById(id);
        return users.orElse(null);
    }

    public Response resetPass(String password, String passwordAgain, String phoneNumber) {
        Users user = repoUsers.findByPhoneNumber(phoneNumber).orElseThrow(NotFoundException::new);


        if(password.equals(passwordAgain)) {
            repoUsers.save(
                    user.setPassword(new EncryptUtils().encrypt(password))
            );
            return new Response().setResponse("Successfully change password");
        } else {
            return new Response().setCode(HttpStatus.BAD_REQUEST.value()).setResponse("passwords are not equals");
        }

    }
}
