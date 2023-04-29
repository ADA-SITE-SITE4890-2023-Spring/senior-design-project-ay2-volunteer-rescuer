package com.java.sdpprojectay2.model;


import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Where(clause = "deleted=false")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idGroup;
    @JsonIgnore
    private String otp;
    private String password;
    @JsonIgnore
    private String token;
    @JsonIgnore
    private String resetToken;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private long gender;
    private String phoneNumber;
    @JsonIgnore
    @Column(columnDefinition = "TINYINT(1)")
    private boolean deleted;
    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();


    public Users() {
    }

    public long getId() {
        return id;
    }

    public Users setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public Users setIdGroup(long idGroup) {
        this.idGroup = idGroup;
        return this;
    }

    public String getOtp() {
        return otp;
    }

    public Users setOtp(String otp) {
        this.otp = otp;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public Users setToken(String token) {
        this.token = token;
        return this;
    }

    public String getResetToken() {
        return resetToken;
    }

    public Users setResetToken(String resetToken) {
        this.resetToken = resetToken;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Users setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Users setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Users setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public long getGender() {
        return gender;
    }

    public Users setGender(long gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Users setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Users setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Users setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", idGroup=" + idGroup +
                ", token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                '}';
    }
}
